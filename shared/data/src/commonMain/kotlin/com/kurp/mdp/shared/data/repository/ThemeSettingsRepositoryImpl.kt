package com.kurp.mdp.shared.data.repository

import com.kuro.mdp.shared.domain.model.settings.ThemeSettings
import com.kuro.mdp.shared.domain.repository.ThemeSettingsRepository
import com.kurp.mdp.shared.data.data_sources.api.settings.ThemeSettingsLocalDataSource
import com.kurp.mdp.shared.data.mappers.settings.mapToData
import com.kurp.mdp.shared.data.mappers.settings.mapToDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Created by: minhthinh.h on 12/23/2024
 *
 * Description:
 */
class ThemeSettingsRepositoryImpl(
    private val localDataSource: ThemeSettingsLocalDataSource
) : ThemeSettingsRepository {
    override fun fetchSettingsFlow(): Flow<ThemeSettings> {
        return localDataSource.fetchSettingsFlow().map { it.mapToDomain() }
    }

    override suspend fun fetchSettings(): ThemeSettings {
        return localDataSource.fetchSettings().mapToDomain()
    }

    override suspend fun updateSettings(settings: ThemeSettings) {
        localDataSource.updateSettings(settings.mapToData())
    }
}