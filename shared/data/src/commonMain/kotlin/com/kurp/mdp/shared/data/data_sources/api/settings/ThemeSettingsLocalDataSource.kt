package com.kurp.mdp.shared.data.data_sources.api.settings

import com.kurp.mdp.shared.data.entities.settings.ThemeSettingsEntity
import kotlinx.coroutines.flow.Flow

interface ThemeSettingsLocalDataSource {
    fun fetchSettingsFlow(): Flow<ThemeSettingsEntity>
    suspend fun fetchSettings(): ThemeSettingsEntity
    suspend fun updateSettings(settings: ThemeSettingsEntity)
}