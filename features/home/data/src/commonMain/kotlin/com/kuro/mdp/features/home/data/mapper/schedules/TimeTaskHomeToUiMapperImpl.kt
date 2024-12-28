package com.kuro.mdp.features.home.data.mapper.schedules

import com.kuro.mdp.features.home.domain.api.TimeTaskHomeToUiMapper
import com.kuro.mdp.features.home.domain.mapper.categories.mapToUi
import com.kuro.mdp.features.home.domain.mapper.schedules.mapToUi
import com.kuro.mdp.features.home.domain.model.schedules.TimeTaskHome
import com.kuro.mdp.shared.domain.common.TimeTaskStatusChecker
import com.kuro.mdp.shared.domain.model.schedules.TimeTask
import com.kuro.mdp.shared.utils.extensions.duration
import com.kuro.mdp.shared.utils.managers.DateManager

/**
 * Created by: minhthinh.h on 12/25/2024
 *
 * Description:
 */

class TimeTaskHomeToUiMapperImpl(
    private val dateManager: DateManager,
    private val statusManager: TimeTaskStatusChecker
) : TimeTaskHomeToUiMapper {
    override fun map(input: TimeTask, parameter: Boolean): TimeTaskHome {
        return TimeTaskHome(
            executionStatus = statusManager.fetchStatus(
                input.timeRange,
                dateManager.fetchCurrentDate(),
            ),
            key = input.key,
            date = input.date,
            startTime = input.timeRange.from,
            endTime = input.timeRange.to,
            createdAt = input.createdAt,
            duration = duration(input.timeRange),
            leftTime = dateManager.calculateLeftTime(input.timeRange.to),
            progress = dateManager.calculateProgress(input.timeRange.from, input.timeRange.to),
            mainCategory = input.category.mapToUi(),
            subCategory = input.subCategory?.mapToUi(),
            isCompleted = input.isCompleted,
            priority = input.priority,
            isEnableNotification = input.isEnableNotification,
            taskNotifications = input.taskNotifications.mapToUi(),
            isConsiderInStatistics = input.isConsiderInStatistics,
            isTemplate = parameter,
            note = input.note
        )
    }
}

