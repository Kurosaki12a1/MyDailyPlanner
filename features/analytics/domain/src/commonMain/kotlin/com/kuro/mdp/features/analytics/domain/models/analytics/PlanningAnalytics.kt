package com.kuro.mdp.features.analytics.domain.models.analytics

import com.kuro.mdp.shared.domain.model.schedules.TimeTask
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

/**
 * Created by: minhthinh.h on 2/18/2025
 *
 * Description:
 */
@Serializable
data class PlanningAnalytics(
    val date: LocalDateTime,
    val timeTasks: List<TimeTask>
)
