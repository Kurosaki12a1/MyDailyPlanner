package com.kuro.mdp.shared.domain.repository

import com.kuro.mdp.shared.domain.model.settings.ThemeSettings
import kotlinx.coroutines.flow.Flow


interface ThemeSettingsRepository {
    fun fetchSettingsFlow(): Flow<ThemeSettings>
    suspend fun fetchSettings(): ThemeSettings
    suspend fun updateSettings(settings: ThemeSettings)
}
