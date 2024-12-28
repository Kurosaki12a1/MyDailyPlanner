package com.kuro.mdp.features.home.data.repository.home

import com.kuro.mdp.features.home.domain.repository.home.HomeSettingsRepository
import com.kuro.mdp.shared.domain.model.settings.TasksSettings
import com.kuro.mdp.shared.domain.repository.TasksSettingsRepository
import com.kuro.mdp.shared.utils.functional.ResultState
import com.kuro.mdp.shared.utils.functional.wrap
import com.kuro.mdp.shared.utils.functional.wrapFlow
import kotlinx.coroutines.flow.Flow

/**
 * Created by: minhthinh.h on 12/24/2024
 *
 * Description:
 */
class HomeSettingsRepositoryImpl(
    private val tasksSettingsRepository: TasksSettingsRepository
) : HomeSettingsRepository {
    override suspend fun fetchTasksSettings(): Flow<ResultState<TasksSettings>> = wrapFlow {
        tasksSettingsRepository.fetchSettings()
    }

    override suspend fun updateTasksSettings(settings: TasksSettings): ResultState<Unit> = wrap {
        tasksSettingsRepository.updateSettings(settings)
    }
}