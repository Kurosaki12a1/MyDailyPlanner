package com.kuro.mdp.charts.legend.data

import androidx.compose.ui.text.AnnotatedString
import com.kuro.mdp.charts.ChartShape

/**
 * Created by: minhthinh.h on 2/20/2025
 *
 * Description:
 */
data class LegendEntry(
    val text: AnnotatedString,
    val value: Float? = null,
    val percent: Float = Float.MAX_VALUE,
    val shape: ChartShape = ChartShape.Default
)
