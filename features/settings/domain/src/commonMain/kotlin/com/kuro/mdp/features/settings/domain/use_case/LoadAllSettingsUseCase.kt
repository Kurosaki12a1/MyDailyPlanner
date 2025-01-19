package com.kuro.mdp.features.settings.domain.use_case

import com.kuro.mdp.features.settings.domain.repository.MenuSettingsRepository
import com.kuro.mdp.shared.domain.model.settings.Settings
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
    private val menuSettingsRepository: MenuSettingsRepository
) {

    operator fun invoke(): Flow<ResultState<Settings>> = flow {
        menuSettingsRepository.fetchAllSettings().collectAndHandle(
            onFailure = { emit(ResultState.Failure(it)) },
            onSuccess = { emit(ResultState.Success(it)) }
        )
    }
}