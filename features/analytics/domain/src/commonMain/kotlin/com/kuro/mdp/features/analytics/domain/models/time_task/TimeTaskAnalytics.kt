package com.kuro.mdp.features.analytics.domain.models.time_task

import com.kuro.mdp.features.analytics.domain.models.categories.MainCategoriesAnalytics
import com.kuro.mdp.features.analytics.domain.models.categories.SubCategoriesAnalytics
import com.kuro.mdp.shared.domain.model.schedules.TaskPriority
import com.kuro.mdp.shared.utils.functional.TimeRange
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

/**
 * Created by: minhthinh.h on 2/18/2025
 *
 * Description:
 */
@Serializable
data class TimeTaskAnalytics(
    val key: Long = 0L,
    val date: LocalDateTime,
    val timeRanges: TimeRange,
    val category: MainCategoriesAnalytics,
    val subCategory: SubCategoriesAnalytics? = null,
    val isCompleted: Boolean = true,
    val priority: TaskPriority = TaskPriority.STANDARD,
    val isEnableNotification: Boolean = true,
    val taskNotifications: TaskNotificationsAnalytics = TaskNotificationsAnalytics(),
    val isConsiderInStatistics: Boolean = true,
    val note: String? = null,
)