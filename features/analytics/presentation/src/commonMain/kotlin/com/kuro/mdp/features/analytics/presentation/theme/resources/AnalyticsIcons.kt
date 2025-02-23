package com.kuro.mdp.features.analytics.presentation.theme.resources

import androidx.compose.runtime.staticCompositionLocalOf
import org.jetbrains.compose.resources.DrawableResource
import shared.resources.Res
import shared.resources.ic_bar_chart
import shared.resources.ic_list_numbered
import shared.resources.ic_numeric_1_circle
import shared.resources.ic_pie_chart
import shared.resources.ic_time_complete
import shared.resources.ic_timer_check

/**
 * Created by: minhthinh.h on 2/18/2025
 *
 * Description:
 */
internal data class AnalyticsIcons(
    val barChart: DrawableResource,
    val pieChart: DrawableResource,
    val numberedList: DrawableResource,
    val numericOneCircle: DrawableResource,
    val timeCheck: DrawableResource,
    val timeComplete: DrawableResource,
)

internal val baseAnalyticsIcons = AnalyticsIcons(
    barChart = Res.drawable.ic_bar_chart,
    pieChart = Res.drawable.ic_pie_chart,
    numberedList = Res.drawable.ic_list_numbered,
    numericOneCircle = Res.drawable.ic_numeric_1_circle,
    timeCheck = Res.drawable.ic_timer_check,
    timeComplete = Res.drawable.ic_time_complete,
)

internal val LocalAnalyticsIcons = staticCompositionLocalOf<AnalyticsIcons> {
    error("Analytics Icons is not provided")
}

internal fun fetchAnalyticsIcons() = baseAnalyticsIcons
