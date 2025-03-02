package com.kuro.mdp.features.settings.domain.use_case.settings

import com.kuro.mdp.features.settings.domain.model.actions.SettingsAction
import com.kuro.mdp.features.settings.domain.repository.SettingsMenuRepository
import com.kuro.mdp.shared.utils.functional.ResultState
import com.kuro.mdp.shared.utils.functional.collectAndHandle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by: minhthinh.h on 1/14/2025
 *
 * Description:
 */
class LoadAllSettingsUseCase(
    private val settingsMenuRepository: SettingsMenuRepository
) {

    operator fun invoke(): Flow<ResultState<SettingsAction>> = flow {
        settingsMenuRepository.fetchAllSettings().collectAndHandle(
            onFailure = { emit(ResultState.Failure(it)) },
            onSuccess = { emit(ResultState.Success(SettingsAction.ChangeAllSettings(it))) }
        )
    }
}