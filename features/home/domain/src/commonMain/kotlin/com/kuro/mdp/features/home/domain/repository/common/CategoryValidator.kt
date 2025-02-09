package com.kuro.mdp.features.home.domain.repository.common

import com.kuro.mdp.features.home.domain.model.categories.MainCategoryHome
import com.kuro.mdp.features.home.domain.model.editor.error.CategoryValidateError
import com.kuro.mdp.shared.utils.validation.Validator

/**
 * Created by: minhthinh.h on 2/6/2025
 *
 * Description:
 */
interface CategoryValidator : Validator<MainCategoryHome, CategoryValidateError>