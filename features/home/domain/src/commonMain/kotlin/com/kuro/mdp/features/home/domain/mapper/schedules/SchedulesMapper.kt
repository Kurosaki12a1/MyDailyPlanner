package com.kuro.mdp.features.home.domain.mapper.schedules

import com.kuro.mdp.features.home.domain.mapper.categories.mapToDomain
import com.kuro.mdp.features.home.domain.mapper.categories.mapToUi
import com.kuro.mdp.features.home.domain.model.schedules.ScheduleHome
import com.kuro.mdp.features.home.domain.model.schedules.TaskNotificationsHome
import com.kuro.mdp.features.home.domain.model.schedules.TimeTaskHome
import com.kuro.mdp.features.home.domain.model.schedules.UndefinedTaskHome
import com.kuro.mdp.shared.domain.model.schedules.Schedule
import com.kuro.mdp.shared.domain.model.schedules.TaskNotifications
import com.kuro.mdp.shared.domain.model.schedules.TimeTask
import com.kuro.mdp.shared.domain.model.schedules.UndefinedTask
import com.kuro.mdp.shared.utils.extensions.toEpochMillis
import com.kuro.mdp.shared.utils.functional.TimeRange

/**
 * Created by: minhthinh.h on 12/25/2024
 *
 * Description:
 */
fun TaskNotifications.mapToUi() = TaskNotificationsHome(
    fifteenMinutesBefore = fifteenMinutesBefore,
    oneHourBefore = oneHourBefore,
    threeHourBefore = threeHourBefore,
    oneDayBefore = oneDayBefore,
    oneWeekBefore = oneWeekBefore,
    beforeEnd = beforeEnd,
)

fun TimeTaskHome.mapToDomain() = TimeTask(
    key = key,
    date = date,
    timeRange = TimeRange(startTime, endTime),
    createdAt = createdAt,
    category = mainCategory.mapToDomain(),
    subCategory = subCategory?.mapToDomain(),
    priority = priority,
    isCompleted = isCompleted,
    isEnableNotification = isEnableNotification,
    taskNotifications = taskNotifications.mapToDomain(),
    isConsiderInStatistics = isConsiderInStatistics,
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


fun UndefinedTaskHome.mapToDomain() = UndefinedTask(
    id = id,
    createdAt = createdAt,
    deadline = deadline,
    mainCategory = mainCategory.mapToDomain(),
    subCategory = subCategory?.mapToDomain(),
    priority = priority,
    note = note,
)

fun UndefinedTask.mapToUi() = UndefinedTaskHome(
    id = id,
    createdAt = createdAt,
    deadline = deadline,
    mainCategory = mainCategory.mapToUi(),
    subCategory = subCategory?.mapToUi(),
    priority = priority,
    note = note,
)

fun ScheduleHome.mapToDomain() = Schedule(
    date = date.toEpochMillis(),
    status = dateStatus,
    timeTasks = timeTasks.map { it.mapToDomain() },
)

