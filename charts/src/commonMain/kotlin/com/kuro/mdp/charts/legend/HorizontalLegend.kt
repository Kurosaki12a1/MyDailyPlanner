package com.kuro.mdp.charts.legend

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastForEach
import com.kuro.mdp.charts.common.DefaultText
import com.kuro.mdp.charts.legend.data.LegendEntry
import com.kuro.mdp.shared.presentation.views.FlowRow

/**
 * Created by: minhthinh.h on 2/20/2025
 *
 * Description:
 */
@Composable
fun HorizontalLegend(
    legendEntries: List<LegendEntry>,
    text: @Composable (item: LegendEntry) -> Unit = { DefaultText(text = it.text) },
) {
    FlowRow(
        mainAxisSpacing = 16.dp,
        crossAxisSpacing = 8.dp,
    ) {
        legendEntries.fastForEach { item ->
            key(item.hashCode()) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .requiredSize(item.shape.size)
                            .background(item.shape.color, item.shape.shape)
                    )

                    Spacer(modifier = Modifier.requiredSize(8.dp))

                    text(item)
                }
            }
        }
    }
}
