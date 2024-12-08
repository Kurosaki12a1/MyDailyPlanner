package com.kurp.mdp.shared.data.mappers.template

import com.kuro.mdp.shared.domain.model.schedules.TaskPriority
import com.kuro.mdp.shared.domain.model.template.Template
import com.kuro.mdp.shared.utils.extensions.mapToDate
import com.kuro.mdp.shared.utils.extensions.toEpochMillis
import com.kurp.mdp.shared.data.entities.template.TemplateCompound
import com.kurp.mdp.shared.data.entities.template.TemplateDetails
import com.kurp.mdp.shared.data.entities.template.TemplateEntity
import com.kurp.mdp.shared.data.mappers.categories.mapToDomain

fun TemplateDetails.mapToDomain() = Template(
    startTime = template.startTime.mapToDate(),
    endTime = template.endTime.mapToDate(),
    category = mainCategory.mapToDomain(),
    subCategory = subCategory?.mapToDomain(mainCategory.mapToDomain()),
    priority = when {
        template.isImportantMax -> TaskPriority.MAX
        template.isImportantMedium -> TaskPriority.MEDIUM
        else -> TaskPriority.STANDARD
    },
    isEnableNotification = template.isEnableNotification,
    isConsiderInStatistics = template.isConsiderInStatistics,
    templateId = template.id,
    repeatEnabled = template.repeatEnabled,
    repeatTimes = repeatTime.let { list ->
        list.map { repeatTimeEntity -> repeatTimeEntity.mapToDomain() }
    },
)

fun Template.mapToData() = TemplateCompound(
    template = TemplateEntity(
        id = templateId,
        startTime = startTime.toEpochMillis(),
        endTime = endTime.toEpochMillis(),
        categoryId = category.id,
        subCategoryId = subCategory?.id,
        isImportantMedium = priority == TaskPriority.MEDIUM,
        isImportantMax = priority == TaskPriority.MAX,
        isEnableNotification = isEnableNotification,
        isConsiderInStatistics = isConsiderInStatistics,
        repeatEnabled = repeatEnabled,
    ),
    repeatTimes = repeatTimes.map {
        it.mapToData(templateId)
    },
)
