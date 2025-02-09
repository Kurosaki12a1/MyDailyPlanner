package com.kuro.mdp.features.settings.presentation.ui.settings.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kuro.mdp.features.settings.presentation.theme.SettingsTheme
import com.kuro.mdp.shared.presentation.extensions.string
import com.kuro.mdp.shared.presentation.theme.ThemeUiType
import com.kuro.mdp.shared.presentation.theme.ThemeUiTypeName
import com.kuro.mdp.shared.presentation.views.SegmentedButtonItem
import com.kuro.mdp.shared.presentation.views.SegmentedButtons

/**
 * Created by: minhthinh.h on 12/26/2024
 *
 * Description:
 */
@Composable
internal fun ThemeColorsChooser(
    modifier: Modifier = Modifier,
    themeColors: ThemeUiType,
    onThemeColorUpdate: ThemeUiType.() -> Unit,
) {
    Surface(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.surfaceContainer,
    ) {
        Column(
            modifier = Modifier.padding(vertical = 12.dp, horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Text(
                text = SettingsTheme.strings.mainSettingsThemeTitle.string(),
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.titleMedium,
            )
            SegmentedButtons(
                modifier = Modifier.fillMaxWidth(),
                items = ThemeColorsTypeSegmentedItems.entries.toTypedArray(),
                selectedItem = themeColors.toSegmentedItem(),
                onItemClick = { onThemeColorUpdate.invoke(it.toThemeColorsType()) },
            )
        }
    }
}

internal enum class ThemeColorsTypeSegmentedItems : SegmentedButtonItem {
    LIGHT {
        override val title: String @Composable get() = SettingsTheme.strings.lightThemeTitle.string()
    },
    DEFAULT {
        override val title: String @Composable get() = SettingsTheme.strings.systemThemeTitle.string()
    },
    DARK {
        override val title: String @Composable get() = SettingsTheme.strings.darkThemeTitle.string()
    },
}

internal fun ThemeUiType.toSegmentedItem(): ThemeColorsTypeSegmentedItems = when (this.name) {
    ThemeUiTypeName.DEFAULT -> ThemeColorsTypeSegmentedItems.DEFAULT
    ThemeUiTypeName.LIGHT -> ThemeColorsTypeSegmentedItems.LIGHT
    ThemeUiTypeName.DARK -> ThemeColorsTypeSegmentedItems.DARK
}

internal fun ThemeColorsTypeSegmentedItems.toThemeColorsType(): ThemeUiType = when (this) {
    ThemeColorsTypeSegmentedItems.LIGHT -> ThemeUiType(ThemeUiTypeName.LIGHT)
    ThemeColorsTypeSegmentedItems.DEFAULT -> ThemeUiType(ThemeUiTypeName.DEFAULT)
    ThemeColorsTypeSegmentedItems.DARK -> ThemeUiType(ThemeUiTypeName.DARK)
}
