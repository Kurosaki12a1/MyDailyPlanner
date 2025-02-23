package com.kuro.mdp.charts.table.data

import androidx.compose.ui.text.AnnotatedString
import com.kuro.mdp.charts.ChartShape

/**
 * Created by: minhthinh.h on 2/20/2025
 *
 * Description:
 */
data class TableEntry(
    val key: AnnotatedString?,
    val value: AnnotatedString?,
    val drawShape: ChartShape? = null,
)
