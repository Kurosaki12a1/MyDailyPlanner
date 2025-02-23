package com.kuro.mdp.features.overview.presentation.ui.overview

import com.kuro.mdp.features.overview.domain.model.schedules.UndefinedTaskOverView
import com.kuro.mdp.shared.presentation.screenmodel.contract.BaseEvent
import kotlinx.datetime.LocalDateTime

/**
 * Created by: minhthinh.h on 1/21/2025
 *
 * Description:
 */
sealed class OverViewEvent : BaseEvent {
    data object Init : OverViewEvent()
    data object Refresh : OverViewEvent()

    //   data object PressScheduleButton : OverViewEvent()
    data object OpenAllSchedules : OverViewEvent()
    data class OpenSchedule(val scheduleDate: LocalDateTime?) : OverViewEvent()
    data class CreateOrUpdateUndefinedTask(val task: UndefinedTaskOverView) : OverViewEvent()
    data class ExecuteUndefinedTask(val scheduleDate: LocalDateTime, val task: UndefinedTaskOverView) : OverViewEvent()
    data class DeleteUndefinedTask(val task: UndefinedTaskOverView) : OverViewEvent()
}