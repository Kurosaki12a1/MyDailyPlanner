package com.kuro.mdp.features.settings.presentation.ui.template

import com.kuro.mdp.features.settings.domain.model.template.TemplateUi
import com.kuro.mdp.features.settings.domain.model.template.TemplatesSortedType
import com.kuro.mdp.shared.domain.model.template.RepeatTime
import com.kuro.mdp.shared.presentation.screenmodel.contract.BaseEvent

sealed class TemplatesEvent : BaseEvent {
    data object Init : TemplatesEvent()
    data object ClearFailure : TemplatesEvent()
    data object NavigateToSettings : TemplatesEvent()
    data class AddTemplate(val template: TemplateUi) : TemplatesEvent()
    data class UpdateTemplate(val template: TemplateUi) : TemplatesEvent()
    data class RestartTemplateRepeat(val template: TemplateUi) : TemplatesEvent()
    data class StopTemplateRepeat(val template: TemplateUi) : TemplatesEvent()
    data class AddRepeatTemplate(val time: RepeatTime, val template: TemplateUi) : TemplatesEvent()
    data class DeleteRepeatTemplate(val time: RepeatTime, val template: TemplateUi) : TemplatesEvent()
    data class UpdatedSortedType(val type: TemplatesSortedType) : TemplatesEvent()
    data class DeleteTemplate(val id: Int) : TemplatesEvent()
    data class ShowTemplateCreator(val shouldShow: Boolean) : TemplatesEvent()
}