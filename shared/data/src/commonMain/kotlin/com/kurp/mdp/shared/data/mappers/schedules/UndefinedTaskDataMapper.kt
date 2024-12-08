package com.kurp.mdp.shared.data.mappers.schedules

import com.kuro.mdp.shared.domain.model.schedules.TaskPriority
import com.kuro.mdp.shared.domain.model.schedules.UndefinedTask
import com.kuro.mdp.shared.utils.extensions.mapToDate
import com.kuro.mdp.shared.utils.extensions.toEpochMillis
import com.kurp.mdp.shared.data.entities.tasks.UndefinedTaskDetails
import com.kurp.mdp.shared.data.entities.tasks.UndefinedTaskEntity
import com.kurp.mdp.shared.data.mappers.categories.mapToDomain

fun UndefinedTaskDetails.mapToDomain() = UndefinedTask(
    id = task.key,
    createdAt = task.createdAt?.mapToDate(),
    deadline = task.deadline?.mapToDate(),
    mainCategory = mainCategory.mapToDomain(),
    subCategory = subCategory?.mapToDomain(mainCategory.mapToDomain()),
    priority = when {
        task.isImportantMax -> TaskPriority.MAX
        task.isImportantMedium -> TaskPriority.MEDIUM
        else -> TaskPriority.STANDARD
    },
    note = task.note,
)

fun UndefinedTask.mapToData() = UndefinedTaskEntity(
    key = id,
    createdAt = createdAt?.toEpochMillis(),
    deadline = deadline?.toEpochMillis(),
    mainCategoryId = mainCategory.id,
    subCategoryId = subCategory?.id,
    isImportantMedium = priority == TaskPriority.MEDIUM,
    isImportantMax = priority == TaskPriority.MAX,
    note = note,
)
