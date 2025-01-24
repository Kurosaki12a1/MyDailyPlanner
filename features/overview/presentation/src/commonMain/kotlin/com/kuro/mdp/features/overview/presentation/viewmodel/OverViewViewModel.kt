package com.kuro.mdp.features.overview.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.kuro.mdp.features.overview.domain.model.OverViewError
import com.kuro.mdp.features.overview.domain.use_case.over_view.OverViewUseCase
import com.kuro.mdp.features.overview.presentation.ui.OverViewEvent
import com.kuro.mdp.features.overview.presentation.ui.OverViewViewState
import com.kuro.mdp.shared.presentation.navigation.destination.Destination
import com.kuro.mdp.shared.presentation.navigation.navigator.Navigator
import com.kuro.mdp.shared.presentation.screenmodel.BaseViewModel
import com.kuro.mdp.shared.utils.extensions.toEpochMillis
import com.kuro.mdp.shared.utils.functional.TimeShiftException
import com.kuro.mdp.shared.utils.functional.TimeTaskImportanceException
import com.kuro.mdp.shared.utils.functional.collectAndHandle
import kotlinx.coroutines.launch

/**
 * Created by: minhthinh.h on 1/21/2025
 *
 * Description:
 */
class OverViewViewModel(
    private val overViewUseCase: OverViewUseCase,
    navigator: Navigator
) : BaseViewModel<OverViewViewState, OverViewEvent>(navigator) {
    init {
        dispatchEvent(OverViewEvent.Init)
    }

    override fun initState(): OverViewViewState = OverViewViewState()

    override fun handleEvent(event: OverViewEvent) {
        when (event) {
            is OverViewEvent.Init, OverViewEvent.Refresh -> {
                showLoading(true)
                viewModelScope.launch {
                    launch {
                        overViewUseCase.loadScheduleUseCase().collectAndHandle(
                            onFailure = { showError(it) },
                            onSuccess = {
                                updateState(
                                    state.value.copy(
                                        isLoading = false,
                                        currentDate = it.first,
                                        currentSchedule = it.second.find { ownDate -> ownDate.date == it.first },
                                        schedules = it.second
                                    )
                                )
                            }
                        )
                    }
                    launch {
                        overViewUseCase.updateUndefinedTasksUseCase().collectAndHandle(
                            onFailure = { showError(it) },
                            onSuccess = {
                                updateState(state.value.copy(undefinedTasks = it))
                            }
                        )
                    }
                    launch {
                        overViewUseCase.updateCategoriesUseCase().collectAndHandle(
                            onFailure = { showError(it) },
                            onSuccess = {
                                updateState(state.value.copy(categories = it))
                            }
                        )
                    }
                }
            }

            is OverViewEvent.CreateOrUpdateUndefinedTask -> {
                viewModelScope.launch {
                    overViewUseCase.createOrUpdateTaskUseCase(event.task).collectAndHandle(
                        onFailure = { showError(it) }
                    )
                }
            }

            is OverViewEvent.DeleteUndefinedTask -> {
                viewModelScope.launch {
                    overViewUseCase.deleteUndefinedTaskUseCase(event.task).collectAndHandle(
                        onFailure = { showError(it) }
                    )
                }
            }

            is OverViewEvent.ExecuteUndefinedTask -> {
                viewModelScope.launch {
                    overViewUseCase.executeUndefinedTaskUseCase(event.scheduleDate, event.task)
                }
            }

            is OverViewEvent.OpenAllSchedules -> {
                // Detail
            }

            is OverViewEvent.OpenSchedule -> {
                val screen = Destination.Home(event.scheduleDate?.toEpochMillis())
                navigateTo(screen)
            }

            is OverViewEvent.PressScheduleButton -> {
                val screen = Destination.Home()
                navigateTo(screen)
            }
        }
    }

    override fun showError(e: Throwable) {
        updateState(
            newState = state.value.copy(
                error = when (e) {
                    is TimeShiftException -> OverViewError.ShiftError
                    is TimeTaskImportanceException -> OverViewError.ImportanceError
                    else -> OverViewError.OtherError(e)
                }
            )
        )
    }

    private fun showLoading(show: Boolean) {
        updateState(newState = state.value.copy(isLoading = show))
    }
}