package com.kuro.mdp.features.home.domain.common

import com.kuro.mdp.shared.domain.model.editor.Editor
import com.kuro.mdp.shared.domain.model.schedules.TimeTask
import com.kuro.mdp.shared.domain.model.template.Template
import com.kuro.mdp.shared.utils.functional.TimeRange

/**
 * Created by: minhthinh.h on 2/7/2025
 *
 * Description:
 */
fun TimeTask.convertToEditModel(template: Template?, undefinedTaskId: Long?) = Editor(
    key = key,
    date = date,
    startTime = timeRange.from,
    endTime = timeRange.to,
    createdAt = createdAt,
    mainCategory = category,
    subCategory = subCategory,
    priority = priority,
    isCompleted = isCompleted,
    isEnableNotification = isEnableNotification,
    taskNotifications = taskNotifications,
    isConsiderInStatistics = isConsiderInStatistics,
    repeatEnabled = template?.repeatEnabled ?: false,
    templateId = template?.templateId,
    undefinedTaskId = undefinedTaskId,
    repeatTimes = template?.repeatTimes ?: emptyList(),
    note = note,
)

fun Editor.convertToTimeTask() = TimeTask(
    key = key,
    date = date,
    timeRange = TimeRange(startTime, endTime),
    createdAt = createdAt,
    category = mainCategory,
    subCategory = subCategory,
    isCompleted = isCompleted,
    priority = priority,
    isEnableNotification = isEnableNotification,
    taskNotifications = taskNotifications,
    isConsiderInStatistics = isConsiderInStatistics,
    note = note,
)
