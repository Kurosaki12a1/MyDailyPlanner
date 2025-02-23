package com.kuro.mdp.shared.presentation.charts

import androidx.compose.ui.graphics.Color

/**
 * Created by: minhthinh.h on 2/20/2025
 *
 * Description:
 */
data class PieChartColors(
    val topOne: Color = Color(0xFFC71CCB),
    val topTwo: Color = Color(0xFFF8B32D),
    val topThree: Color = Color(0xFFFFE600),
    val topFour: Color = Color(0xFF9ABD10),
    val topFive: Color = Color(0xFF1DD79D),
    val other: Color = Color(0xFF00A3FF),
) {
    fun fetchColorByTopIndex(topIndex: Int) = when (topIndex) {
        0 -> topOne
        1 -> topTwo
        2 -> topThree
        3 -> topFour
        4 -> topFive
        else -> other
    }

    companion object {
        fun default() = PieChartColors()
    }
}

fun fetchPieColorByTop(index: Int): Color {
    val pieColors = PieChartColors.default()
    return pieColors.fetchColorByTopIndex(index)
}
