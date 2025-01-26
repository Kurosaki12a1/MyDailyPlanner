package com.kuro.mdp.features.settings.domain.model.categories

sealed class CategoriesAction {
    data class SetUp(val categories: List<CategoriesUi>, val selected: MainCategoryUi?) :
        CategoriesAction()

    data class UpdateCategories(val categories: List<CategoriesUi>) : CategoriesAction()
    data class ChangeMainCategory(val category: MainCategoryUi) : CategoriesAction()
}