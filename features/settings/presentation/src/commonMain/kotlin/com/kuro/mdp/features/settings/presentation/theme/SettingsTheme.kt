package com.kuro.mdp.features.settings.presentation.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.kuro.mdp.features.settings.presentation.theme.resource.LocalSettingsIcons
import com.kuro.mdp.features.settings.presentation.theme.resource.LocalSettingsStrings
import com.kuro.mdp.features.settings.presentation.theme.resource.SettingsIcons
import com.kuro.mdp.features.settings.presentation.theme.resource.SettingsStrings
import com.kuro.mdp.features.settings.presentation.theme.resource.fetchSettingsIcons
import com.kuro.mdp.features.settings.presentation.theme.resource.fetchSettingsStrings

/**
 * Created by: minhthinh.h on 12/25/2024
 *
 * Description:
 */

@Composable
internal fun SettingsTheme(content: @Composable () -> Unit) {
    val strings = fetchSettingsStrings()
    val icons = fetchSettingsIcons()

    CompositionLocalProvider(
        LocalSettingsStrings provides strings,
        LocalSettingsIcons provides icons,
        content = content,
    )
}


internal object SettingsTheme {
    val strings: SettingsStrings
        @Composable get() = LocalSettingsStrings.current

    val icons: SettingsIcons
        @Composable get() = LocalSettingsIcons.current
}