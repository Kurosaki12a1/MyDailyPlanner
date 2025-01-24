package com.kuro.mdp.features.settings.presentation.ui.categories

import com.kuro.mdp.features.settings.domain.model.categories.CategoriesUi
import com.kuro.mdp.features.settings.domain.model.categories.MainCategoryUi
import com.kuro.mdp.shared.presentation.screenmodel.contract.BaseViewState
import kotlinx.serialization.Serializable

/**
 * Created by: minhthinh.h on 1/24/2025
 *
 * Description:
 */
@Serializable
data class CategoriesViewState(
    val selectedMainCategory: MainCategoryUi? = null,
    val categories: List<CategoriesUi> = emptyList(),
    val failure: String? = null
) : BaseViewState