package com.kuro.mdp.features.overview.domain.model.actions

import com.kuro.mdp.features.overview.domain.model.schedules.ScheduleOverView
import com.kuro.mdp.shared.presentation.screenmodel.contract.BaseAction
import kotlinx.datetime.LocalDateTime

/**
 * Created by: minhthinh.h on 2/18/2025
 *
 * Description:
 */

sealed class DetailsAction : BaseAction {
    data class UpdateSchedules(val date: LocalDateTime, val schedules: List<ScheduleOverView>) : DetailsAction()
    data class UpdateLoading(val isLoading: Boolean) : DetailsAction()
}
