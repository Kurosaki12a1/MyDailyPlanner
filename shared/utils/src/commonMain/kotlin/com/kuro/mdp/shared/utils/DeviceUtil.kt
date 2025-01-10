package com.kuro.mdp.shared.utils

import androidx.compose.runtime.Composable

/**
 * Created by: minhthinh.h on 1/9/2025
 *
 * Description:
 */

enum class DevicePlatform {
    ANDROID,
    IOS
}

expect fun getPlatform(): DevicePlatform

expect fun getDeviceInfo(): String

@Composable
expect fun ScreenProtection(enable: Boolean)