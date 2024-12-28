package com.kuro.mdp.features.settings.presentation.ui.settings

import com.kuro.mdp.features.settings.domain.model.TasksSettingsUi
import com.kuro.mdp.features.settings.domain.model.ThemeSettingsUi
import com.kuro.mdp.shared.presentation.screenmodel.contract.BaseEvent

/**
 * Created by: minhthinh.h on 12/26/2024
 *
 * Description:
 */
internal sealed class SettingsEvent : BaseEvent {
    data object Init : SettingsEvent()
    data object PressDonateButton : SettingsEvent()
    data class ChangedThemeSettings(val themeSettings: ThemeSettingsUi) : SettingsEvent()
    data class ChangedTasksSettings(val tasksSettings: TasksSettingsUi) : SettingsEvent()
}
