package com.kuro.mdp.shared.domain.common

import com.kuro.mdp.shared.domain.model.schedules.TimeTaskStatus
import com.kuro.mdp.shared.utils.functional.TimeRange
import kotlinx.datetime.LocalDateTime

interface TimeTaskStatusChecker {
    fun fetchStatus(timeRange: TimeRange, currentDate: LocalDateTime): TimeTaskStatus
}