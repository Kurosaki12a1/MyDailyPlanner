package com.kuro.mdp.features.settings.domain.model.actions

import com.kuro.mdp.features.settings.domain.model.categories.CategoriesUi
import com.kuro.mdp.features.settings.domain.model.template.TemplateUi
import com.kuro.mdp.features.settings.domain.model.template.TemplatesSortedType
import com.kuro.mdp.shared.presentation.screenmodel.contract.BaseAction

/**
 * Created by: minhthinh.h on 2/25/2025
 *
 * Description:
 */
sealed class TemplatesAction : BaseAction {
    data object AddRepeatTemplates : TemplatesAction()
    data object AddTemplates : TemplatesAction()
    data object DeleteRepeatTemplates : TemplatesAction()
    data object DeleteTemplates : TemplatesAction()
    data object RestartTemplatesRepeat : TemplatesAction()
    data object StopTemplatesRepeat : TemplatesAction()
    data class UpdateCategories(val categories: List<CategoriesUi>) : TemplatesAction()
    data class UpdateTemplates(val templates: List<TemplateUi>) : TemplatesAction()
    data class ChangeSortedType(val type: TemplatesSortedType) : TemplatesAction()
}
