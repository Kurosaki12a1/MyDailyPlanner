package com.kuro.mdp.features.overview.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.kuro.mdp.features.overview.domain.model.actions.DetailsAction
import com.kuro.mdp.features.overview.domain.use_case.details.DetailsUseCase
import com.kuro.mdp.features.overview.presentation.ui.details.DetailsEvent
import com.kuro.mdp.features.overview.presentation.ui.details.DetailsViewState
import com.kuro.mdp.shared.presentation.navigation.destination.Destination
import com.kuro.mdp.shared.presentation.navigation.navigator.Navigator
import com.kuro.mdp.shared.presentation.screenmodel.BaseViewModel
import com.kuro.mdp.shared.utils.extensions.toEpochMillis
import kotlinx.coroutines.launch

/**
 * Created by: minhthinh.h on 2/18/2025
 *
 * Description:
 */
class DetailsViewModel(
    private val detailsUseCase: DetailsUseCase,
    navigator: Navigator
) : BaseViewModel<DetailsViewState, DetailsEvent, DetailsAction>(navigator) {

    init {
        dispatchEvent(DetailsEvent.Init)
    }

    override fun initState(): DetailsViewState = DetailsViewState()

    override fun handleEvent(event: DetailsEvent) {
        when (event) {
            is DetailsEvent.Init -> {
                viewModelScope.launch {
                    detailsUseCase.loadAllSchedulesUseCase().collectAndHandleWork()
                }
            }

            is DetailsEvent.OpenSchedule -> {
                val screen = Destination.Home(event.schedule.date.toEpochMillis())
                navigateTo(screen)
            }

            is DetailsEvent.PressBackButton -> {
                popBackStack()
            }
        }
    }

    override fun showError(e: Throwable?) {

    }

    override fun updateState(action: DetailsAction) {
        when (action) {
            is DetailsAction.UpdateLoading -> {
                update { it.copy(isLoading = action.isLoading) }
            }

            is DetailsAction.UpdateSchedules -> {
                update {
                    it.copy(
                        isLoading = false,
                        currentSchedule = action.schedules.find { schedule -> schedule.date.date == action.date.date },
                        schedules = action.schedules
                    )
                }
            }
        }
    }

}