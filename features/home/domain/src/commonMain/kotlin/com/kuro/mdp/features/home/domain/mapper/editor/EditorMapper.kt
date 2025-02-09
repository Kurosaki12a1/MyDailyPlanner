package com.kuro.mdp.features.home.domain.mapper.editor

import com.kuro.mdp.features.home.domain.mapper.categories.mapToDomain
import com.kuro.mdp.features.home.domain.mapper.categories.mapToUi
import com.kuro.mdp.features.home.domain.model.editor.EditModelHome
import com.kuro.mdp.features.home.domain.model.editor.EditParameters
import com.kuro.mdp.features.home.domain.model.editor.TaskNotificationsHome
import com.kuro.mdp.shared.domain.model.editor.Editor
import com.kuro.mdp.shared.domain.model.schedules.TaskNotifications
import com.kuro.mdp.shared.utils.extensions.duration
import com.kuro.mdp.shared.utils.functional.TimeRange

/**
 * Created by: minhthinh.h on 2/7/2025
 *
 * Description:
 */
fun Editor.mapToUi() = EditModelHome(
    key = key,
    date = date,
    timeRange = TimeRange(startTime, endTime),
    createdAt = createdAt,
    duration = duration(startTime, endTime),
    mainCategory = mainCategory.mapToUi(),
    subCategory = subCategory?.mapToUi(),
    parameters = EditParameters(
        priority = priority,
        isEnableNotification = isEnableNotification,
        taskNotifications = taskNotifications.mapToUi(),
        isConsiderInStatistics = isConsiderInStatistics,
    ),
    isCompleted = isCompleted,
    repeatEnabled = repeatEnabled,
    templateId = templateId,
    undefinedTaskId = undefinedTaskId,
    repeatTimes = repeatTimes,
    note = note,
)

fun TaskNotifications.mapToUi() = TaskNotificationsHome(
    fifteenMinutesBefore = fifteenMinutesBefore,
    oneHourBefore = oneHourBefore,
    threeHourBefore = threeHourBefore,
    oneDayBefore = oneDayBefore,
    oneWeekBefore = oneWeekBefore,
    beforeEnd = beforeEnd,
)

fun EditModelHome.mapToDomain() = Editor(
    key = key,
    date = date,
    startTime = timeRange.from,
    endTime = timeRange.to,
    createdAt = createdAt,
    mainCategory = mainCategory.mapToDomain(),
    subCategory = subCategory?.mapToDomain(),
    isCompleted = isCompleted,
    priority = parameters.priority,
    isEnableNotification = parameters.isEnableNotification,
    taskNotifications = parameters.taskNotifications.mapToDomain(),
    isConsiderInStatistics = parameters.isConsiderInStatistics,
    repeatEnabled = repeatEnabled,
    templateId = templateId,
    undefinedTaskId = undefinedTaskId,
    repeatTimes = repeatTimes,
    note = note,
)

fun TaskNotificationsHome.mapToDomain() = TaskNotifications(
    fifteenMinutesBefore = fifteenMinutesBefore,
    oneHourBefore = oneHourBefore,
    threeHourBefore = threeHourBefore,
    oneDayBefore = oneDayBefore,
    oneWeekBefore = oneWeekBefore,
    beforeEnd = beforeEnd,
)
