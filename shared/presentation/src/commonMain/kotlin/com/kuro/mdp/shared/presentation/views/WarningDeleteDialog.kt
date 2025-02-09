package com.kuro.mdp.shared.presentation.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kuro.mdp.shared.presentation.extensions.string
import com.kuro.mdp.shared.presentation.theme.AppTheme

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun WarningDeleteDialog(
    modifier: Modifier = Modifier,
    text: String,
    onDismiss: () -> Unit,
    onAction: () -> Unit,
) {
    BasicAlertDialog(
        onDismissRequest = onDismiss,
        modifier = modifier.width(328.dp)
    ) {
        Surface(
            shape = MaterialTheme.shapes.extraLarge,
            color = MaterialTheme.colorScheme.surfaceContainer,
        ) {
            Column {
                WarningDeleteDialogHeader(modifier = Modifier.fillMaxWidth())
                Text(
                    modifier = Modifier.padding(start = 24.dp, end = 24.dp, top = 16.dp)
                        .fillMaxWidth(),
                    text = text,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    style = MaterialTheme.typography.bodyMedium,
                )
                DialogButtons(
                    confirmTitle = AppTheme.strings.warningDeleteConfirmTitle.string(),
                    onConfirmClick = onAction,
                    onCancelClick = onDismiss,
                )
            }
        }
    }
}

@Composable
internal fun WarningDeleteDialogHeader(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.padding(start = 24.dp, end = 24.dp, top = 24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            imageVector = Icons.Default.Delete,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.secondary,
        )
        Text(
            text = AppTheme.strings.warningDialogTitle.string(),
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.headlineSmall,
        )
    }
}
