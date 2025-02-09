package com.kuro.mdp.features.home.domain.model.editor

import com.kuro.mdp.shared.domain.model.schedules.TaskPriority
import kotlinx.serialization.Serializable

@Serializable
data class EditParameters(
    val priority: TaskPriority = TaskPriority.STANDARD,
    val isEnableNotification: Boolean = true,
    val taskNotifications: TaskNotificationsHome = TaskNotificationsHome(),
    val isConsiderInStatistics: Boolean = true,
)
