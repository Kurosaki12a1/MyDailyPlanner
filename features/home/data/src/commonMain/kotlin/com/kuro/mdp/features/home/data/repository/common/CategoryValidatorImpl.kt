package com.kuro.mdp.features.home.data.repository.common

import com.kuro.mdp.features.home.domain.model.categories.MainCategoryHome
import com.kuro.mdp.features.home.domain.model.editor.error.CategoryValidateError
import com.kuro.mdp.features.home.domain.repository.common.CategoryValidator
import com.kuro.mdp.shared.utils.validation.ValidateResult

/**
 * Created by: minhthinh.h on 2/6/2025
 *
 * Description:
 */
class CategoryValidatorImpl : CategoryValidator {
    override fun validate(data: MainCategoryHome): ValidateResult<CategoryValidateError> {
        return if (data.id == 0) {
            ValidateResult(false, CategoryValidateError.EmptyCategoryError)
        } else {
            ValidateResult(true, null)
        }
    }
}