package com.kuro.mdp.features.settings.presentation.mappers

import com.kuro.mdp.features.settings.domain.model.settings.SettingsUi
import com.kuro.mdp.shared.domain.model.settings.Settings

/**
 * Created by: minhthinh.h on 12/26/2024
 *
 * Description:
 */
internal fun Settings.mapToUi() = SettingsUi(
    themeSettings = themeSettings.mapToUi(),
    tasksSettings = tasksSettings.mapToUi(),
)

internal fun SettingsUi.mapToDomain() = Settings(
    themeSettings = themeSettings.mapToDomain(),
    tasksSettings = tasksSettings.mapToDomain(),
)
