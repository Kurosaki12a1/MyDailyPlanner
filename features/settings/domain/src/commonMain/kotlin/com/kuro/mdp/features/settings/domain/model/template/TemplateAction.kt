package com.kuro.mdp.features.settings.domain.model.template

import com.kuro.mdp.features.settings.domain.model.categories.CategoriesUi
import com.kuro.mdp.features.settings.domain.model.categories.MainCategoryUi

sealed class TemplateAction {
    data class SetUp(val categories: List<CategoriesUi>, val selected: MainCategoryUi?) : TemplateAction()
    data class UpdateCategories(val categories: List<CategoriesUi>) : TemplateAction()
    data class ChangeMainCategory(val category: MainCategoryUi) : TemplateAction()
}
