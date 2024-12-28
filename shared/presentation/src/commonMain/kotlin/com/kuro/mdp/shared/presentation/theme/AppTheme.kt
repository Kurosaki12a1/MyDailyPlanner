package com.kuro.mdp.shared.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import com.kuro.mdp.shared.presentation.AppLanguage
import com.kuro.mdp.shared.presentation.LocalAppLanguage
import com.kuro.mdp.shared.presentation.theme.materials.blueDarkColorScheme
import com.kuro.mdp.shared.presentation.theme.materials.blueLightColorScheme
import com.kuro.mdp.shared.presentation.theme.materials.blueSeed
import com.kuro.mdp.shared.presentation.theme.materials.blue_theme_light_primary
import com.kuro.mdp.shared.presentation.theme.materials.pinkDarkColorScheme
import com.kuro.mdp.shared.presentation.theme.materials.pinkLightColorScheme
import com.kuro.mdp.shared.presentation.theme.materials.pinkSeed
import com.kuro.mdp.shared.presentation.theme.materials.pink_theme_light_primary
import com.kuro.mdp.shared.presentation.theme.materials.purpleDarkColorScheme
import com.kuro.mdp.shared.presentation.theme.materials.purpleLightColorScheme
import com.kuro.mdp.shared.presentation.theme.materials.purpleSeed
import com.kuro.mdp.shared.presentation.theme.materials.purple_theme_light_primary
import com.kuro.mdp.shared.presentation.theme.materials.redDarkColorScheme
import com.kuro.mdp.shared.presentation.theme.materials.redLightColorScheme
import com.kuro.mdp.shared.presentation.theme.materials.redSeed
import com.kuro.mdp.shared.presentation.theme.materials.red_theme_light_primary
import com.kuro.mdp.shared.presentation.theme.resources.AppElevations
import com.kuro.mdp.shared.presentation.theme.resources.AppIcons
import com.kuro.mdp.shared.presentation.theme.resources.AppStrings
import com.kuro.mdp.shared.presentation.theme.resources.LocalAppElevations
import com.kuro.mdp.shared.presentation.theme.resources.LocalAppIcons
import com.kuro.mdp.shared.presentation.theme.resources.LocalAppStrings
import kotlinx.serialization.Serializable

/**
 * Created by: minhthinh.h on 12/9/2024
 *
 * Description:
 */

object AppTheme {
    val themeType: ThemeUiType
        @Composable
        @ReadOnlyComposable
        get() = LocalThemeUiType.current

    val colorsType: ColorsUiType
        @Composable
        @ReadOnlyComposable
        get() = LocalColorsUiType.current

    val language: AppLanguage
        @Composable
        @ReadOnlyComposable
        get() = LocalAppLanguage.current

    val elevations: AppElevations
        @Composable
        @ReadOnlyComposable
        get() = LocalAppElevations.current

    val icons: AppIcons
        @Composable
        @ReadOnlyComposable
        get() = LocalAppIcons.current

    val strings: AppStrings
        @Composable
        @ReadOnlyComposable
        get() = LocalAppStrings.current
}

val LocalThemeUiType = staticCompositionLocalOf { ThemeUiType(name = ThemeUiTypeName.DEFAULT) }
val LocalColorsUiType = staticCompositionLocalOf { ColorsUiType(name = ColorsUiTypeName.BLUE) }

@Serializable
data class ThemeUiType(
    val name: ThemeUiTypeName
) {
    @Composable
    fun isDarkTheme() = when (name) {
        ThemeUiTypeName.DEFAULT -> isSystemInDarkTheme()
        ThemeUiTypeName.LIGHT -> false
        ThemeUiTypeName.DARK -> true
    }
}

@Serializable
data class ColorsUiType(
    val name: ColorsUiTypeName
) {
    fun seed() = when (name) {
        ColorsUiTypeName.RED -> redSeed
        ColorsUiTypeName.PINK -> pinkSeed
        ColorsUiTypeName.PURPLE -> purpleSeed
        ColorsUiTypeName.BLUE -> blueSeed
    }

    fun onSeed() = when (name) {
        ColorsUiTypeName.RED -> red_theme_light_primary
        ColorsUiTypeName.PINK -> pink_theme_light_primary
        ColorsUiTypeName.PURPLE -> purple_theme_light_primary
        ColorsUiTypeName.BLUE -> blue_theme_light_primary
    }

    @Composable
    fun fetchLightColorScheme() = when (name) {
        ColorsUiTypeName.RED -> redLightColorScheme
        ColorsUiTypeName.PINK -> pinkLightColorScheme
        ColorsUiTypeName.PURPLE -> purpleLightColorScheme
        ColorsUiTypeName.BLUE -> blueLightColorScheme
    }

    @Composable
    fun fetchDarkColorScheme() = when (name) {
        ColorsUiTypeName.RED -> redDarkColorScheme
        ColorsUiTypeName.PINK -> pinkDarkColorScheme
        ColorsUiTypeName.PURPLE -> purpleDarkColorScheme
        ColorsUiTypeName.BLUE -> blueDarkColorScheme
    }

    @Composable
    fun fetchColorScheme(themeType: ThemeUiType) = when (themeType.name) {
        ThemeUiTypeName.DEFAULT -> if (isSystemInDarkTheme()) fetchDarkColorScheme() else fetchLightColorScheme()
        ThemeUiTypeName.LIGHT -> fetchLightColorScheme()
        ThemeUiTypeName.DARK -> fetchDarkColorScheme()
    }
}

enum class ColorsUiTypeName {
    RED, PINK, PURPLE, BLUE
}

enum class ThemeUiTypeName {
    DEFAULT, LIGHT, DARK;
}