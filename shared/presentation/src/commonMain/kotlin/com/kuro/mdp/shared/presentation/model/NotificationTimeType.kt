package com.kuro.mdp.shared.presentation.model

import com.kuro.mdp.shared.domain.model.schedules.TaskNotificationType
import com.kuro.mdp.shared.presentation.theme.resources.AppStrings
import org.jetbrains.compose.resources.StringResource

/**
 * Created by: minhthinh.h on 12/9/2024
 *
 * Description:
 */
enum class NotificationTimeType {
    BEFORE_TASK, START_TASK, AFTER_TASK
}

fun TaskNotificationType.toTimeType(): NotificationTimeType = when (this) {
    TaskNotificationType.START -> NotificationTimeType.START_TASK
    TaskNotificationType.FIFTEEN_MINUTES_BEFORE -> NotificationTimeType.BEFORE_TASK
    TaskNotificationType.ONE_HOUR_BEFORE -> NotificationTimeType.BEFORE_TASK
    TaskNotificationType.THREE_HOUR_BEFORE -> NotificationTimeType.BEFORE_TASK
    TaskNotificationType.ONE_DAY_BEFORE -> NotificationTimeType.BEFORE_TASK
    TaskNotificationType.ONE_WEEK_BEFORE -> NotificationTimeType.BEFORE_TASK
    TaskNotificationType.AFTER_START_BEFORE_END -> NotificationTimeType.AFTER_TASK
}

fun NotificationTimeType.mapToString(strings: AppStrings): StringResource = when (this) {
    NotificationTimeType.BEFORE_TASK -> strings.beforeTaskNotifyText
    NotificationTimeType.START_TASK -> strings.startTaskNotifyText
    NotificationTimeType.AFTER_TASK -> strings.afterTaskNotifyText
}
