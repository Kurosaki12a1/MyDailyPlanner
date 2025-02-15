package com.kuro.mdp.shared.presentation

import android.app.Activity
import android.text.format.DateFormat
import android.view.WindowManager
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import java.util.Locale

@Composable
actual fun ScreenProtection(enable: Boolean) {
    val context = LocalContext.current
    val window = (context as Activity?)?.window
    LaunchedEffect(enable) {
        if (enable) {
            window?.setFlags(
                WindowManager.LayoutParams.FLAG_SECURE,
                WindowManager.LayoutParams.FLAG_SECURE
            )
        } else {
            window?.clearFlags(WindowManager.LayoutParams.FLAG_SECURE)
        }
    }
}

actual fun changeLanguage(appLanguage: LanguageUiType) {
    val locale: Locale = if (appLanguage.code != null) Locale(appLanguage.code) else Locale(LanguageUiType.EN.code!!)
    Locale.setDefault(locale)
}

@Composable
actual fun is24HourFormat(): Boolean {
    return DateFormat.is24HourFormat(LocalContext.current)
}

@Composable
actual fun getScreenWidthDp(): Dp = LocalConfiguration.current.screenWidthDp.dp

@Composable
actual fun getScreenHeightDp(): Dp = LocalConfiguration.current.screenHeightDp.dp