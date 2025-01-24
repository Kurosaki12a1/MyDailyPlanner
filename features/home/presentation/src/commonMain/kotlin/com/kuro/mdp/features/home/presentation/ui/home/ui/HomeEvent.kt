package com.kuro.mdp.features.home.presentation.ui.home.ui

import com.kuro.mdp.features.home.domain.model.schedules.TimeTaskHome
import com.kuro.mdp.shared.domain.model.settings.ViewToggleStatus
import com.kuro.mdp.shared.presentation.screenmodel.contract.BaseEvent
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

/**
 * Created by: minhthinh.h on 12/20/2024
 *
 * Description:
 */
@Serializable
sealed class HomeEvent : BaseEvent {
    data class Init(val scheduleDate: LocalDateTime? = null) : HomeEvent()
    data object CreateSchedule : HomeEvent()
    data object PressOverviewButton : HomeEvent()
    data class LoadSchedule(val date: LocalDateTime?) : HomeEvent()
    data class PressAddTimeTaskButton(val startTime: LocalDateTime, val endTime: LocalDateTime) : HomeEvent()
    data class PressEditTimeTaskButton(val timeTask: TimeTaskHome) : HomeEvent()
    data class ChangeTaskDoneStateButton(val timeTask: TimeTaskHome) : HomeEvent()
    data class TimeTaskShiftUp(val timeTask: TimeTaskHome) : HomeEvent()
    data class TimeTaskShiftDown(val timeTask: TimeTaskHome) : HomeEvent()
    data class PressViewToggleButton(val status: ViewToggleStatus) : HomeEvent()
    data class SetShowDialog(val isShow: Boolean) : HomeEvent()
}