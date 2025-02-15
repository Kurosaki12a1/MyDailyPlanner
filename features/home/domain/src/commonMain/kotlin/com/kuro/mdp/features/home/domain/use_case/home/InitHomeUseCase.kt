package com.kuro.mdp.features.home.domain.use_case.home

import com.kuro.mdp.features.home.domain.repository.home.HomeSettingsRepository
import com.kuro.mdp.shared.domain.model.settings.TasksSettings
import com.kuro.mdp.shared.utils.functional.ResultState
import com.kuro.mdp.shared.utils.functional.collectAndHandle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by: minhthinh.h on 1/13/2025
 *
 * Description:
 */
class InitHomeUseCase(
    private val settingsRepository: HomeSettingsRepository
) {
    operator fun invoke(): Flow<ResultState<TasksSettings>> = flow {
        settingsRepository.fetchTasksSettings().collectAndHandle(
            onFailure = { e -> emit(ResultState.Failure(e)) },
            onSuccess = {
                emit(ResultState.Success(it))
            }
        )
    }
}