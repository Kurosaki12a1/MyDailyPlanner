package com.kuro.mdp.features.overview.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.kuro.mdp.features.overview.domain.model.details.DetailsAction
import com.kuro.mdp.features.overview.domain.use_case.details.DetailsUseCase
import com.kuro.mdp.features.overview.presentation.ui.details.DetailsEvent
import com.kuro.mdp.features.overview.presentation.ui.details.DetailsViewState
import com.kuro.mdp.shared.presentation.navigation.destination.Destination
import com.kuro.mdp.shared.presentation.navigation.navigator.Navigator
import com.kuro.mdp.shared.presentation.screenmodel.BaseViewModel
import com.kuro.mdp.shared.utils.extensions.toEpochMillis
import com.kuro.mdp.shared.utils.functional.collectAndHandle
import kotlinx.coroutines.launch

/**
 * Created by: minhthinh.h on 2/18/2025
 *
 * Description:
 */
class DetailsViewModel(
    private val detailsUseCase: DetailsUseCase,
    navigator: Navigator
) : BaseViewModel<DetailsViewState, DetailsEvent>(navigator) {

    init {
        dispatchEvent(DetailsEvent.Init)
    }

    override fun initState(): DetailsViewState = DetailsViewState()

    override fun handleEvent(event: DetailsEvent) {
        when (event) {
            is DetailsEvent.Init -> {
                viewModelScope.launch {
                    detailsUseCase.loadAllSchedulesUseCase().collectAndHandle(
                        onFailure = { showError(it) },
                        onSuccess = { updateState(it) }
                    )
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

    override fun showError(e: Throwable) {

    }

    private fun updateState(action: DetailsAction) {
        when (action) {
            is DetailsAction.UpdateLoading -> {
                updateState(
                    state.value.copy(
                        isLoading = action.isLoading
                    )
                )
            }

            is DetailsAction.UpdateSchedules -> {
                updateState(
                    state.value.copy(
                        isLoading = false,
                        currentSchedule = action.schedules.find { it.date.date == action.date.date },
                        schedules = action.schedules
                    )
                )
            }
        }
    }
}