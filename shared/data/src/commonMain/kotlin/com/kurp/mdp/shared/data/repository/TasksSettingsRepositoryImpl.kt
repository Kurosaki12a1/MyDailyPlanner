package com.kurp.mdp.shared.data.repository

import com.kuro.mdp.shared.domain.model.settings.TasksSettings
import com.kuro.mdp.shared.domain.repository.TasksSettingsRepository
import com.kurp.mdp.shared.data.data_sources.api.settings.TasksSettingsLocalDataSource
import com.kurp.mdp.shared.data.mappers.settings.mapToData
import com.kurp.mdp.shared.data.mappers.settings.mapToDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Created by: minhthinh.h on 12/23/2024
 *
 * Description:
 */
class TasksSettingsRepositoryImpl(
    private val localDataSource: TasksSettingsLocalDataSource
) : TasksSettingsRepository {
    override fun fetchSettings(): Flow<TasksSettings> {
        return localDataSource.fetchSettings().map { it.mapToDomain() }
    }

    override suspend fun updateSettings(settings: TasksSettings) {
        localDataSource.updateSettings(settings.mapToData())
    }
}