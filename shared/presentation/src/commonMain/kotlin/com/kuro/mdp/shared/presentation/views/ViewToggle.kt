package com.kuro.mdp.shared.presentation.views

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kuro.mdp.shared.domain.model.settings.ViewToggleStatus
import com.kuro.mdp.shared.presentation.theme.AppTheme
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

/**
 * Created by: minhthinh.h on 12/19/2024
 *
 * Description:
 */
@Composable
fun ViewToggle(
    modifier: Modifier = Modifier,
    isHideTitle: Boolean = false,
    status: ViewToggleStatus,
    onStatusChange: (ViewToggleStatus) -> Unit,
) {
    val title = when (status) {
        ViewToggleStatus.EXPANDED -> AppTheme.strings.expandViewToggleTittle
        ViewToggleStatus.COMPACT -> AppTheme.strings.compactViewToggleTittle
    }
    val icon = when (status) {
        ViewToggleStatus.EXPANDED -> AppTheme.icons.expandedViewIcon
        ViewToggleStatus.COMPACT -> AppTheme.icons.compactViewIcon
    }
    TextButton(
        onClick = { onStatusChange(status) },
        modifier = modifier.height(40.dp),
    ) {
        BoxWithConstraints {
            if (maxWidth >= 129.dp && !isHideTitle) {
                Row {
                    Text(
                        text = stringResource(title),
                        color = MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.labelMedium,
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        painter = painterResource(icon),
                        contentDescription = stringResource(title),
                        tint = MaterialTheme.colorScheme.onBackground,
                    )
                }
            } else {
                Icon(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(icon),
                    contentDescription = stringResource(title),
                    tint = MaterialTheme.colorScheme.onBackground,
                )
            }
        }
    }
}