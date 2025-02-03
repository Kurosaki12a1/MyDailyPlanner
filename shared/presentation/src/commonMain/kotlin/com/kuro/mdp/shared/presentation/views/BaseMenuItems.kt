package com.kuro.mdp.shared.presentation.views

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import com.kuro.mdp.shared.presentation.theme.AppTheme
import org.jetbrains.compose.resources.painterResource

@Composable
fun CheckedMenuItem(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    text: String,
    check: Boolean,
    onCheckedChange: (Boolean) -> Unit,
) = DropdownMenuItem(
    modifier = modifier.alpha(if (enabled) 1f else 0.6f),
    onClick = { onCheckedChange(!check) },
    enabled = enabled,
    leadingIcon = {
        Checkbox(
            modifier = Modifier.size(32.dp),
            enabled = enabled,
            checked = check,
            onCheckedChange = onCheckedChange,
        )
    },
    text = {
        Text(
            text = text,
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.titleMedium,
        )
    },
)

@Composable
fun BackMenuItem(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    title: String,
    onClick: () -> Unit,
) = DropdownMenuItem(
    modifier = modifier.alpha(if (enabled) 1f else 0.6f),
    enabled = enabled,
    onClick = onClick,
    leadingIcon = {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = title,
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    },
    text = {
        Text(
            text = title,
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.titleMedium,
        )
    },
)

@Composable
fun NavMenuItem(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    text: String,
    onClick: () -> Unit,
) = DropdownMenuItem(
    modifier = modifier.alpha(if (enabled) 1f else 0.6f),
    enabled = enabled,
    onClick = onClick,
    trailingIcon = {
        Icon(
            painter = painterResource(AppTheme.icons.menuNavArrow),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    },
    text = {
        Text(
            text = text,
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.titleMedium,
        )
    },
)
