package com.kuro.mdp.features.home.presentation.ui.home.ui.home

import androidx.compose.runtime.Stable
import com.kuro.mdp.features.home.domain.model.HomeError
import com.kuro.mdp.features.home.domain.model.schedules.TimeTaskHome
import com.kuro.mdp.shared.domain.model.schedules.DailyScheduleStatus
import com.kuro.mdp.shared.domain.model.settings.CalendarButtonBehavior
import com.kuro.mdp.shared.domain.model.settings.ViewToggleStatus
import com.kuro.mdp.shared.presentation.screenmodel.contract.BaseViewState
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

/**
 * Created by: minhthinh.h on 12/20/2024
 *
 * Description:
 */

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
