package com.kuro.mdp.features.settings.domain.model.template

import com.kuro.mdp.features.settings.domain.model.categories.CategoriesUi

sealed class TemplatesAction {
    data class UpdateCategories(val categories: List<CategoriesUi>) : TemplatesAction()
    data class UpdateTemplates(val templates: List<TemplateUi>) : TemplatesAction()
    data class ChangeSortedType(val type: TemplatesSortedType) : TemplatesAction()
}
