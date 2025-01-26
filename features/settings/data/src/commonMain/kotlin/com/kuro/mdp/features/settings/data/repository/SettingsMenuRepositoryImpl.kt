package com.kuro.mdp.features.settings.data.repository

import com.kuro.mdp.features.settings.domain.repository.SettingsMenuRepository
import com.kuro.mdp.shared.domain.model.settings.Settings
import com.kuro.mdp.shared.domain.model.settings.TasksSettings
import com.kuro.mdp.shared.domain.model.settings.ThemeSettings
import com.kuro.mdp.shared.domain.repository.TasksSettingsRepository
import com.kuro.mdp.shared.domain.repository.ThemeSettingsRepository
import com.kuro.mdp.shared.utils.functional.ResultState
import com.kuro.mdp.shared.utils.functional.wrap
import com.kuro.mdp.shared.utils.functional.wrapFlow
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map

/**
 * Created by: minhthinh.h on 1/2/2025
 *
 * Description:
 */
class SettingsMenuRepositoryImpl(
    private val themeSettingsRepository: ThemeSettingsRepository,
    private val tasksSettingsRepository: TasksSettingsRepository,
) : SettingsMenuRepository {
    override suspend fun updateThemeSettings(settings: ThemeSettings): ResultState<Unit> = wrap {
        themeSettingsRepository.updateSettings(settings)
    }

    override suspend fun updateTasksSettings(settings: TasksSettings): ResultState<Unit> = wrap {
        tasksSettingsRepository.updateSettings(settings)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun fetchAllSettings(): Flow<ResultState<Settings>> = wrapFlow {
        themeSettingsRepository.fetchSettingsFlow().flatMapLatest { themeSettings ->
            tasksSettingsRepository.fetchSettings().map { taskSettings ->
                return@map Settings(
                    themeSettings = themeSettings,
                    tasksSettings = taskSettings
                )
            }
        }
    }

    override suspend fun resetAllSettings(): ResultState<Settings> = wrap {
        val themeSettings = ThemeSettings().apply { themeSettingsRepository.updateSettings(this) }
        val tasksSettings = TasksSettings().apply { tasksSettingsRepository.updateSettings(this) }

        return@wrap Settings(themeSettings, tasksSettings)
    }
}