package com.kuro.mdp.features.overview.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.kuro.mdp.features.overview.domain.model.OverViewError
import com.kuro.mdp.features.overview.domain.model.actions.OverViewAction
import com.kuro.mdp.features.overview.domain.use_case.over_view.OverViewUseCase
import com.kuro.mdp.features.overview.presentation.ui.overview.OverViewEvent
import com.kuro.mdp.features.overview.presentation.ui.overview.OverViewViewState
import com.kuro.mdp.shared.presentation.navigation.destination.Destination
import com.kuro.mdp.shared.presentation.navigation.navigator.Navigator
import com.kuro.mdp.shared.presentation.screenmodel.BaseViewModel
import com.kuro.mdp.shared.utils.extensions.toEpochMillis
import com.kuro.mdp.shared.utils.functional.TimeShiftException
import com.kuro.mdp.shared.utils.functional.TimeTaskImportanceException
import kotlinx.coroutines.launch

/**
 * Created by: minhthinh.h on 1/21/2025
 *
 * Description:
 */
class OverViewViewModel(
    private val overViewUseCase: OverViewUseCase,
    navigator: Navigator
) : BaseViewModel<OverViewViewState, OverViewEvent, OverViewAction>(navigator) {
    init {
        dispatchEvent(OverViewEvent.Init)
    }


    override fun initState(): OverViewViewState = OverViewViewState()

    override fun handleEvent(event: OverViewEvent) {
        when (event) {
            is OverViewEvent.Init, OverViewEvent.Refresh -> {
                updateState(OverViewAction.UpdateLoading(true))
                viewModelScope.launch {
                    launch {
                        overViewUseCase.loadScheduleUseCase().collectAndHandleWork()
                    }
                    launch {
                        overViewUseCase.updateUndefinedTasksUseCase().collectAndHandleWork()
                    }
                    launch {
                        overViewUseCase.updateCategoriesUseCase().collectAndHandleWork()
                    }
                }
            }

            is OverViewEvent.ClearFailure -> showError(null)

            is OverViewEvent.CreateOrUpdateUndefinedTask -> {
                viewModelScope.launch {
                    overViewUseCase.createOrUpdateTaskUseCase(event.task).collectAndHandleWork()
                }
            }

            is OverViewEvent.DeleteUndefinedTask -> {
                viewModelScope.launch {
                    overViewUseCase.deleteUndefinedTaskUseCase(event.task).collectAndHandleWork()
                }
            }

            is OverViewEvent.ExecuteUndefinedTask -> {
                viewModelScope.launch {
                    overViewUseCase.executeUndefinedTaskUseCase(event.scheduleDate, event.task).handleWork()
                }
            }

            is OverViewEvent.OpenAllSchedules -> {
                navigateTo(Destination.Details)
            }

            is OverViewEvent.OpenSchedule -> {
                val screen = Destination.Home(event.scheduleDate?.toEpochMillis())
                navigateTo(screen)
            }
        }
    }

    override fun showError(e: Throwable?) {
        update {
            it.copy(
                error = when (e) {
                    null -> null
                    is TimeShiftException -> OverViewError.ShiftError
                    is TimeTaskImportanceException -> OverViewError.ImportanceError
                    else -> OverViewError.OtherError(e)
                }
            )
        }
    }

    override fun updateState(action: OverViewAction) {
        when (action) {
            is OverViewAction.UpdateCategories -> {
                update {
                    it.copy(categories = action.categories)
                }
            }

            is OverViewAction.UpdateLoading -> {
                update {
                    it.copy(isLoading = action.isLoading)
                }
            }

            is OverViewAction.UpdateSchedules -> {
                update {
                    it.copy(
                        isLoading = false,
                        currentDate = action.date,
                        currentSchedule = action.schedules.find { ownDate -> ownDate.date.date == action.date },
                        schedules = action.schedules
                    )
                }
            }

            is OverViewAction.UpdateUndefinedTasks -> {
                update {
                    it.copy(undefinedTasks = action.tasks)
                }
            }

            else -> {}
        }
    }
}