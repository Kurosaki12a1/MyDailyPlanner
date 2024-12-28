package com.kuro.mdp.features.home.presentation.ui.home.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.kuro.mdp.features.home.domain.model.templates.TemplatesSortedType
import com.kuro.mdp.features.home.presentation.ui.home.theme.resources.HomeIcons
import com.kuro.mdp.features.home.presentation.ui.home.theme.resources.HomeStrings
import com.kuro.mdp.features.home.presentation.ui.home.theme.resources.LocalHomeIcons
import com.kuro.mdp.features.home.presentation.ui.home.theme.resources.LocalHomeStrings
import com.kuro.mdp.features.home.presentation.ui.home.theme.resources.fetchHomeIcons
import com.kuro.mdp.features.home.presentation.ui.home.theme.resources.fetchHomeString

/**
 * Created by: minhthinh.h on 12/19/2024
 *
 * Description:
 */
@Composable
internal fun HomeTheme(content: @Composable () -> Unit) {
    val strings = fetchHomeString()
    val icons = fetchHomeIcons()

    CompositionLocalProvider(
        LocalHomeStrings provides strings,
        LocalHomeIcons provides icons,
        content = content,
    )
}

internal object HomeTheme {
    val strings: HomeStrings
        @Composable get() = LocalHomeStrings.current

    val icons: HomeIcons
        @Composable get() = LocalHomeIcons.current
}

@Composable
fun TemplatesSortedType.mapToString() = when (this) {
    TemplatesSortedType.DATE -> HomeTheme.strings.sortedTypeDate
    TemplatesSortedType.CATEGORIES -> HomeTheme.strings.sortedTypeCategories
    TemplatesSortedType.DURATION -> HomeTheme.strings.sortedTypeDuration
}


