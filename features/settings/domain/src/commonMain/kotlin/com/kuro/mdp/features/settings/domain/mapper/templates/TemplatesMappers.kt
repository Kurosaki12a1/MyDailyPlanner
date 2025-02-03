package com.kuro.mdp.features.settings.domain.mapper.templates

import com.kuro.mdp.features.settings.domain.mapper.categories.mapToDomain
import com.kuro.mdp.features.settings.domain.mapper.categories.mapToUi
import com.kuro.mdp.features.settings.domain.model.template.TemplateUi
import com.kuro.mdp.shared.domain.model.template.Template

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
