package com.kuro.mdp.features.overview.presentation.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.kuro.mdp.features.overview.presentation.theme.resources.LocalOverViewIcons
import com.kuro.mdp.features.overview.presentation.theme.resources.LocalOverViewStrings
import com.kuro.mdp.features.overview.presentation.theme.resources.OverViewIcons
import com.kuro.mdp.features.overview.presentation.theme.resources.OverViewStrings
import com.kuro.mdp.features.overview.presentation.theme.resources.fetchOverViewIcons
import com.kuro.mdp.features.overview.presentation.theme.resources.fetchOverViewStrings

/**
 * Created by: minhthinh.h on 1/20/2025
 *
 * Description:
 */
@Composable
internal fun OverViewTheme(content: @Composable () -> Unit) {
    val strings = fetchOverViewStrings()
    val icons = fetchOverViewIcons()

    CompositionLocalProvider(
        LocalOverViewStrings provides strings,
        LocalOverViewIcons provides icons,
        content = content,
    )
}

internal object OverViewTheme {
    val strings: OverViewStrings
        @Composable get() = LocalOverViewStrings.current

    val icons: OverViewIcons
        @Composable get() = LocalOverViewIcons.current
}
