package com.kuro.mdp.shared.utils

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

expect fun String.format(format: String, vararg args: Any?): String

expect fun String.format(vararg args: Any?): String