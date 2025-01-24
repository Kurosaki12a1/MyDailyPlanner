package com.kuro.mdp.features.overview.domain.model.schedules

import com.kuro.mdp.shared.domain.model.schedules.DailyScheduleStatus
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

/**
 * Created by: minhthinh.h on 12/19/2024
 *
 * Description:
 */
@Serializable
data class ScheduleOverView(
    val date: LocalDateTime,
    val dateStatus: DailyScheduleStatus,
    val timeTasks: List<TimeTaskOverView>,
    val progress: Float,
)
