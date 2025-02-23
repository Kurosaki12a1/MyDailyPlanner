package com.kuro.mdp.features.analytics.data.repository

import com.kuro.mdp.features.analytics.domain.repository.SettingsAnalyticsRepository
import com.kuro.mdp.shared.domain.model.settings.TasksSettings
import com.kuro.mdp.shared.domain.repository.TasksSettingsRepository
import com.kuro.mdp.shared.utils.functional.ResultState
import com.kuro.mdp.shared.utils.functional.wrap
import kotlinx.coroutines.flow.first

/**
 * Created by: minhthinh.h on 2/18/2025
 *
 * Description:
 */
class SettingsAnalyticsRepositoryImpl(
    private val settingsRepository: TasksSettingsRepository
) : SettingsAnalyticsRepository {
    override suspend fun fetchTasksSettings(): ResultState<TasksSettings> = wrap {
        settingsRepository.fetchSettings().first()
    }

    override suspend fun updateTasksSettings(settings: TasksSettings): ResultState<Unit> = wrap {
        settingsRepository.updateSettings(settings)
    }
}