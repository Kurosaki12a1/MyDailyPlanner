package com.kurp.mdp.shared.data.repository.common

import com.kuro.mdp.shared.domain.common.TimeTaskStatusChecker
import com.kuro.mdp.shared.domain.model.schedules.TimeTaskStatus
import com.kuro.mdp.shared.utils.functional.TimeRange
import kotlinx.datetime.LocalDateTime

class TimeTaskStatusCheckerImpl : TimeTaskStatusChecker {
    override fun fetchStatus(timeRange: TimeRange, currentDate: LocalDateTime): TimeTaskStatus {
        return if (currentDate.time > timeRange.from.time && currentDate.time < timeRange.to.time) {
            TimeTaskStatus.RUNNING
        } else if (currentDate.time > timeRange.to.time) {
            TimeTaskStatus.COMPLETED
        } else {
            TimeTaskStatus.PLANNED
        }
    }
}