package com.kuro.mdp.features.home.domain.use_case

import com.kuro.mdp.features.home.domain.repository.home.HomeSettingsRepository
import com.kuro.mdp.shared.domain.model.settings.ViewToggleStatus
import com.kuro.mdp.shared.utils.functional.ResultState
import com.kuro.mdp.shared.utils.functional.handle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

/**
 * Created by: minhthinh.h on 1/14/2025
 *
 * Description:
 */
class ChangeTaskViewStatusUseCase(
    private val settingsRepository: HomeSettingsRepository
) {

    operator fun invoke(status: ViewToggleStatus): Flow<ResultState<Unit>> = flow {
        val newStatus = when (status) {
            ViewToggleStatus.EXPANDED -> ViewToggleStatus.COMPACT
            ViewToggleStatus.COMPACT -> ViewToggleStatus.EXPANDED
        }
        settingsRepository.fetchTasksSettings().first().handle(
            onFailure = { error("Error get task settings!") },
            onSuccess = { oldSettings ->
                val newSettings = oldSettings.copy(taskViewStatus = newStatus)
                settingsRepository.updateTasksSettings(newSettings).handle(
                    onFailure = { e -> emit(ResultState.Failure(e)) },
                    onSuccess = { emit(ResultState.Success(Unit)) }
                )
            }
        )
    }
}