package com.kuro.mdp.features.analytics.domain.use_case

import com.kuro.mdp.features.analytics.domain.models.actions.AnalyticsAction
import com.kuro.mdp.features.analytics.domain.repository.SettingsAnalyticsRepository
import com.kuro.mdp.shared.utils.functional.ResultState
import com.kuro.mdp.shared.utils.functional.handle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by: minhthinh.h on 2/19/2025
 *
 * Description:
 */
class LoadSettingsUseCase(
    private val settingsRepository: SettingsAnalyticsRepository
) {
    operator fun invoke(): Flow<ResultState<AnalyticsAction>> = flow {
        settingsRepository.fetchTasksSettings().handle(
            onFailure = { emit(ResultState.Failure(it)) },
            onSuccess = { emit(ResultState.Success(AnalyticsAction.UpdateTimePeriod(it.taskAnalyticsRange))) }
        )
    }
}