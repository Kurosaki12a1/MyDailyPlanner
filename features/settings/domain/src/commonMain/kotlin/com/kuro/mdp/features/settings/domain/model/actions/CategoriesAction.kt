package com.kuro.mdp.features.settings.domain.model.actions

import com.kuro.mdp.features.settings.domain.model.categories.CategoriesUi
import com.kuro.mdp.features.settings.domain.model.categories.MainCategoryUi
import com.kuro.mdp.shared.presentation.screenmodel.contract.BaseAction

sealed class CategoriesAction : BaseAction {
    data class SetUp(val categories: List<CategoriesUi>, val selected: MainCategoryUi?) : CategoriesAction()
    data class UpdateCategories(val categories: List<CategoriesUi>) : CategoriesAction()
    data class ChangeMainCategory(val category: MainCategoryUi) : CategoriesAction()
}