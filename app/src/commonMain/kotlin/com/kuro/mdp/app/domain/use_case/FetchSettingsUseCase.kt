package com.kuro.mdp.app.domain.use_case

import com.kuro.mdp.features.settings.domain.repository.SettingsMenuRepository
import com.kuro.mdp.shared.domain.model.settings.Settings
import com.kuro.mdp.shared.utils.functional.ResultState
import com.kuro.mdp.shared.utils.functional.collectAndHandle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by: minhthinh.h on 2/7/2025
 *
 * Description:
 */
class FetchSettingsUseCase(
    private val settingsMenuRepository: SettingsMenuRepository
) {

    operator fun invoke(): Flow<ResultState<Settings>> = flow {
        settingsMenuRepository.fetchAllSettings().collectAndHandle(
            onFailure = { emit(ResultState.Failure(it)) },
            onSuccess = { emit(ResultState.Success(it)) }
        )
    }
}