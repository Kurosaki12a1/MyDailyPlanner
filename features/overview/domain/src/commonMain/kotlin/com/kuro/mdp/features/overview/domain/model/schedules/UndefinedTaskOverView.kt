package com.kuro.mdp.features.overview.domain.model.schedules

import com.kuro.mdp.features.overview.domain.model.categories.MainCategoryOverView
import com.kuro.mdp.features.overview.domain.model.categories.SubCategoryOverView
import com.kuro.mdp.shared.domain.model.schedules.TaskPriority
import com.kuro.mdp.shared.domain.model.schedules.TimeTaskStatus
import com.kuro.mdp.shared.utils.functional.TimeRange
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

/**
 * Created by: minhthinh.h on 12/19/2024
 *
 * Description:
 */
@Serializable
data class UndefinedTaskOverView(
    val id: Long = 0L,
    val createdAt: LocalDateTime? = null,
    val deadline: LocalDateTime? = null,
    val mainCategory: MainCategoryOverView,
    val subCategory: SubCategoryOverView? = null,
    val priority: TaskPriority = TaskPriority.STANDARD,
    val note: String? = null,
)

fun UndefinedTaskOverView.convertToTimeTask(
    scheduleDate: LocalDateTime,
    timeRange: TimeRange,
) = TimeTaskOverView(
    executionStatus = TimeTaskStatus.PLANNED,
    date = scheduleDate,
    startTime = timeRange.from,
    endTime = timeRange.to,
    createdAt = createdAt,
    mainCategory = mainCategory,
    subCategory = subCategory,
    priority = priority,
    note = note,
)
