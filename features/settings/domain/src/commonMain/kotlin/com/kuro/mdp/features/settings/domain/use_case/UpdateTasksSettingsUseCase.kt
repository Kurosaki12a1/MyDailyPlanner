package com.kuro.mdp.features.settings.domain.use_case

import com.kuro.mdp.features.settings.domain.repository.MenuSettingsRepository
import com.kuro.mdp.shared.domain.model.settings.TasksSettings
import com.kuro.mdp.shared.utils.functional.ResultState
import com.kuro.mdp.shared.utils.functional.handle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by: minhthinh.h on 1/14/2025
 *
 * Description:
 */
class UpdateTasksSettingsUseCase(
    private val menuSettingsRepository: MenuSettingsRepository
) {
    operator fun invoke(taskSettings: TasksSettings): Flow<ResultState<Unit>> = flow {
        menuSettingsRepository.updateTasksSettings(taskSettings).handle(
            onFailure = { e -> emit(ResultState.Failure(e)) },
            onSuccess = {}
        )

    }
}