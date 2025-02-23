package com.kuro.mdp.features.analytics.presentation.ui.analytics.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.kuro.mdp.charts.legend.data.LegendEntry
import kotlin.math.roundToInt

/**
 * Created by: minhthinh.h on 2/20/2025
 *
 * Description:
 */
@Composable
internal fun AnalyticsTimeLegend(
    modifier: Modifier = Modifier,
    scrollable: Boolean = false,
    legendEntries: List<LegendEntry>,
    selectedItem: Int,
    onSelectedItem: (Int) -> Unit,
) {
    if (scrollable) {
        val listState = rememberLazyListState()
        LazyColumn(
            modifier = modifier,
            state = listState,
        ) {
            items(6) { index ->
                val entry = legendEntries[index]
                AnalyticsTimeLegendItem(
                    modifier = Modifier.animateItem(fadeInSpec = null, fadeOutSpec = null),
                    isSelected = selectedItem == index,
                    categoryName = entry.text.text,
                    percent = entry.percent,
                    color = entry.shape.color,
                    onSelectedItem = { onSelectedItem(index) },
                )
            }
        }
    } else {
        Column {
            legendEntries.take(6).forEachIndexed { index, entry ->
                AnalyticsTimeLegendItem(
                    isSelected = selectedItem == index,
                    categoryName = entry.text.text,
                    percent = entry.percent,
                    color = entry.shape.color,
                    onSelectedItem = { onSelectedItem(index) },
                )
            }
        }
    }
}

@Composable
internal fun AnalyticsTimeLegendItem(
    modifier: Modifier = Modifier,
    isSelected: Boolean,
    categoryName: String,
    percent: Float,
    color: Color,
    onSelectedItem: () -> Unit,
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.small)
            .clickable { onSelectedItem() },
        shape = MaterialTheme.shapes.small,
        color = when (isSelected) {
            true -> MaterialTheme.colorScheme.surfaceContainerHigh
            false -> MaterialTheme.colorScheme.background
        }
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 4.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(modifier = Modifier.size(10.dp).clip(RoundedCornerShape(100.dp)).background(color))
            Text(
                modifier = Modifier.weight(1f),
                text = categoryName,
                color = MaterialTheme.colorScheme.onBackground,
                overflow = TextOverflow.Ellipsis,
                maxLines = if (isSelected) 2 else 1,
                style = MaterialTheme.typography.labelLarge,
            )
            Text(
                text = "${percent.roundToInt()}%",
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.labelMedium,
            )
        }
    }
}
