package com.kuro.mdp.features.home.domain.model.editor.error

import com.kuro.mdp.shared.utils.validation.ValidateError
import kotlinx.serialization.Serializable

/**
 * Created by: minhthinh.h on 2/6/2025
 *
 * Description:
 */
@Serializable
sealed class CategoryValidateError : ValidateError {
    data object EmptyCategoryError : CategoryValidateError()
}