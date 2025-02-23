package com.kuro.mdp.features.analytics.domain.use_case

import com.kuro.mdp.features.analytics.domain.models.actions.AnalyticsAction
import com.kuro.mdp.features.analytics.domain.repository.AnalyticsRepository
import com.kuro.mdp.shared.utils.functional.Constants
import com.kuro.mdp.shared.utils.functional.ResultState
import com.kuro.mdp.shared.utils.functional.TimePeriod
import com.kuro.mdp.shared.utils.functional.collectAndHandle
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart

/**
 * Created by: minhthinh.h on 2/19/2025
 *
 * Description:
 */
class LoadAnalyticsUseCase(
    private val analyticsRepository: AnalyticsRepository
) {

    operator fun invoke(period: TimePeriod): Flow<ResultState<AnalyticsAction>> = flow<ResultState<AnalyticsAction>> {
        delay(Constants.Delay.LOAD_ANIMATION)
        analyticsRepository.fetchAnalytics(period).collectAndHandle(
            onFailure = { emit(ResultState.Failure(it)) },
            onSuccess = { analytics -> emit(ResultState.Success(AnalyticsAction.UpdateAnalytics(analytics))) }
        )
    }.onStart { emit(ResultState.Success(AnalyticsAction.UpdateLoading(isLoading = true))) }
}