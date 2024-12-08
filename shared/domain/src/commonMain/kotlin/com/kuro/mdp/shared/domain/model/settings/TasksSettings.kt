package com.kuro.mdp.shared.domain.model.settings

import com.kuro.mdp.shared.utils.functional.TimePeriod

data class TasksSettings(
    val taskViewStatus: ViewToggleStatus = ViewToggleStatus.COMPACT,
    val taskAnalyticsRange: TimePeriod = TimePeriod.WEEK,
    val calendarButtonBehavior: CalendarButtonBehavior = CalendarButtonBehavior.SET_CURRENT_DATE,
    val secureMode: Boolean = false,
)
