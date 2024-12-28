package com.kuro.mdp.features.settings.presentation.ui.settings.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.kuro.mdp.features.settings.presentation.theme.SettingsTheme
import com.kuro.mdp.shared.presentation.theme.ColorsUiType
import com.kuro.mdp.shared.presentation.theme.ColorsUiTypeName
import com.kuro.mdp.shared.utils.extensions.string

/**
 * Created by: minhthinh.h on 12/26/2024
 *
 * Description:
 */
@Composable
internal fun ColorsTypeChooser(
    modifier: Modifier = Modifier,
    colorsType: ColorsUiType,
    onChoose: (ColorsUiType) -> Unit,
) {
    Surface(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.surfaceContainer,
    ) {
        Column(
            modifier = Modifier.padding(vertical = 12.dp, horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Text(
                text = SettingsTheme.strings.mainSettingsColorsTitle.string(),
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.titleMedium,
            )
            Row(
                modifier = Modifier.fillMaxWidth().height(48.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                for (color in ColorsUiTypeName.entries) {
                    ColorTypeItem(
                        modifier = Modifier.weight(1f),
                        model = ColorsUiType(color),
                        selected = colorsType.name == color,
                        onClick = { if (colorsType.name != color) onChoose(ColorsUiType(color)) },
                    )
                }
            }
        }
    }
}

@Composable
internal fun ColorTypeItem(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    model: ColorsUiType,
    selected: Boolean,
    onClick: () -> Unit,
) {
    Surface(
        onClick = onClick,
        modifier = modifier.height(48.dp).widthIn(max = 80.dp),
        enabled = enabled,
        shape = MaterialTheme.shapes.medium,
        color = model.seed(),
    ) {
        if (selected) {
            Surface(
                modifier = Modifier.fillMaxSize().padding(horizontal = 12.dp, vertical = 8.dp),
                shape = MaterialTheme.shapes.medium,
                color = model.onSeed(),
            ) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = null,
                        tint = Color.White,
                    )
                }
            }
        }
    }
}
