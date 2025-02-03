package com.kuro.mdp.shared.presentation.views

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kuro.mdp.shared.presentation.theme.AppTheme
import com.kuro.mdp.shared.utils.extensions.string
import com.kuro.mdp.shared.utils.functional.TimeFormat

@Composable
fun TimeFormatSelector(
    modifier: Modifier = Modifier,
    isVisible: Boolean,
    format: TimeFormat,
    onChangeFormat: (TimeFormat) -> Unit,
) {
    if (isVisible) {
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.spacedBy(0.dp),
        ) {
            TextButton(
                modifier = Modifier.weight(1f),
                onClick = { onChangeFormat(TimeFormat.AM) },
                shape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
                colors = ButtonDefaults.textButtonColors(
                    containerColor = when (format) {
                        TimeFormat.AM -> MaterialTheme.colorScheme.primaryContainer
                        TimeFormat.PM -> MaterialTheme.colorScheme.surfaceContainerHigh
                    },
                ),
            ) {
                Text(
                    text = AppTheme.strings.amFormatTitle.string(),
                    color = when (format) {
                        TimeFormat.AM -> MaterialTheme.colorScheme.onPrimaryContainer
                        TimeFormat.PM -> MaterialTheme.colorScheme.onSurfaceVariant
                    },
                    style = MaterialTheme.typography.labelMedium,
                )
            }
            TextButton(
                modifier = Modifier.weight(1f),
                onClick = { onChangeFormat(TimeFormat.PM) },
                shape = RoundedCornerShape(bottomStart = 8.dp, bottomEnd = 8.dp),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
                colors = ButtonDefaults.textButtonColors(
                    containerColor = when (format) {
                        TimeFormat.PM -> MaterialTheme.colorScheme.primaryContainer
                        TimeFormat.AM -> MaterialTheme.colorScheme.surfaceContainerHigh
                    },
                ),
            ) {
                Text(
                    text = AppTheme.strings.pmFormatTitle.string(),
                    color = when (format) {
                        TimeFormat.AM -> MaterialTheme.colorScheme.onSurfaceVariant
                        TimeFormat.PM -> MaterialTheme.colorScheme.onPrimaryContainer
                    },
                    style = MaterialTheme.typography.labelMedium,
                )
            }
        }
    }
}
