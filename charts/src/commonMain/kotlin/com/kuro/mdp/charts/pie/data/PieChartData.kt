package com.kuro.mdp.charts.pie.data

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import com.kuro.mdp.charts.legend.data.LegendPosition

/**
 * Created by: minhthinh.h on 2/20/2025
 *
 * Description:
 */
data class PieChartData(
    val entries: List<PieChartEntry>,
    val colors: List<Color> = emptyList(),
    val legendPosition: LegendPosition = LegendPosition.Bottom,
    val legendShape: Shape = CircleShape,
    val animate: Boolean = true,
)
