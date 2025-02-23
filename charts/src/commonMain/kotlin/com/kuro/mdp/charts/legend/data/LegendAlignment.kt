package com.kuro.mdp.charts.legend.data

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.ui.Alignment

/**
 * Created by: minhthinh.h on 2/20/2025
 *
 * Description:
 */
enum class LegendAlignment {
    Start, Center, End;

    fun toVerticalArrangement(): Arrangement.Vertical =
        when (this) {
            Start -> Arrangement.Top
            End -> Arrangement.Bottom
            Center -> Arrangement.Center
        }

    fun toVerticalAlignment(): Alignment.Vertical =
        when (this) {
            Start -> Alignment.Top
            End -> Alignment.Bottom
            Center -> Alignment.CenterVertically
        }

    fun toHorizontalArrangement(): Arrangement.Horizontal =
        when (this) {
            Start -> Arrangement.Start
            End -> Arrangement.End
            Center -> Arrangement.Center
        }
}
