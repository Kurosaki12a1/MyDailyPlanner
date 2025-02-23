package com.kuro.mdp.features.analytics.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.kuro.mdp.features.analytics.domain.models.actions.AnalyticsAction
import com.kuro.mdp.features.analytics.domain.use_case.AnalyticsUseCase
import com.kuro.mdp.features.analytics.presentation.ui.analytics.contract.AnalyticsEvent
import com.kuro.mdp.features.analytics.presentation.ui.analytics.contract.AnalyticsViewState
import com.kuro.mdp.shared.presentation.navigation.navigator.Navigator
import com.kuro.mdp.shared.presentation.screenmodel.BaseViewModelNew
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

/**
 * Created by: minhthinh.h on 2/19/2025
 *
 * Description:
 */
class AnalyticsViewModel(
    private val analyticsUseCase: AnalyticsUseCase,
    navigator: Navigator
) : BaseViewModelNew<AnalyticsViewState, AnalyticsEvent, AnalyticsAction>(navigator) {

    init {
        dispatchEvent(AnalyticsEvent.Init)
    }

    override fun initState(): AnalyticsViewState = AnalyticsViewState()

    override fun handleEvent(event: AnalyticsEvent) {
        when (event) {
            is AnalyticsEvent.ChangeTimePeriod -> {
                viewModelScope.launch {
                    launch {
                        analyticsUseCase.updateTimePeriodUseCase(event.period).collectAndHandleWork()
                    }
                    launch {
                        analyticsUseCase.loadAnalyticsUseCase(event.period).collectAndHandleWork()
                    }
                }
            }

            is AnalyticsEvent.Init -> {
                viewModelScope.launch {
                    try {
                        analyticsUseCase.loadSettingsUseCase().collectAndHandleWork()
                        analyticsUseCase.loadAnalyticsUseCase(checkNotNull(state.value.timePeriod)).collectAndHandleWork()
                    } catch (e: Exception) {
                        if (e is CoroutineExceptionHandler) throw e
                    }
                }
            }
        }
    }

    override fun showError(e: Throwable?) {

    }

    override fun updateState(action: AnalyticsAction) {
        when (action) {
            is AnalyticsAction.UpdateLoading -> update {
                it.copy(isLoading = action.isLoading)
            }

            is AnalyticsAction.UpdateAnalytics -> update {
                it.copy(
                    scheduleAnalytics = action.analytics,
                    isLoading = false,
                )
            }

            is AnalyticsAction.UpdateTimePeriod -> update {
                it.copy(
                    timePeriod = action.period
                )
            }
        }
    }
}