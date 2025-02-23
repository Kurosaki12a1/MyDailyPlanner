package com.kuro.mdp.features.overview.domain.model.details

import com.kuro.mdp.features.overview.domain.model.schedules.ScheduleOverView
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

/**
 * Created by: minhthinh.h on 2/18/2025
 *
 * Description:
 */

@Serializable
sealed class DetailsAction {
    data class UpdateSchedules(val date: LocalDateTime, val schedules: List<ScheduleOverView>) : DetailsAction()
    data class UpdateLoading(val isLoading: Boolean) : DetailsAction()
}
