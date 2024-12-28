package com.kuro.mdp.features.home.domain.repository.home

import com.kuro.mdp.shared.domain.model.settings.TasksSettings
import com.kuro.mdp.shared.utils.functional.ResultState
import kotlinx.coroutines.flow.Flow

/**
 * Created by: minhthinh.h on 12/24/2024
 *
 * Description:
 */
interface HomeSettingsRepository {
    suspend fun fetchTasksSettings(): Flow<ResultState<TasksSettings>>
    suspend fun updateTasksSettings(settings: TasksSettings): ResultState<Unit>
}