package com.kuro.mdp.shared.presentation.views

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.kuro.mdp.shared.presentation.theme.AppTheme
import org.jetbrains.compose.resources.painterResource

/**
 * Created by: minhthinh.h on 12/19/2024
 *
 * Description:
 */
@Composable
fun ExpandedIcon(
    modifier: Modifier = Modifier,
    isExpanded: Boolean,
    color: Color = MaterialTheme.colorScheme.onSurface,
    description: String? = null,
) {
    val icon = when (isExpanded) {
        true -> painterResource(AppTheme.icons.arrowUp)
        false -> painterResource(AppTheme.icons.arrowDown)
    }
    Box(modifier = modifier.animateContentSize()) {
        Icon(
            painter = icon,
            contentDescription = description,
            tint = color,
        )
    }
}
