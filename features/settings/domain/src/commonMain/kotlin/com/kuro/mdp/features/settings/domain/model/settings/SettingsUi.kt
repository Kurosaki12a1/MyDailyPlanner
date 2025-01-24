package com.kuro.mdp.features.settings.domain.model.settings

import kotlinx.serialization.Serializable

/**
 * Created by: minhthinh.h on 12/26/2024
 *
 * Description:
 */
@Serializable
data class SettingsUi(
    val themeSettings: ThemeSettingsUi,
    val tasksSettings: TasksSettingsUi,
)