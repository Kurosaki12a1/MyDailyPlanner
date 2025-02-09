package com.kuro.mdp.shared.utils.validation

/**
 * Created by: minhthinh.h on 2/6/2025
 *
 * Description:
 */
interface Validator<D, E : ValidateError> {
    fun validate(data: D): ValidateResult<E>
}
