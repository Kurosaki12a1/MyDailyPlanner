package com.kurp.mdp.shared.data.mappers.settings

import com.kuro.mdp.shared.domain.model.settings.ThemeSettings
import com.kurp.mdp.shared.data.entities.settings.ThemeSettingsEntity

fun ThemeSettingsEntity.mapToDomain() = ThemeSettings(
    language = language,
    themeColors = themeColors,
    colorsType = colorsType,
    isDynamicColorEnable = isDynamicColorEnable,
)

fun ThemeSettings.mapToData() = ThemeSettingsEntity(
    id = 0,
    language = language,
    themeColors = themeColors,
    colorsType = colorsType,
    isDynamicColorEnable = isDynamicColorEnable,
)
