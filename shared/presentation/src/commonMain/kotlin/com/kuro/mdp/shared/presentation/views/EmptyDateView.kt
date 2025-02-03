package com.kuro.mdp.shared.presentation.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kuro.mdp.shared.presentation.theme.AppTheme
import org.jetbrains.compose.resources.painterResource

@Composable
fun EmptyDateView(
    modifier: Modifier = Modifier,
    emptyTitle: String,
    subTitle: String? = null,
    onActionButton: (@Composable () -> Unit)? = null,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            painter = painterResource(AppTheme.icons.notFound),
            contentDescription = AppTheme.strings.emptyScheduleTitle.toString(),
            tint = MaterialTheme.colorScheme.surfaceVariant,
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = emptyTitle,
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    style = MaterialTheme.typography.headlineSmall,
                )
                if (subTitle != null) {
                    Text(
                        text = subTitle,
                        color = MaterialTheme.colorScheme.outlineVariant,
                        style = MaterialTheme.typography.titleSmall,
                    )
                }
            }
            onActionButton?.invoke()
        }
    }
}