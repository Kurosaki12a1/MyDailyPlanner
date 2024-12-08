package com.kurp.mdp.shared.data.data_sources.api.settings

import com.kurp.mdp.shared.data.entities.settings.TasksSettingsEntity
import kotlinx.coroutines.flow.Flow

interface TasksSettingsLocalDataSource {

    fun fetchSettings(): Flow<TasksSettingsEntity>
    suspend fun updateSettings(settings: TasksSettingsEntity)
}