package com.kurp.mdp.shared.data.mappers.schedules

import com.kuro.mdp.shared.domain.model.schedules.TaskNotifications
import com.kuro.mdp.shared.domain.model.schedules.TaskPriority
import com.kuro.mdp.shared.domain.model.schedules.TimeTask
import com.kuro.mdp.shared.utils.extensions.isCurrentDay
import com.kuro.mdp.shared.utils.extensions.mapToDate
import com.kuro.mdp.shared.utils.extensions.shiftDays
import com.kuro.mdp.shared.utils.extensions.toEpochMillis
import com.kuro.mdp.shared.utils.functional.TimeRange
import com.kurp.mdp.shared.data.entities.tasks.TimeTaskDetails
import com.kurp.mdp.shared.data.entities.tasks.TimeTaskEntity
import com.kurp.mdp.shared.data.mappers.categories.mapToDomain

fun TimeTaskDetails.mapToDomain() = TimeTask(
    key = timeTask.key,
    date = timeTask.dailyScheduleDate.mapToDate(),
    timeRange = TimeRange(timeTask.startTime.mapToDate(), timeTask.endTime.mapToDate()),
    createdAt = timeTask.createdAt?.mapToDate(),
    category = mainCategory.mainCategory.mapToDomain(),
    subCategory = subCategory?.mapToDomain(mainCategory.mainCategory.mapToDomain()),
    isCompleted = timeTask.isCompleted,
    priority = when {
        timeTask.isImportantMax -> TaskPriority.MAX
        timeTask.isImportantMedium -> TaskPriority.MEDIUM
        else -> TaskPriority.STANDARD
    },
    isEnableNotification = timeTask.isEnableNotification,
    taskNotifications = TaskNotifications(
        fifteenMinutesBefore = timeTask.fifteenMinutesBeforeNotify,
        oneHourBefore = timeTask.oneHourBeforeNotify,
        threeHourBefore = timeTask.threeHourBeforeNotify,
        oneDayBefore = timeTask.oneDayBeforeNotify,
        oneWeekBefore = timeTask.oneWeekBeforeNotify,
        beforeEnd = timeTask.beforeEndNotify,
    ),
    isConsiderInStatistics = timeTask.isConsiderInStatistics,
    note = timeTask.note
)

fun TimeTask.mapToData() = TimeTaskEntity(
    key = key,
    dailyScheduleDate = date.toEpochMillis(),
    nextScheduleDate = if (timeRange.to.isCurrentDay(date)) null else date.shiftDays(1).toEpochMillis(),
    startTime = timeRange.from.toEpochMillis(),
    endTime = timeRange.to.toEpochMillis(),
    createdAt = createdAt?.toEpochMillis(),
    mainCategoryId = category.id,
    subCategoryId = subCategory?.id,
    isCompleted = isCompleted,
    isImportantMedium = priority == TaskPriority.MEDIUM,
    isImportantMax = priority == TaskPriority.MAX,
    isEnableNotification = isEnableNotification,
    fifteenMinutesBeforeNotify = taskNotifications.fifteenMinutesBefore,
    oneHourBeforeNotify = taskNotifications.oneHourBefore,
    threeHourBeforeNotify = taskNotifications.threeHourBefore,
    oneWeekBeforeNotify = taskNotifications.oneWeekBefore,
    oneDayBeforeNotify = taskNotifications.oneDayBefore,
    beforeEndNotify = taskNotifications.beforeEnd,
    isConsiderInStatistics = isConsiderInStatistics,
    note = note
)
