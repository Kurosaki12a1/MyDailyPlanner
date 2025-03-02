package com.kuro.mdp.features.settings.domain.use_case.settings

import com.kuro.mdp.features.settings.domain.model.actions.SettingsAction
import com.kuro.mdp.features.settings.domain.repository.SettingsMenuRepository
import com.kuro.mdp.shared.utils.functional.ResultState
import com.kuro.mdp.shared.utils.functional.handle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by: minhthinh.h on 1/14/2025
 *
 * Description:
 */
class ResetToDefaultUseCase(
    private val settingsMenuRepository: SettingsMenuRepository
) {

    operator fun invoke(): Flow<ResultState<SettingsAction>> = flow {
        settingsMenuRepository.resetAllSettings().handle(
            onFailure = { ResultState.Failure(it) },
            onSuccess = { emit(ResultState.Success(SettingsAction.ResetToDefault)) }
        )
    }
}