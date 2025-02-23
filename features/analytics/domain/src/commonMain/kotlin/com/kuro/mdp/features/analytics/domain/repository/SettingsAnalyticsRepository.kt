package com.kuro.mdp.features.analytics.domain.repository

import com.kuro.mdp.shared.domain.model.settings.TasksSettings
import com.kuro.mdp.shared.utils.functional.ResultState

/**
 * Created by: minhthinh.h on 2/18/2025
 *
 * Description:
 */
interface SettingsAnalyticsRepository {

    suspend fun fetchTasksSettings(): ResultState<TasksSettings>

    suspend fun updateTasksSettings(settings: TasksSettings): ResultState<Unit>
}