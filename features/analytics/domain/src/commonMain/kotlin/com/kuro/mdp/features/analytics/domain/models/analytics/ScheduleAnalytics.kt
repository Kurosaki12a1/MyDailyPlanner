package com.kuro.mdp.features.analytics.domain.models.analytics

import com.kuro.mdp.shared.domain.model.schedules.TimeTask
import com.kuro.mdp.shared.utils.functional.TimeRange
import kotlinx.serialization.Serializable

/**
 * Created by: minhthinh.h on 2/18/2025
 *
 * Description:
 */
@Serializable
data class ScheduleAnalytics(
    val dateWorkLoadMap: Map<TimeRange, List<TimeTask>>,
    val categoriesAnalytics: List<CategoryAnalytics>,
    val planningAnalytics: Map<Int, List<PlanningAnalytics>>,
    val totalTasksCount: Int,
    val totalTasksTime: Long,
    val averageDayLoad: Int,
    val averageTaskTime: Long,
)

