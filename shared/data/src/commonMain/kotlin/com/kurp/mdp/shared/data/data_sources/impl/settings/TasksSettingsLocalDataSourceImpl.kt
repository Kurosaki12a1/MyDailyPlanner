package com.kurp.mdp.shared.data.data_sources.impl.settings

import com.kurp.mdp.shared.data.data_sources.api.settings.TasksSettingsLocalDataSource
import com.kurp.mdp.shared.data.data_sources.dao.settings.TasksSettingsDao
import com.kurp.mdp.shared.data.entities.settings.TasksSettingsEntity
import kotlinx.coroutines.flow.Flow

class TasksSettingsLocalDataSourceImpl(
    private val settingsDao: TasksSettingsDao,
) : TasksSettingsLocalDataSource {

    override fun fetchSettings(): Flow<TasksSettingsEntity> {
        return settingsDao.fetchSettingsFlow()
    }

    override suspend fun updateSettings(settings: TasksSettingsEntity) {
        settingsDao.updateSettings(settings)
    }
}