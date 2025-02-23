package com.kuro.mdp.features.home.presentation.ui.home.ui.home.contract

import androidx.compose.runtime.Stable
import com.kuro.mdp.features.home.domain.model.HomeError
import com.kuro.mdp.features.home.domain.model.schedules.TimeTaskHome
import com.kuro.mdp.shared.domain.model.schedules.DailyScheduleStatus
import com.kuro.mdp.shared.domain.model.settings.CalendarButtonBehavior
import com.kuro.mdp.shared.domain.model.settings.ViewToggleStatus
import com.kuro.mdp.shared.presentation.screenmodel.contract.BaseEvent
import com.kuro.mdp.shared.presentation.screenmodel.contract.BaseViewState
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

/**
 * Created by: minhthinh.h on 2/19/2025
 *
 * Description:
 */
@Serializable
sealed class HomeEvent : BaseEvent {
    data object Init : HomeEvent()
    data object CreateSchedule : HomeEvent()
    data object PressOverviewButton : HomeEvent()
    data object ClearFailure : HomeEvent()
    data class LoadSchedule(val date: LocalDateTime?) : HomeEvent()
    data class PressAddTimeTaskButton(val startTime: LocalDateTime, val endTime: LocalDateTime) : HomeEvent()
    data class PressEditTimeTaskButton(val timeTask: TimeTaskHome) : HomeEvent()
    data class ChangeTaskDoneStateButton(val timeTask: TimeTaskHome) : HomeEvent()
    data class TimeTaskShiftUp(val timeTask: TimeTaskHome) : HomeEvent()
    data class TimeTaskShiftDown(val timeTask: TimeTaskHome) : HomeEvent()
    data class PressViewToggleButton(val status: ViewToggleStatus) : HomeEvent()
    data class SetShowDialog(val isShow: Boolean) : HomeEvent()
}

@Serializable
@Stable
data class HomeViewState(
    val currentDate: LocalDateTime? = null,
    val dateStatus: DailyScheduleStatus? = null,
    val taskViewStatus: ViewToggleStatus = ViewToggleStatus.COMPACT,
    val calendarButtonBehavior: CalendarButtonBehavior = CalendarButtonBehavior.SET_CURRENT_DATE,
    val timeTasks: List<TimeTaskHome> = emptyList(),
    val error: HomeError? = null
) : BaseViewState