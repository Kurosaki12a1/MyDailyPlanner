package com.kuro.mdp.features.settings.domain.repository

import com.kuro.mdp.shared.domain.model.settings.Settings
import com.kuro.mdp.shared.domain.model.settings.TasksSettings
import com.kuro.mdp.shared.domain.model.settings.ThemeSettings
import com.kuro.mdp.shared.utils.functional.ResultState
import kotlinx.coroutines.flow.Flow

/**
 * Created by: minhthinh.h on 1/2/2025
 *
 * Description:
 */
interface MenuSettingsRepository {
    suspend fun updateThemeSettings(settings: ThemeSettings): ResultState<Unit>
    suspend fun updateTasksSettings(settings: TasksSettings): ResultState<Unit>
    suspend fun fetchAllSettings(): Flow<ResultState<Settings>>
    suspend fun resetAllSettings(): ResultState<Settings>
}