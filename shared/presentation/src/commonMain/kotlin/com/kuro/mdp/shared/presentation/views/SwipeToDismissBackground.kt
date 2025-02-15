package com.kuro.mdp.shared.presentation.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwipeToDismissBoxState
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

/**
 * Created by: minhthinh.h on 2/10/2025
 *
 * Description:
 */
@Composable
@ExperimentalMaterial3Api
fun SwipeToDismissBackground(
    modifier: Modifier = Modifier,
    dismissState: SwipeToDismissBoxState,
    startToEndContent: @Composable (RowScope.() -> Unit)? = null,
    endToStartContent: @Composable (RowScope.() -> Unit)? = null,
    startToEndColor: Color? = null,
    endToStartColor: Color? = null,
    settledColor: Color = Color.Transparent,
    shape: Shape = MaterialTheme.shapes.large,
    contentPadding: PaddingValues = PaddingValues(12.dp, 8.dp),
    horizontalArrangement: Arrangement.Horizontal = Arrangement.spacedBy(12.dp),
) {
    val color = when (dismissState.dismissDirection) {
        SwipeToDismissBoxValue.StartToEnd -> startToEndColor ?: settledColor
        SwipeToDismissBoxValue.EndToStart -> endToStartColor ?: settledColor
        SwipeToDismissBoxValue.Settled -> settledColor
    }
    val contentColor = MaterialTheme.colorScheme.contentColorFor(color)
    val textStyle = MaterialTheme.typography.titleMedium

    CompositionLocalProvider(
        LocalContentColor provides contentColor,
        LocalTextStyle provides textStyle,
    ) {
        Row(
            modifier = modifier.fillMaxSize().clip(shape).background(color).padding(contentPadding),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = horizontalArrangement,
        ) {
            startToEndContent?.invoke(this)
            Spacer(modifier = Modifier.weight(1f))
            endToStartContent?.invoke(this)
        }
    }
}