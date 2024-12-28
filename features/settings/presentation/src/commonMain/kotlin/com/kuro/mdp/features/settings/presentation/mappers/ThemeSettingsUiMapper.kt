package com.kuro.mdp.features.settings.presentation.mappers

import com.kuro.mdp.features.settings.domain.model.ThemeSettingsUi
import com.kuro.mdp.shared.domain.model.settings.ColorsType
import com.kuro.mdp.shared.domain.model.settings.LanguageType
import com.kuro.mdp.shared.domain.model.settings.ThemeSettings
import com.kuro.mdp.shared.domain.model.settings.ThemeType
import com.kuro.mdp.shared.presentation.LanguageUiType
import com.kuro.mdp.shared.presentation.theme.ColorsUiType
import com.kuro.mdp.shared.presentation.theme.ColorsUiTypeName
import com.kuro.mdp.shared.presentation.theme.ThemeUiType
import com.kuro.mdp.shared.presentation.theme.ThemeUiTypeName

/**
 * Created by: minhthinh.h on 12/26/2024
 *
 * Description:
 */
internal fun ThemeSettings.mapToUi() = ThemeSettingsUi(
    language = language.mapToUi(),
    themeColors = themeColors.mapToUi(),
    isDynamicColorEnable = isDynamicColorEnable,
    colorsType = colorsType.mapToUi(),
)

internal fun ThemeSettingsUi.mapToDomain() = ThemeSettings(
    language = language.mapToDomain(),
    themeColors = themeColors.mapToDomain(),
    isDynamicColorEnable = isDynamicColorEnable,
    colorsType = colorsType.mapToDomain(),
)

fun LanguageType.mapToUi() = LanguageUiType.entries.find { it.code == this.code }!!

fun ThemeType.mapToUi(): ThemeUiType = when (this) {
    ThemeType.DEFAULT -> ThemeUiType(ThemeUiTypeName.DEFAULT)
    ThemeType.LIGHT -> ThemeUiType(ThemeUiTypeName.LIGHT)
    ThemeType.DARK -> ThemeUiType(ThemeUiTypeName.DARK)
}

fun ColorsType.mapToUi(): ColorsUiType = when (this) {
    ColorsType.PINK -> ColorsUiType(ColorsUiTypeName.PINK)
    ColorsType.PURPLE -> ColorsUiType(ColorsUiTypeName.PURPLE)
    ColorsType.RED -> ColorsUiType(ColorsUiTypeName.RED)
    ColorsType.BLUE -> ColorsUiType(ColorsUiTypeName.BLUE)
}

fun LanguageUiType.mapToDomain() = LanguageType.entries.find { it.code == this.code }!!

fun ThemeUiType.mapToDomain(): ThemeType = when (this.name) {
    ThemeUiTypeName.DEFAULT -> ThemeType.DEFAULT
    ThemeUiTypeName.LIGHT -> ThemeType.LIGHT
    ThemeUiTypeName.DARK -> ThemeType.DARK
}

fun ColorsUiType.mapToDomain(): ColorsType = when (this.name) {
    ColorsUiTypeName.PINK -> ColorsType.PINK
    ColorsUiTypeName.PURPLE -> ColorsType.PURPLE
    ColorsUiTypeName.RED -> ColorsType.RED
    ColorsUiTypeName.BLUE -> ColorsType.BLUE
}
