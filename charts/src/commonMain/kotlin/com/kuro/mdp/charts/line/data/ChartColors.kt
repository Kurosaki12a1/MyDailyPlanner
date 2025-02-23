package com.kuro.mdp.charts.line.data

import androidx.compose.ui.graphics.Color

/**
 * Created by: minhthinh.h on 2/20/2025
 *
 * Description:
 */
data class ChartColors(
    val axis: Color,
    val background: Color,
    val xLabel: Color,
    val yLabel: Color,
    val horizontalLines: Color,
    val drillDownLine: Color,
) {

    companion object {
        fun defaultColors(
            axis: Color = Color.Black,
            background: Color = Color.Transparent,
            yLabel: Color = Color(0xFF909BAA),
            xLabel: Color = Color(0xFF909BAA),
            horizontalLines: Color = Color(0xFF233F53),
            drillDownLines: Color = Color.Black
        ): ChartColors = ChartColors(
            axis = axis,
            background = background,
            xLabel = xLabel,
            yLabel = yLabel,
            horizontalLines = horizontalLines,
            drillDownLine = drillDownLines
        )
    }
}
