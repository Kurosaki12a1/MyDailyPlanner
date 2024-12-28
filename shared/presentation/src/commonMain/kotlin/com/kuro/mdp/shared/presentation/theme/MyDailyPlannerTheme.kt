package com.kuro.mdp.shared.presentation.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.kuro.mdp.shared.presentation.LocalAppLanguage
import com.kuro.mdp.shared.presentation.fetchAppLanguage
import com.kuro.mdp.shared.presentation.language
import com.kuro.mdp.shared.presentation.theme.materials.baseShapes
import com.kuro.mdp.shared.presentation.theme.materials.baseTypography
import com.kuro.mdp.shared.presentation.theme.resources.LocalAppColorsType
import com.kuro.mdp.shared.presentation.theme.resources.LocalAppElevations
import com.kuro.mdp.shared.presentation.theme.resources.LocalAppIcons
import com.kuro.mdp.shared.presentation.theme.resources.fetchAppColorsType
import com.kuro.mdp.shared.presentation.theme.resources.fetchAppElevations
import com.kuro.mdp.shared.presentation.theme.resources.fetchAppIcons

/**
 * Created by: minhthinh.h on 12/9/2024
 *
 * Description:
 */
@Composable
fun MyDailyPlannerTheme(
    themeUiType: ThemeUiType = AppTheme.themeType,
    colorsType: ColorsUiType = AppTheme.colorsType,
    content: @Composable () -> Unit
) {
    val colorsScheme = colorsType.fetchColorScheme(themeUiType)
    val appColorsType = fetchAppColorsType(themeUiType, colorsType)
    val appLanguage = fetchAppLanguage(language)
    val appElevations = fetchAppElevations()
    val appIcons = fetchAppIcons()
    MaterialTheme(
        colorScheme = colorsScheme,
        shapes = baseShapes,
        typography = baseTypography
    ) {
        CompositionLocalProvider(
            LocalAppElevations provides appElevations,
            LocalAppIcons provides appIcons,
            LocalAppColorsType provides appColorsType,
            LocalAppLanguage provides appLanguage
        ) {
            content()
        }
    }
}