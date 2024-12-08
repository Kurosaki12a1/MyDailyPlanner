package com.kuro.mdp.shared.domain.common

import com.kuro.mdp.shared.domain.model.schedules.DailyScheduleStatus
import kotlinx.datetime.LocalDateTime

interface ScheduleStatusChecker {
    fun fetchState(requiredDate: LocalDateTime, currentDate: LocalDateTime): DailyScheduleStatus
}