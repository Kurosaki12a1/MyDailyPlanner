package com.kuro.mdp.shared.utils

import android.os.Build

actual fun String.format(format: String, vararg args: Any?): String {
    return java.lang.String.format(format, *args)
}

actual fun String.format(vararg args: Any?): String {
    return java.lang.String.format(this, *args)
}

actual fun getPlatform(): DevicePlatform = DevicePlatform.ANDROID

actual fun getDeviceInfo(): String = "${Build.VERSION.RELEASE} ${Build.MODEL}"