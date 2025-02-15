package com.kuro.mdp.shared.utils

import platform.Foundation.NSString
import platform.Foundation.stringWithFormat
import platform.UIKit.UIDevice

actual fun String.format(format: String, vararg args: Any?): String {
    return NSString.stringWithFormat(format, args as Any)
}

actual fun String.format(vararg args: Any?): String {
    return NSString.stringWithFormat(this, args as Any)
}

// Returns the current platform of the device.
actual fun getPlatform(): DevicePlatform = DevicePlatform.IOS

// Returns a string containing the device's system name and version.
actual fun getDeviceInfo(): String = "${UIDevice.currentDevice.systemName()} ${UIDevice.currentDevice.systemVersion}"