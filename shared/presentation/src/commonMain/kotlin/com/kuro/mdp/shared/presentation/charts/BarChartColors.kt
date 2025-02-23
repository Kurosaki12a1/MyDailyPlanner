package com.kuro.mdp.shared.presentation.charts

import androidx.compose.ui.graphics.Color

/**
 * Created by: minhthinh.h on 2/20/2025
 *
 * Description:
 */
object BarChartColorsDefaults {

    private val barChartColors = listOf(
        Color(0xFFFF7723),
        Color(0xFF0263FF),
        Color(0xFF8E30FF),
        Color(0xFF51A8B7),
        Color(0xFF7E6BA1),
        Color(0xFFC4E7D4),
        Color(0xFFD12137),
        Color(0xFF274210),
        Color(0xFF6582CC),
        Color(0xFF3237D8),
    )

    fun fetchColors() = barChartColors
}
