package com.kuro.mdp.shared.utils

import androidx.compose.runtime.Composable
import platform.Foundation.NSDateFormatter
import platform.Foundation.NSLocale
import platform.Foundation.currentLocale

@Composable
actual fun is24HourFormat(): Boolean {
    // Get the current locale from iOS.
    val locale = NSLocale.currentLocale
    // Get the date format string using template "j" (time format symbol).
    val dateFormat = NSDateFormatter.dateFormatFromTemplate("j", 0u, locale)
    // If dateFormat contains "a", it uses AM/PM (12-hour). Otherwise, 24-hour.
    return dateFormat?.contains("a")?.not() ?: true
}