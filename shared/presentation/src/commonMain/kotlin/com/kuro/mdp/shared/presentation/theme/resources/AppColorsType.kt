package com.kuro.mdp.shared.presentation.theme.resources

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import com.kuro.mdp.shared.presentation.theme.ColorsUiType
import com.kuro.mdp.shared.presentation.theme.ThemeUiType

/**
 * Created by: minhthinh.h on 12/9/2024
 *
 * Description:
 */
data class ColorsType(
    val isDark: Boolean,
    val colors: ColorsUiType,
)

val LocalAppColorsType = staticCompositionLocalOf<ColorsType> {
    error("Colors type is not provided")
}

@Composable
fun fetchAppColorsType(
    themeType: ThemeUiType,
    colors: ColorsUiType,
) = ColorsType(
    isDark = themeType.isDarkTheme(),
    colors = colors,
)
