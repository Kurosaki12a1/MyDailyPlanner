package com.kuro.mdp.features.settings.domain.mapper.templates

import com.kuro.mdp.features.settings.domain.mapper.categories.mapToDomain
import com.kuro.mdp.features.settings.domain.mapper.categories.mapToUi
import com.kuro.mdp.features.settings.domain.model.template.TemplateUi
import com.kuro.mdp.shared.domain.model.schedules.TimeTask
import com.kuro.mdp.shared.domain.model.template.Template
import com.kuro.mdp.shared.utils.extensions.changeDay
import com.kuro.mdp.shared.utils.extensions.generateUniqueKey
import com.kuro.mdp.shared.utils.extensions.getLocalDateTimeNow
import com.kuro.mdp.shared.utils.extensions.isCurrentDay
import com.kuro.mdp.shared.utils.extensions.shiftDays
import com.kuro.mdp.shared.utils.functional.TimeRange
import kotlinx.datetime.LocalDateTime

fun TemplateUi.mapToDomain() = Template(
    templateId = templateId,
    startTime = startTime,
    endTime = endTime,
    category = category.mapToDomain(),
    subCategory = subCategory?.mapToDomain(),
    priority = priority,
    isEnableNotification = isEnableNotification,
    isConsiderInStatistics = isConsiderInStatistics,
    repeatEnabled = repeatEnabled,
    repeatTimes = repeatTimes,
)

fun Template.mapToUi() = TemplateUi(
    templateId = templateId,
    startTime = startTime,
    endTime = endTime,
    category = category.mapToUi(),
    subCategory = subCategory?.mapToUi(),
    priority = priority,
    isEnableNotification = isEnableNotification,
    isConsiderInStatistics = isConsiderInStatistics,
    repeatEnabled = repeatEnabled,
    repeatTimes = repeatTimes,
)

fun Template.convertToTimeTask(
    date: LocalDateTime,
    key: Long = generateUniqueKey(),
    createdAt: LocalDateTime? = getLocalDateTimeNow(),
) = TimeTask(
    key = key,
    date = date,
    timeRange = TimeRange(
        from = startTime.changeDay(date),
        to = if (endTime.isCurrentDay(startTime)) endTime.changeDay(date) else endTime.changeDay(date.shiftDays(1)),
    ),
    createdAt = createdAt,
    category = category,
    subCategory = subCategory,
    priority = priority,
    isEnableNotification = isEnableNotification,
    isConsiderInStatistics = isConsiderInStatistics,
)