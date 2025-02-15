package com.kuro.mdp.shared.presentation.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import com.kuro.mdp.shared.presentation.extensions.placeholder
import com.kuro.mdp.shared.presentation.extensions.shimmer
import com.kuro.mdp.shared.presentation.model.PlaceHolderHighlight

/**
 * Created by: minhthinh.h on 1/21/2025
 *
 * Description:
 */
@Composable
fun PlaceHolderBox(
    modifier: Modifier = Modifier,
    shape: Shape = MaterialTheme.shapes.medium,
    color: Color = MaterialTheme.colorScheme.surfaceContainerHigh,
) = Box(
    modifier = modifier.fillMaxWidth().placeholder(
        visible = true,
        color = color,
        shape = shape,
        highlight = PlaceHolderHighlight.shimmer(
            highlightColor = MaterialTheme.colorScheme.highlightColorFor(color),
        ),
    ),
)

fun ColorScheme.highlightColorFor(mainColor: Color) = when (mainColor) {
    primary -> primaryContainer
    secondary -> secondaryContainer
    tertiary -> tertiaryContainer
    background -> surfaceContainer
    error -> errorContainer
    primaryContainer -> primary
    secondaryContainer -> secondary
    tertiaryContainer -> tertiary
    errorContainer -> error
    inverseSurface -> surfaceContainer
    surface -> surfaceContainer
    surfaceVariant -> surface
    surfaceBright -> surfaceVariant
    surfaceContainer -> surfaceContainerHighest
    surfaceContainerHigh -> surfaceContainerLow
    surfaceContainerHighest -> surfaceContainer
    surfaceContainerLow -> surfaceContainerHigh
    surfaceContainerLowest -> surfaceVariant
    else -> Color.Unspecified
}
