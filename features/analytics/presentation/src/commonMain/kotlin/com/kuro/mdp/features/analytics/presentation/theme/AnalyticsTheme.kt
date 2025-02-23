package com.kuro.mdp.features.analytics.presentation.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.kuro.mdp.features.analytics.presentation.theme.resources.AnalyticsIcons
import com.kuro.mdp.features.analytics.presentation.theme.resources.AnalyticsStrings
import com.kuro.mdp.features.analytics.presentation.theme.resources.LocalAnalyticsIcons
import com.kuro.mdp.features.analytics.presentation.theme.resources.LocalAnalyticsStrings
import com.kuro.mdp.features.analytics.presentation.theme.resources.fetchAnalyticsIcons
import com.kuro.mdp.features.analytics.presentation.theme.resources.fetchAnalyticsString

/**
 * Created by: minhthinh.h on 2/18/2025
 *
 * Description:
 */
@Composable
internal fun AnalyticsTheme(content: @Composable () -> Unit) {
    val strings = fetchAnalyticsString()
    val icons = fetchAnalyticsIcons()

    CompositionLocalProvider(
        LocalAnalyticsStrings provides strings,
        LocalAnalyticsIcons provides icons,
        content = content,
    )
}

internal object AnalyticsTheme {
    val strings: AnalyticsStrings
        @Composable get() = LocalAnalyticsStrings.current

    val icons: AnalyticsIcons
        @Composable get() = LocalAnalyticsIcons.current
}

