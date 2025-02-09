package com.kuro.mdp.features.settings.presentation.mappers

import androidx.compose.runtime.Composable
import com.kuro.mdp.features.settings.domain.model.template.TemplatesSortedType
import com.kuro.mdp.features.settings.presentation.theme.SettingsTheme
import com.kuro.mdp.shared.presentation.extensions.string

@Composable
internal fun TemplatesSortedType.mapToString(): String = when (this) {
    TemplatesSortedType.DATE -> SettingsTheme.strings.sortedTypeDate.string()
    TemplatesSortedType.CATEGORIES -> SettingsTheme.strings.sortedTypeCategories.string()
    TemplatesSortedType.DURATION -> SettingsTheme.strings.sortedTypeDuration.string()
}
