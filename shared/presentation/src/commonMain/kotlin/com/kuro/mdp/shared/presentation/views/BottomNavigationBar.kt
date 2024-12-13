package com.kuro.mdp.shared.presentation.views

import androidx.compose.foundation.basicMarquee
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import com.kuro.mdp.shared.presentation.theme.AppTheme
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

/**
 * Created by: minhthinh.h on 12/10/2024
 *
 * Description:
 */
@Composable
fun <Item : BottomBarItem> BottomNavigationBar(
    modifier: Modifier,
    selectedItem: String?,
    items: Array<Item>,
    showLabel: Boolean,
    onItemSelected: (Item) -> Unit
) {
    NavigationBar(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.background,
        tonalElevation = AppTheme.elevations.levelZero
    ) {
        items.forEach { item ->
            NavigationBarItem(
                selected = selectedItem == item.destination,
                onClick = { onItemSelected.invoke(item) },
                icon = {
                    BottomBarIcon(
                        selected = selectedItem == item.destination,
                        enabledIcon = painterResource(item.enabledIcon),
                        disabledIcon = painterResource(item.disabledIcon),
                        description = item.label
                    )
                },
                label = {
                    if (showLabel) {
                        BottomBarLabel(
                            selected = selectedItem == item.destination,
                            title = item.label
                        )
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        }
    }
}

@Composable
private fun BottomBarIcon(
    selected: Boolean,
    enabledIcon: Painter,
    disabledIcon: Painter,
    description: String
) {
    Icon(
        painter = if (selected) enabledIcon else disabledIcon,
        contentDescription = description,
        tint = when (selected) {
            true -> MaterialTheme.colorScheme.onSecondaryContainer
            false -> MaterialTheme.colorScheme.onSurfaceVariant
        }
    )
}

@Composable
private fun BottomBarLabel(
    selected: Boolean,
    title: String
) {
    Text(
        modifier = Modifier.basicMarquee(
            iterations = Int.MAX_VALUE,
            repeatDelayMillis = 2000
        ),
        text = title,
        color = when (selected) {
            true -> MaterialTheme.colorScheme.onSurface
            false -> MaterialTheme.colorScheme.onSurfaceVariant
        },
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        style = MaterialTheme.typography.labelMedium,
        fontWeight = if (selected) FontWeight.Bold else FontWeight.Medium
    )
}

interface BottomBarItem {
    val destination: String
    val label: String @Composable get
    val enabledIcon: DrawableResource
    val disabledIcon: DrawableResource
}