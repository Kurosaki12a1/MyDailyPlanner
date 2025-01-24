package com.kuro.mdp.features.overview.domain.model.schedules

import com.kuro.mdp.features.overview.domain.model.categories.MainCategoryOverView
import com.kuro.mdp.features.overview.domain.model.categories.SubCategoryOverView
import com.kuro.mdp.shared.domain.model.schedules.TaskPriority
import com.kuro.mdp.shared.domain.model.schedules.TimeTaskStatus
import com.kuro.mdp.shared.utils.extensions.duration
import com.kuro.mdp.shared.utils.functional.TimeRange
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

/**
 * Created by: minhthinh.h on 12/19/2024
 *
 * Description:
 */
@Serializable
data class TimeTaskOverView(
    val key: Long = 0L,
    val executionStatus: TimeTaskStatus = TimeTaskStatus.PLANNED,
    val date: LocalDateTime,
    val startTime: LocalDateTime,
    val endTime: LocalDateTime,
    val createdAt: LocalDateTime? = null,
    val duration: Long = duration(startTime, endTime),
    val leftTime: Long = 0L,
    val progress: Float = 0f,
    val mainCategory: MainCategoryOverView,
    val subCategory: SubCategoryOverView? = null,
    val isCompleted: Boolean = true,
    val priority: TaskPriority = TaskPriority.STANDARD,
    val isEnableNotification: Boolean = true,
    val taskNotifications: TaskNotificationsOverView = TaskNotificationsOverView(),
    val isConsiderInStatistics: Boolean = true,
    val isTemplate: Boolean = false,
    val note: String? = null,
) {
    fun timeToTimeRange() = TimeRange(startTime, endTime)
}
