package com.kuro.mdp.features.home.presentation.ui.home.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.kuro.mdp.features.home.domain.mapper.schedules.mapToDomain
import com.kuro.mdp.features.home.domain.model.HomeError
import com.kuro.mdp.features.home.domain.model.actions.HomeAction
import com.kuro.mdp.features.home.domain.use_case.home.HomeUseCase
import com.kuro.mdp.features.home.presentation.ui.home.ui.home.contract.HomeEvent
import com.kuro.mdp.features.home.presentation.ui.home.ui.home.contract.HomeViewState
import com.kuro.mdp.shared.presentation.navigation.graph.NavigationGraph
import com.kuro.mdp.shared.presentation.navigation.navigator.Navigator
import com.kuro.mdp.shared.presentation.screenmodel.BaseViewModel
import com.kuro.mdp.shared.utils.functional.TimeShiftException
import com.kuro.mdp.shared.utils.functional.TimeTaskImportanceException
import kotlinx.coroutines.launch

/**
 * Created by: minhthinh.h on 12/20/2024
 *
 * Description:
 */
internal class HomeViewModel(
    private val homeUseCase: HomeUseCase,
    navigator: Navigator
) : BaseViewModel<HomeViewState, HomeEvent, HomeAction>(navigator) {

    private val _isDateDialogShown = mutableStateOf(false)
    val isDateDialogShown: State<Boolean>
        get() = _isDateDialogShown

    init {
        dispatchEvent(HomeEvent.Init)
    }

    override fun initState(): HomeViewState = HomeViewState()

    override fun handleEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.Init -> {
                viewModelScope.launch {
                    homeUseCase.initHomeUseCase()
                        .collectAndHandleWork()
                }
            }

            is HomeEvent.ChangeTaskDoneStateButton -> {
                viewModelScope.launch {
                    homeUseCase.changeTaskDoneStateWorkUseCase(
                        state.value.currentDate,
                        event.timeTask.key
                    )
                        .collectAndHandleWork()
                }
            }

            is HomeEvent.CreateSchedule -> {
                viewModelScope.launch {
                    homeUseCase.createScheduleUseCase(state.value.currentDate)
                        .collectAndHandleWork()
                }
            }


            is HomeEvent.LoadSchedule -> {
                viewModelScope.launch {
                    homeUseCase.loadScheduleUseCase(event.date)
                        .collectAndHandleWork()
                }
            }

            is HomeEvent.PressAddTimeTaskButton -> {
                viewModelScope.launch {
                    homeUseCase.addTimeTaskUseCase(
                        date = state.value.currentDate,
                        startTime = event.startTime,
                        endTime = event.endTime
                    ).handleWork()
                }
            }

            is HomeEvent.PressEditTimeTaskButton -> {
                viewModelScope.launch {
                    homeUseCase.editTimeTaskUseCase(
                        timeTask = event.timeTask.mapToDomain()
                    ).handleWork()
                }
            }

            is HomeEvent.PressOverviewButton -> {
                navigateTo(NavigationGraph.OverviewGraph)
            }

            is HomeEvent.PressViewToggleButton -> {
                viewModelScope.launch {
                    homeUseCase.changeTaskViewStatusUseCase(event.status).collectAndHandleWork()
                }
            }

            is HomeEvent.SetShowDialog -> {
                _isDateDialogShown.value = event.isShow
            }

            is HomeEvent.TimeTaskShiftDown -> {
                viewModelScope.launch {
                    homeUseCase.shiftDownTimeWorkUseCase(event.timeTask)
                        .collectAndHandleWork()
                }
            }

            is HomeEvent.TimeTaskShiftUp -> {
                viewModelScope.launch {
                    homeUseCase.shiftUpTimeWorkUseCase(event.timeTask)
                        .collectAndHandleWork()
                }
            }

            HomeEvent.ClearFailure -> {
                showError(null)
            }
        }
    }

    override fun showError(e: Throwable?) {
        update {
            if (e == null) it.copy(error = null)
            else {
                it.copy(
                    error = when (e) {
                        is TimeShiftException -> HomeError.ShiftError
                        is TimeTaskImportanceException -> HomeError.ImportanceError
                        else -> HomeError.OtherError(e)
                    }
                )
            }
        }
    }

    override fun updateState(action: HomeAction) {
        when (action) {
            is HomeAction.Navigate -> {
                update { state.value }
            }

            is HomeAction.SetEmptySchedule -> update {
                it.copy(
                    timeTasks = emptyList(),
                    currentDate = action.date,
                    dateStatus = action.status
                )
            }

            is HomeAction.SetupSettings -> {
                update {
                    it.copy(
                        taskViewStatus = action.settings.taskViewStatus,
                        calendarButtonBehavior = action.settings.calendarButtonBehavior
                    )
                }
            }

            is HomeAction.UpdateSchedule -> {
                update {
                    it.copy(
                        timeTasks = action.schedule.timeTasks,
                        currentDate = action.schedule.date,
                        dateStatus = action.schedule.dateStatus,
                    )
                }
            }
        }
    }
}