package com.kuro.mdp.shared.utils

import android.text.format.DateFormat
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
actual fun is24HourFormat(): Boolean {
    return DateFormat.is24HourFormat(LocalContext.current)
}