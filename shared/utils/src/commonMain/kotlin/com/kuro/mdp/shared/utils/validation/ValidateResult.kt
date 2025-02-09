package com.kuro.mdp.shared.utils.validation

/**
 * Created by: minhthinh.h on 2/6/2025
 *
 * Description:
 */
data class ValidateResult<E : ValidateError>(
    val isValid: Boolean,
    val validError: E?,
)

suspend fun <E : ValidateError> ValidateResult<E>.handle(
    onValid: suspend () -> Unit,
    onError: suspend (E) -> Unit,
) = when (this.isValid) {
    true -> onValid()
    false -> onError(checkNotNull(this.validError))
}
