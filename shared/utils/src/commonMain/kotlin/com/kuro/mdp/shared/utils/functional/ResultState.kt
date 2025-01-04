package com.kuro.mdp.shared.utils.functional

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

/**
 * Created by: minhthinh.h on 12/24/2024
 *
 * Description:
 */
sealed class ResultState<out T> {
    data object Default : ResultState<Nothing>()

    data object Loading : ResultState<Nothing>()

    data class Success<T>(val data: T) : ResultState<T>()

    data class Failure(val exception: Throwable) : ResultState<Nothing>()

    val isSuccess: Boolean
        get() = this is Success

    val isFailure: Boolean
        get() = this is Failure

    override fun toString(): String {
        return when (this) {
            is Default -> Constants.State.DEFAULT
            is Loading -> Constants.State.LOADING
            is Success -> "Success($data)"
            is Failure -> exception.message ?: ""
        }
    }
}

fun <T> Result<T>.toResultState(): ResultState<T> {
    return fold(
        onSuccess = { ResultState.Success(it) },
        onFailure = { ResultState.Failure(it) }
    )
}

fun <T> default(): ResultState<T> = ResultState.Default

fun <T> loading(): ResultState<T> = ResultState.Loading

suspend fun <T> wrap(block: suspend () -> T): ResultState<T> {
    return try {
        ResultState.Success(block.invoke())
    } catch (e: Exception) {
        e.printStackTrace()
        ResultState.Failure(e)
    }
}

suspend fun <T> wrapFlow(block: suspend () -> Flow<T>): Flow<ResultState<T>> = flow {
    emit(ResultState.Loading)
    block.invoke().catch { error -> emit(ResultState.Failure(error)) }
        .collect { data -> emit(ResultState.Success(data)) }
}

suspend fun <T> ResultState<T>.handle(
    onDefault: suspend () -> Unit = {},
    onLoading: suspend () -> Unit = {},
    onFailure: suspend (Throwable) -> Unit = {},
    onSuccess: suspend (T) -> Unit = {}
) = when (this) {
    is ResultState.Default -> onDefault.invoke()
    is ResultState.Loading -> onLoading.invoke()
    is ResultState.Success -> onSuccess(data)
    is ResultState.Failure -> onFailure(exception)
}

suspend fun <T> Flow<ResultState<T>>.collectAndHandle(
    onDefault: suspend () -> Unit = {},
    onLoading: suspend () -> Unit = {},
    onFailure: suspend (Throwable) -> Unit = {},
    onSuccess: suspend (T) -> Unit = {}
) = collect { data ->
    when (data) {
        is ResultState.Default -> onDefault.invoke()
        is ResultState.Loading -> onLoading.invoke()
        is ResultState.Success -> onSuccess(data.data)
        is ResultState.Failure -> onFailure(data.exception)
    }
}