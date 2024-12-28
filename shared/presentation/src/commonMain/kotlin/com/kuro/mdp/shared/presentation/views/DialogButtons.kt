package com.kuro.mdp.shared.presentation.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.kuro.mdp.shared.presentation.theme.AppTheme
import com.kuro.mdp.shared.utils.extensions.string

/**
 * Created by: minhthinh.h on 12/26/2024
 *
 * Description:
 */
@Composable
fun DialogButtons(
    modifier: Modifier = Modifier,
    enabledConfirm: Boolean = true,
    confirmTitle: String = AppTheme.strings.confirmTitle.string(),
    onCancelClick: () -> Unit,
    onConfirmClick: () -> Unit,
) {
    Row(
        modifier = modifier.padding(top = 16.dp, bottom = 16.dp, end = 16.dp, start = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Spacer(modifier = Modifier.weight(1f))
        TextButton(onClick = onCancelClick) {
            Text(
                text = AppTheme.strings.cancelTitle.string(),
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.labelLarge,
            )
        }
        TextButton(enabled = enabledConfirm, onClick = onConfirmClick) {
            Text(
                text = confirmTitle,
                color = when (enabledConfirm) {
                    true -> MaterialTheme.colorScheme.primary
                    false -> MaterialTheme.colorScheme.onSurfaceVariant
                },
                style = MaterialTheme.typography.labelLarge,
            )
        }
    }
}

@Composable
fun DialogButtons(
    modifier: Modifier = Modifier,
    confirmFirstTitle: String,
    confirmSecondTitle: String,
    onCancelClick: () -> Unit,
    onConfirmFirstClick: () -> Unit,
    onConfirmSecondClick: () -> Unit,
) {
    Row(
        modifier = modifier.padding(top = 16.dp, bottom = 16.dp, end = 16.dp, start = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        TextButton(onClick = onCancelClick) {
            Text(
                text = AppTheme.strings.cancelTitle.string(),
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.labelLarge,
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        TextButton(onClick = onConfirmFirstClick) {
            Text(
                text = confirmFirstTitle,
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.labelLarge,
            )
        }
        TextButton(onClick = onConfirmSecondClick) {
            Text(
                text = confirmSecondTitle,
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.labelLarge,
            )
        }
    }
}

@Composable
fun DialogHeader(
    modifier: Modifier = Modifier,
    header: String,
    title: String? = null,
    paddingValues: PaddingValues = PaddingValues(top = 24.dp, bottom = 12.dp, start = 24.dp, end = 24.dp),
    headerColor: Color = MaterialTheme.colorScheme.onSurface,
    titleColor: Color = MaterialTheme.colorScheme.onSurfaceVariant,
) {
    Column(modifier = modifier.padding(paddingValues)) {
        Text(
            text = header,
            color = headerColor,
            style = MaterialTheme.typography.headlineSmall,
        )
        if (title != null) {
            Text(
                text = title,
                color = titleColor,
                style = MaterialTheme.typography.bodyMedium,
            )
        }
    }
}