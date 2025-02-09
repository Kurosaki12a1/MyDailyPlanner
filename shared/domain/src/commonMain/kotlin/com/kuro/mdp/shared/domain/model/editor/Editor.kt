package com.kuro.mdp.shared.domain.model.editor

import com.kuro.mdp.shared.domain.model.categories.MainCategory
import com.kuro.mdp.shared.domain.model.categories.SubCategory
import com.kuro.mdp.shared.domain.model.schedules.TaskNotifications
import com.kuro.mdp.shared.domain.model.schedules.TaskPriority
import com.kuro.mdp.shared.domain.model.template.RepeatTime
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

/**
 * Created by: minhthinh.h on 2/7/2025
 *
 * Description:
 */
@Serializable
data class Editor(
    val key: Long = 0L,
    val date: LocalDateTime,
    val startTime: LocalDateTime,
    val endTime: LocalDateTime,
    val createdAt: LocalDateTime? = null,
    val mainCategory: MainCategory = MainCategory(),
    val subCategory: SubCategory? = null,
    val isCompleted: Boolean = true,
    val priority: TaskPriority = TaskPriority.STANDARD,
    val isEnableNotification: Boolean = true,
    val isConsiderInStatistics: Boolean = true,
    val taskNotifications: TaskNotifications = TaskNotifications(),
    val repeatEnabled: Boolean = false,
    val templateId: Int? = null,
    val undefinedTaskId: Long? = null,
    val repeatTimes: List<RepeatTime> = emptyList(),
    val note: String? = null,
)