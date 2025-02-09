package com.kuro.mdp.shared.presentation.views

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

/**
 * Created by: minhthinh.h on 2/7/2025
 *
 * Description:
 */
@Composable
fun NoneItemsView(
    modifier: Modifier = Modifier,
    background: Color = Color.Transparent,
    text: String,
    border: BorderStroke = BorderStroke(1.dp, MaterialTheme.colorScheme.surfaceVariant),
    color: Color = MaterialTheme.colorScheme.onSurfaceVariant,
    style: TextStyle = MaterialTheme.typography.labelLarge
) {
    Surface(
        modifier = modifier.fillMaxWidth().height(48.dp),
        shape = MaterialTheme.shapes.large,
        color = background,
        border = border,
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(
                text = text,
                color = color,
                maxLines = 1,
                style = style,
            )
        }
    }
}