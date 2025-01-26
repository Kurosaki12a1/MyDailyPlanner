package com.kuro.mdp.features.settings.presentation.ui.categories.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.kuro.mdp.features.settings.presentation.theme.SettingsTheme
import com.kuro.mdp.shared.utils.extensions.string

@Composable
internal fun CategoriesParametersMenu(
    modifier: Modifier = Modifier,
    expanded: Boolean,
    onDismiss: () -> Unit,
    onRestoreDefaultCategories: () -> Unit,
) {
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = onDismiss,
        modifier = modifier,
        shape = MaterialTheme.shapes.large,
        offset = DpOffset(0.dp, 4.dp),
    ) {
        DropdownMenuItem(
            onClick = onRestoreDefaultCategories,
            text = {
                Text(
                    text = SettingsTheme.strings.restoreDefaultCategories.string(),
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.titleMedium,
                )
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface,
                )
            },
        )
    }
}
