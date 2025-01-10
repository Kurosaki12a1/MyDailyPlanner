package com.kuro.mdp.shared.utils

import android.app.Activity
import android.os.Build
import android.view.WindowManager
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext

actual fun getPlatform(): DevicePlatform = DevicePlatform.ANDROID

actual fun getDeviceInfo(): String = "${Build.VERSION.RELEASE} ${Build.MODEL}"

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