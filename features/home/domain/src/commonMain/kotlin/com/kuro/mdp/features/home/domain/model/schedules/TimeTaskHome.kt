package com.kuro.mdp.features.home.domain.model.schedules

import com.kuro.mdp.features.home.domain.model.categories.MainCategoryHome
import com.kuro.mdp.features.home.domain.model.categories.SubCategoryHome
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
data class TimeTaskHome(
    val key: Long = 0L,
    val executionStatus: TimeTaskStatus = TimeTaskStatus.PLANNED,
    val date: LocalDateTime,
    val startTime: LocalDateTime,
    val endTime: LocalDateTime,
    val createdAt: LocalDateTime? = null,
    val duration: Long = duration(startTime, endTime),
    val leftTime: Long = 0L,
    val progress: Float = 0f,
    val mainCategory: MainCategoryHome,
    val subCategory: SubCategoryHome? = null,
    val isCompleted: Boolean = true,
    val priority: TaskPriority = TaskPriority.STANDARD,
    val isEnableNotification: Boolean = true,
    val taskNotifications: TaskNotificationsHome = TaskNotificationsHome(),
    val isConsiderInStatistics: Boolean = true,
    val isTemplate: Boolean = false,
    val note: String? = null,
) {
    fun timeToTimeRange() = TimeRange(startTime, endTime)
}
