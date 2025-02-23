package com.kuro.mdp.features.analytics.presentation.theme.resources

import analytics.resources.Res
import analytics.resources.allTimeTitle
import analytics.resources.averageCountTaskTitle
import analytics.resources.averageTimeTaskTitle
import analytics.resources.categoryStatisticsTitle
import analytics.resources.executedStatisticsTitle
import analytics.resources.halfYearTimePeriod
import analytics.resources.intelligenceTabTitle
import analytics.resources.menuIconDesc
import analytics.resources.monthTimePeriod
import analytics.resources.otherAnalyticsName
import analytics.resources.otherError
import analytics.resources.planningAnalyticsTitle
import analytics.resources.refreshAnalyticIconDesc
import analytics.resources.timeSelectorTitle
import analytics.resources.timeTabTitle
import analytics.resources.topAppBarAnalyticsTitle
import analytics.resources.totalCountTaskTitle
import analytics.resources.totalTimeTaskTitle
import analytics.resources.weekTimePeriod
import analytics.resources.workLoadAnalyticsTitle
import analytics.resources.workLoadTabTitle
import analytics.resources.yearTimePeriod
import androidx.compose.runtime.staticCompositionLocalOf
import org.jetbrains.compose.resources.StringResource

/**
 * Created by: minhthinh.h on 2/18/2025
 *
 * Description:
 */
internal data class AnalyticsStrings(
    val topAppBarTitle: StringResource,
    val menuIconDesc: StringResource,
    val timeTabTitle: StringResource,
    val workLoadTabTitle: StringResource,
    val intelligenceTabTitle: StringResource,
    val weekTimePeriod: StringResource,
    val monthTimePeriod: StringResource,
    val yearTimePeriod: StringResource,
    val halfYearTimePeriod: StringResource,
    val timeSelectorTitle: StringResource,
    val refreshAnalyticIconDesc: StringResource,
    val otherAnalyticsName: StringResource,
    val allTimeTitle: StringResource,
    val totalCountTaskTitle: StringResource,
    val totalTimeTaskTitle: StringResource,
    val averageCountTaskTitle: StringResource,
    val averageTimeTaskTitle: StringResource,
    val planningAnalyticsTitle: StringResource,
    val workLoadAnalyticsTitle: StringResource,
    val categoryStatisticsTitle: StringResource,
    val executedStatisticsTitle: StringResource,
    val otherError: StringResource
)

internal val baseAnalyticsStrings = AnalyticsStrings(
    topAppBarTitle = Res.string.topAppBarAnalyticsTitle,
    menuIconDesc = Res.string.menuIconDesc,
    timeTabTitle = Res.string.timeTabTitle,
    workLoadTabTitle = Res.string.workLoadTabTitle,
    intelligenceTabTitle = Res.string.intelligenceTabTitle,
    weekTimePeriod = Res.string.weekTimePeriod,
    monthTimePeriod = Res.string.monthTimePeriod,
    yearTimePeriod = Res.string.yearTimePeriod,
    halfYearTimePeriod = Res.string.halfYearTimePeriod,
    timeSelectorTitle = Res.string.timeSelectorTitle,
    refreshAnalyticIconDesc = Res.string.refreshAnalyticIconDesc,
    otherAnalyticsName = Res.string.otherAnalyticsName,
    allTimeTitle = Res.string.allTimeTitle,
    totalCountTaskTitle = Res.string.totalCountTaskTitle,
    totalTimeTaskTitle = Res.string.totalTimeTaskTitle,
    averageCountTaskTitle = Res.string.averageCountTaskTitle,
    averageTimeTaskTitle = Res.string.averageTimeTaskTitle,
    planningAnalyticsTitle = Res.string.planningAnalyticsTitle,
    workLoadAnalyticsTitle = Res.string.workLoadAnalyticsTitle,
    categoryStatisticsTitle = Res.string.categoryStatisticsTitle,
    executedStatisticsTitle = Res.string.executedStatisticsTitle,
    otherError = Res.string.otherError,
)

internal val LocalAnalyticsStrings = staticCompositionLocalOf<AnalyticsStrings> {
    error("Home Strings is not provided")
}

internal fun fetchAnalyticsString() = baseAnalyticsStrings