package com.kurp.mdp.shared.data.data_sources.impl.settings

import com.kurp.mdp.shared.data.data_sources.api.settings.ThemeSettingsLocalDataSource
import com.kurp.mdp.shared.data.data_sources.dao.settings.ThemeSettingsDao
import com.kurp.mdp.shared.data.entities.settings.ThemeSettingsEntity
import kotlinx.coroutines.flow.Flow

class ThemeSettingsLocalDataSourceImpl(
    private val settingsDao: ThemeSettingsDao,
) : ThemeSettingsLocalDataSource {

    override fun fetchSettingsFlow(): Flow<ThemeSettingsEntity> {
        return settingsDao.fetchSettingsFlow()
    }

    override suspend fun fetchSettings(): ThemeSettingsEntity {
        return settingsDao.fetchSettings()
    }

    override suspend fun updateSettings(settings: ThemeSettingsEntity) {
        settingsDao.updateSettings(settings)
    }
}