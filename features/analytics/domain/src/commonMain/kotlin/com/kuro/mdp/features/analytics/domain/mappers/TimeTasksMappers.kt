package com.kuro.mdp.features.analytics.domain.mappers

import com.kuro.mdp.features.analytics.domain.models.time_task.TaskNotificationsAnalytics
import com.kuro.mdp.features.analytics.domain.models.time_task.TimeTaskAnalytics
import com.kuro.mdp.shared.domain.model.schedules.TaskNotifications
import com.kuro.mdp.shared.domain.model.schedules.TimeTask

/**
 * Created by: minhthinh.h on 2/19/2025
 *
 * Description:
 */
fun TimeTask.mapToUI() = TimeTaskAnalytics(
    key = key,
    date = date,
    timeRanges = timeRange,
    category = category.mapToUi(),
    subCategory = subCategory?.mapToUi(),
    isCompleted = isCompleted,
    priority = priority,
    isEnableNotification = isEnableNotification,
    taskNotifications = taskNotifications.mapToUi(),
    isConsiderInStatistics = isConsiderInStatistics,
    note = note
)

fun TaskNotifications.mapToUi() = TaskNotificationsAnalytics(
    fifteenMinutesBefore = fifteenMinutesBefore,
    oneHourBefore = oneHourBefore,
    threeHourBefore = threeHourBefore,
    oneDayBefore = oneDayBefore,
    oneWeekBefore = oneWeekBefore,
    beforeEnd = beforeEnd
)