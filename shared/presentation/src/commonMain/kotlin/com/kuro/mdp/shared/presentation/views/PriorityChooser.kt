package com.kuro.mdp.shared.presentation.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.kuro.mdp.shared.domain.model.schedules.TaskPriority
import com.kuro.mdp.shared.presentation.mappers.mapToString
import com.kuro.mdp.shared.presentation.theme.AppTheme
import com.kuro.mdp.shared.utils.extensions.string
import org.jetbrains.compose.resources.painterResource

@Composable
fun PriorityChooser(
    modifier: Modifier = Modifier,
    priority: TaskPriority,
    onPriorityChange: (TaskPriority) -> Unit,
) {
    var isOpenPriorityMenu by remember { mutableStateOf(false) }
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Icon(
            painter = painterResource(AppTheme.icons.priority),
            contentDescription = AppTheme.strings.priorityLabel.string(),
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        Text(
            modifier = Modifier.weight(1f),
            text = AppTheme.strings.priorityLabel.string(),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        Box(contentAlignment = Alignment.TopEnd) {
            SuggestionChip(
                label = { Text(text = priority.mapToString()) },
                onClick = { isOpenPriorityMenu = true },
            )
            PriorityMenu(
                isExpanded = isOpenPriorityMenu,
                selected = priority,
                onDismiss = { isOpenPriorityMenu = false },
                onChoose = { mainCategory ->
                    isOpenPriorityMenu = false
                    onPriorityChange(mainCategory)
                },
            )
        }
    }
}

@Composable
fun PriorityMenu(
    modifier: Modifier = Modifier,
    isExpanded: Boolean,
    selected: TaskPriority,
    onDismiss: () -> Unit,
    onChoose: (TaskPriority) -> Unit,
) {
    DropdownMenu(
        expanded = isExpanded,
        onDismissRequest = onDismiss,
        modifier = modifier.sizeIn(maxHeight = 200.dp),
        shape = MaterialTheme.shapes.large,
        offset = DpOffset(0.dp, 6.dp),
    ) {
        TaskPriority.entries.forEach { priority ->
            DropdownMenuItem(
                enabled = selected != priority,
                onClick = { onChoose(priority) },
                text = {
                    Text(
                        text = priority.mapToString(),
                        color = MaterialTheme.colorScheme.onSurface,
                        style = MaterialTheme.typography.titleMedium,
                    )
                },
            )
        }
    }
}