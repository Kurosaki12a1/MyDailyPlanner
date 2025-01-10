package com.kuro.mdp.shared.presentation.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
actual fun getDynamicScheme(themeType: ThemeUiTypeName): ColorScheme? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        val context = LocalContext.current
        when (themeType) {
            ThemeUiTypeName.DEFAULT -> if (isSystemInDarkTheme()) {
                dynamicDarkColorScheme(context)
            } else {
                dynamicLightColorScheme(context)
            }

            ThemeUiTypeName.LIGHT -> dynamicLightColorScheme(context)
            ThemeUiTypeName.DARK -> dynamicDarkColorScheme(context)
        }
    } else null
}