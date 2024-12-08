package com.kurp.mdp.shared.data.repository.common

import com.kuro.mdp.shared.domain.common.ScheduleStatusChecker
import com.kuro.mdp.shared.domain.model.schedules.DailyScheduleStatus
import kotlinx.datetime.LocalDateTime

class ScheduleStatusCheckerImpl : ScheduleStatusChecker {
    override fun fetchState(
        requiredDate: LocalDateTime,
        currentDate: LocalDateTime
    ): DailyScheduleStatus {
        return if (requiredDate.time > currentDate.time) {
            DailyScheduleStatus.PLANNED
        } else if (requiredDate.time < currentDate.time) {
            DailyScheduleStatus.REALIZED
        } else {
            DailyScheduleStatus.ACCOMPLISHMENT
        }
    }
}