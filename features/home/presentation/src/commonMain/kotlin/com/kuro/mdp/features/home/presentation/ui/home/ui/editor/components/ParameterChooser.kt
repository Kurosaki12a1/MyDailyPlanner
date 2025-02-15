package com.kuro.mdp.features.home.presentation.ui.home.ui.editor.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import com.kuro.mdp.features.home.presentation.ui.home.theme.HomeTheme
import com.kuro.mdp.shared.presentation.extensions.string
import com.kuro.mdp.shared.presentation.views.SegmentedButtonItem
import com.kuro.mdp.shared.presentation.views.SegmentedButtons

/**
 * Created by: minhthinh.h on 2/10/2025
 *
 * Description:
 */
@Composable
internal fun ParameterChooser(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    selected: Boolean,
    leadingIcon: Painter? = null,
    title: String,
    optionsButton: (@Composable () -> Unit)? = null,
    description: String,

    onChangeSelected: (Boolean) -> Unit,
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.surfaceContainerLow,
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if (leadingIcon != null) {
                Icon(
                    painter = leadingIcon,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface,
                )
            }
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.titleMedium,
                )
                Text(
                    text = description,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                if (optionsButton != null) optionsButton()
                Switch(
                    enabled = enabled,
                    modifier = Modifier.align(Alignment.Top),
                    checked = selected,
                    onCheckedChange = onChangeSelected,
                    thumbContent = {
                        Icon(
                            modifier = Modifier.size(SwitchDefaults.IconSize),
                            imageVector = if (selected) Icons.Default.Check else Icons.Default.Close,
                            contentDescription = HomeTheme.strings.parameterChooserSwitchIconDesc.string(),
                        )
                    },
                )
            }
        }
    }
}

@Composable
internal fun <Item : SegmentedButtonItem> SegmentedParametersChooser(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    parameters: Array<Item>,
    selected: Item,
    leadingIcon: Painter? = null,
    title: String,
    onChangeSelected: (Item) -> Unit,
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.surfaceContainerLow,
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                if (leadingIcon != null) {
                    Icon(
                        painter = leadingIcon,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurface,
                    )
                }
                Text(
                    text = title,
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.titleMedium,
                )
            }
            SegmentedButtons(
                enabled = enabled,
                items = parameters,
                selectedItem = selected,
                onItemClick = onChangeSelected,
            )
        }
    }
}