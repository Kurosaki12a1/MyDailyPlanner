package com.kuro.mdp.features.settings.presentation.mappers

import com.kuro.mdp.features.settings.domain.model.TasksSettingsUi
import com.kuro.mdp.shared.domain.model.settings.TasksSettings

/**
 * Created by: minhthinh.h on 12/26/2024
 *
 * Description:
 */
internal fun TasksSettings.mapToUi() = TasksSettingsUi(
    taskViewStatus = taskViewStatus,
    calendarButtonBehavior = calendarButtonBehavior,
    secureMode = secureMode,
)

internal fun TasksSettingsUi.mapToDomain() = TasksSettings(
    taskViewStatus = taskViewStatus,
    calendarButtonBehavior = calendarButtonBehavior,
    secureMode = secureMode,
)
