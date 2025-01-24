package com.kuro.mdp.features.overview.data.mapper.schedule

import com.kuro.mdp.features.overview.domain.api.TimeTaskOverViewToUiMapper
import com.kuro.mdp.features.overview.domain.mapper.categories.mapToUi
import com.kuro.mdp.features.overview.domain.mapper.schedules.mapToUi
import com.kuro.mdp.features.overview.domain.model.schedules.TimeTaskOverView
import com.kuro.mdp.shared.domain.common.TimeTaskStatusChecker
import com.kuro.mdp.shared.domain.model.schedules.TimeTask
import com.kuro.mdp.shared.utils.extensions.duration
import com.kuro.mdp.shared.utils.managers.DateManager

/**
 * Created by: minhthinh.h on 1/22/2025
 *
 * Description:
 */
class TimeTaskOverViewToUiMapperImpl(
    private val dateManager: DateManager,
    private val statusManager: TimeTaskStatusChecker
) : TimeTaskOverViewToUiMapper {
    override fun map(input: TimeTask, parameter: Boolean): TimeTaskOverView {
        return TimeTaskOverView(
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


