package com.kuro.mdp.features.settings.presentation.ui.settings

import com.kuro.mdp.features.settings.domain.model.SettingsError
import com.kuro.mdp.features.settings.domain.model.TasksSettingsUi
import com.kuro.mdp.features.settings.domain.model.ThemeSettingsUi
import com.kuro.mdp.shared.presentation.screenmodel.contract.BaseViewState
import kotlinx.serialization.Serializable

/**
 * Created by: minhthinh.h on 12/26/2024
 *
 * Description:
 */
@Serializable
internal data class SettingsViewState(
    val themeSettings: ThemeSettingsUi? = null,
    val tasksSettings: TasksSettingsUi? = null,
    val failure: SettingsError? = null
) : BaseViewState

