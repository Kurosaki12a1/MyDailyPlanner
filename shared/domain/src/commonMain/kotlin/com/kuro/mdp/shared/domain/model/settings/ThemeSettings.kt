package com.kuro.mdp.shared.domain.model.settings

data class ThemeSettings(
    val language: LanguageType = LanguageType.DEFAULT,
    val themeColors: ThemeType = ThemeType.DEFAULT,
    val colorsType: ColorsType = ColorsType.PINK,
    val isDynamicColorEnable: Boolean = false,
)
