package com.kuro.mdp.features.home.domain.common

import com.kuro.mdp.shared.domain.model.editor.Editor
import com.kuro.mdp.shared.domain.model.template.Template
import com.kuro.mdp.shared.utils.extensions.changeDay
import com.kuro.mdp.shared.utils.extensions.getLocalDateTimeNow
import com.kuro.mdp.shared.utils.extensions.isCurrentDay
import com.kuro.mdp.shared.utils.extensions.shiftDays
import kotlinx.datetime.LocalDateTime

/**
 * Created by: minhthinh.h on 2/7/2025
 *
 * Description:
 */
fun Template.convertToEditModel(date: LocalDateTime) = Editor(
    date = date,
    startTime = startTime.changeDay(date),
    endTime = if (endTime.isCurrentDay(startTime)) endTime.changeDay(date) else endTime.changeDay(date.shiftDays(1)),
    createdAt = getLocalDateTimeNow(),
    mainCategory = category,
    subCategory = subCategory,
    priority = priority,
    isEnableNotification = isEnableNotification,
    isConsiderInStatistics = isConsiderInStatistics,
    repeatTimes = repeatTimes,
    templateId = templateId,
)

fun Editor.convertToTemplate(id: Int = 0) = Template(
    templateId = id,
    startTime = startTime,
    endTime = endTime,
    category = mainCategory,
    subCategory = subCategory,
    priority = priority,
    isEnableNotification = isEnableNotification,
    isConsiderInStatistics = isConsiderInStatistics,
    repeatTimes = repeatTimes,
)
