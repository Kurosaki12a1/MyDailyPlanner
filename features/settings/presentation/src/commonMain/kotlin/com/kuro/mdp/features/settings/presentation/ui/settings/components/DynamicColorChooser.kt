package com.kuro.mdp.features.settings.presentation.ui.settings.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kuro.mdp.features.settings.presentation.theme.SettingsTheme
import com.kuro.mdp.shared.utils.extensions.string

/**
 * Created by: minhthinh.h on 12/26/2024
 *
 * Description:
 */
@Composable
internal fun DynamicColorChooser(
    modifier: Modifier = Modifier,
    dynamicColor: Boolean,
    onChange: (Boolean) -> Unit,
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.surfaceContainer,
    ) {
        Row(
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = SettingsTheme.strings.mainSettingsDynamicColorTitle.string(),
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.titleMedium,
            )
            Switch(checked = dynamicColor, onCheckedChange = onChange)
        }
    }
}

