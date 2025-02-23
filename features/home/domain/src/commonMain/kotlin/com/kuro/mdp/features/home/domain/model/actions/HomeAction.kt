package com.kuro.mdp.features.home.domain.model.actions

import com.kuro.mdp.features.home.domain.model.schedules.ScheduleHome
import com.kuro.mdp.shared.domain.model.schedules.DailyScheduleStatus
import com.kuro.mdp.shared.domain.model.settings.TasksSettings
import com.kuro.mdp.shared.presentation.screenmodel.contract.BaseAction
import kotlinx.datetime.LocalDateTime

/**
 * Created by: minhthinh.h on 2/19/2025
 *
 * Description:
 */
sealed class HomeAction : BaseAction {
    data object Navigate : HomeAction()
    data class SetupSettings(val settings: TasksSettings) : HomeAction()
    data class UpdateSchedule(val schedule: ScheduleHome) : HomeAction()
    data class SetEmptySchedule(val date: LocalDateTime, val status: DailyScheduleStatus?) : HomeAction()
}
