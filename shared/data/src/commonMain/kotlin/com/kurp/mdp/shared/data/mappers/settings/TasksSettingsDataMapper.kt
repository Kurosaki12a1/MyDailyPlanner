package com.kurp.mdp.shared.data.mappers.settings

import com.kuro.mdp.shared.domain.model.settings.CalendarButtonBehavior
import com.kuro.mdp.shared.domain.model.settings.TasksSettings
import com.kuro.mdp.shared.domain.model.settings.ViewToggleStatus
import com.kuro.mdp.shared.utils.functional.TimePeriod
import com.kurp.mdp.shared.data.entities.settings.TasksSettingsEntity

fun TasksSettings.mapToData() = TasksSettingsEntity(
    id = 0,
    taskViewStatus = taskViewStatus.toString(),
    taskAnalyticsRange = taskAnalyticsRange.toString(),
    calendarButtonBehavior = calendarButtonBehavior.toString(),
    secureMode = secureMode,
)

fun TasksSettingsEntity.mapToDomain() = TasksSettings(
    taskViewStatus = ViewToggleStatus.valueOf(taskViewStatus),
    taskAnalyticsRange = TimePeriod.valueOf(taskAnalyticsRange),
    calendarButtonBehavior = CalendarButtonBehavior.valueOf(calendarButtonBehavior),
    secureMode = secureMode,
)
