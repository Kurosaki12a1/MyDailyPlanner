package com.kuro.mdp.charts.pie.data

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString

/**
 * Created by: minhthinh.h on 2/20/2025
 *
 * Description:
 */
data class PieChartEntry(
    val value: Float,
    val label: AnnotatedString,
    val textDisplay: String,
    /**
     * Color of the pie slice and legend entry, if not provided [PieChartData.colors] will be used
     */
    val color: Color? = null,
)
