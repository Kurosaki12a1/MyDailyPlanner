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
    data class Success<T>(val data: T) : ResultState<T>()

    data class Failure(val exception: Throwable) : ResultState<Nothing>()

    val isSuccess: Boolean
        get() = this is Success

    val isFailure: Boolean
        get() = this is Failure

    override fun toString(): String {
        return when (this) {
            is Success -> "Success($data)"
            is Failure -> exception.message ?: ""
        }
    }
}

suspend fun <T> wrap(block: suspend () -> T): ResultState<T> {
    return try {
        ResultState.Success(block.invoke())
    } catch (e: Exception) {
        e.printStackTrace()
        ResultState.Failure(e)
    }
}

fun <T> wrapFlow(block: suspend () -> Flow<T>): Flow<ResultState<T>> = flow {
    block.invoke().catch { error -> emit(ResultState.Failure(error)) }
        .collect { data -> emit(ResultState.Success(data)) }
}

suspend fun <T> ResultState<T>.handle(
    onFailure: suspend (Throwable) -> Unit = {},
    onSuccess: suspend (T) -> Unit = {}
) = when (this) {
    is ResultState.Success -> onSuccess(data)
    is ResultState.Failure -> onFailure(exception)
}

suspend fun <T> Flow<ResultState<T>>.collectAndHandle(
    onFailure: suspend (Throwable) -> Unit = {},
    onSuccess: suspend (T) -> Unit = {}
) = collect { data ->
    when (data) {
        is ResultState.Success -> onSuccess(data.data)
        is ResultState.Failure -> onFailure(data.exception)
    }
}

fun <A, B> ResultState<A>.transform(
    mapper: (A) -> B
): ResultState<B> {
    return when (this) {
        is ResultState.Failure -> ResultState.Failure(exception)
        is ResultState.Success -> ResultState.Success(mapper(data))
    }
}

fun <A, B> Flow<ResultState<A>>.transform(
    mapper: (A) -> B
): Flow<ResultState<B>> = flow {
    this@transform.collect { data ->
        when (data) {
            is ResultState.Failure -> {
                emit(ResultState.Failure(data.exception))
            }

            is ResultState.Success -> {
                emit(ResultState.Success(mapper(data.data)))
            }
        }
    }
}

