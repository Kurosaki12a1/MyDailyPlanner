package com.kuro.mdp.shared.domain.repository

import com.kuro.mdp.shared.domain.model.settings.TasksSettings
import kotlinx.coroutines.flow.Flow

interface TasksSettingsRepository {
    fun fetchSettings(): Flow<TasksSettings>
    suspend fun updateSettings(settings: TasksSettings)
}
