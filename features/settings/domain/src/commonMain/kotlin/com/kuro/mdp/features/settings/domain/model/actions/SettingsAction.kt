package com.kuro.mdp.features.settings.domain.model.actions

import com.kuro.mdp.shared.domain.model.settings.Settings
import com.kuro.mdp.shared.presentation.screenmodel.contract.BaseAction

/**
 * Created by: minhthinh.h on 2/25/2025
 *
 * Description:
 */
sealed class SettingsAction : BaseAction {
    data object ChangedTasksSettings : SettingsAction()
    data object ChangedThemeSettings : SettingsAction()
    data object ResetToDefault : SettingsAction()
    data class ChangeAllSettings(val settings: Settings) : SettingsAction()
}
