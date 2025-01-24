package com.kuro.mdp.features.home.presentation.ui.home.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.kuro.mdp.features.home.domain.model.HomeError
import com.kuro.mdp.features.home.domain.model.schedules.TimeTaskHome
import com.kuro.mdp.features.home.domain.use_case.HomeUseCase
import com.kuro.mdp.features.home.presentation.ui.home.ui.HomeEvent
import com.kuro.mdp.features.home.presentation.ui.home.ui.HomeViewState
import com.kuro.mdp.shared.domain.model.schedules.DailyScheduleStatus
import com.kuro.mdp.shared.domain.model.settings.TasksSettings
import com.kuro.mdp.shared.presentation.navigation.destination.Destination
import com.kuro.mdp.shared.presentation.navigation.navigator.Navigator
import com.kuro.mdp.shared.presentation.screenmodel.BaseViewModel
import com.kuro.mdp.shared.utils.functional.TimeShiftException
import com.kuro.mdp.shared.utils.functional.TimeTaskImportanceException
import com.kuro.mdp.shared.utils.functional.collectAndHandle
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDateTime

/**
 * Created by: minhthinh.h on 12/20/2024
 *
 * Description:
 */
internal class HomeViewModel(
    private val homeUseCase: HomeUseCase,
    navigator: Navigator
) : BaseViewModel<HomeViewState, HomeEvent>(navigator) {

    private val _isDateDialogShown = mutableStateOf(false)
    val isDateDialogShown: State<Boolean>
        get() = _isDateDialogShown

    override fun initState(): HomeViewState = HomeViewState()

    override fun handleEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.Init -> {
                viewModelScope.launch {
                    homeUseCase.initHomeUseCase(event.scheduleDate).collectAndHandle(
                        onFailure = { e -> showError(e) },
                        onSuccess = { task -> setUpSettings(task) }
                    )
                }
            }

            is HomeEvent.ChangeTaskDoneStateButton -> {
                viewModelScope.launch {
                    homeUseCase.changeTaskDoneStateWorkUseCase(state.value.currentDate, event.timeTask.key).collectAndHandle(
                        onFailure = { e -> showError(e) }
                    )
                }
            }

            is HomeEvent.CreateSchedule -> {
                viewModelScope.launch {
                    homeUseCase.createScheduleUseCase(state.value.currentDate).collectAndHandle(
                        onFailure = { e -> showError(e) }
                    )
                }
            }


            is HomeEvent.LoadSchedule -> {
                viewModelScope.launch {
                    homeUseCase.loadScheduleUseCase(event.date).collectAndHandle(
                        onFailure = { e -> showError(e) },
                        onSuccess = { data ->
                            loadSchedule(
                                timeTasks = data?.timeTasks ?: emptyList(),
                                date = data?.date,
                                dateStatus = if (data?.progress == -1f) null else data?.dateStatus
                            )
                        }
                    )
                }
            }

            is HomeEvent.PressAddTimeTaskButton -> {
                // TODO
            }

            is HomeEvent.PressEditTimeTaskButton -> {

            }

            is HomeEvent.PressOverviewButton -> {
                navigateTo(Destination.Overview)
            }

            is HomeEvent.PressViewToggleButton -> {
                viewModelScope.launch {
                    homeUseCase.changeTaskViewStatusUseCase(event.status).collectAndHandle(
                        onFailure = { e -> showError(e) }
                    )
                }
            }

            is HomeEvent.SetShowDialog -> {
                _isDateDialogShown.value = event.isShow
            }

            is HomeEvent.TimeTaskShiftDown -> {
                viewModelScope.launch {
                    homeUseCase.shiftDownTimeWorkUseCase(event.timeTask).collectAndHandle(
                        onFailure = { e -> showError(e) }
                    )
                }
            }

            is HomeEvent.TimeTaskShiftUp -> {
                viewModelScope.launch {
                    homeUseCase.shiftUpTimeWorkUseCase(event.timeTask).collectAndHandle(
                        onFailure = { e -> showError(e) }
                    )
                }
            }
        }
    }

    override fun showError(e: Throwable) {
        updateState(
            newState = state.value.copy(
                error = when (e) {
                    is TimeShiftException -> HomeError.ShiftError
                    is TimeTaskImportanceException -> HomeError.ImportanceError
                    else -> HomeError.OtherError(e)
                }
            )
        )
    }

    private fun setUpSettings(settings: TasksSettings) {
        updateState(
            newState = state.value.copy(
                taskViewStatus = settings.taskViewStatus,
                calendarButtonBehavior = settings.calendarButtonBehavior
            )
        )
    }

    private fun loadSchedule(
        timeTasks: List<TimeTaskHome>,
        date: LocalDateTime?,
        dateStatus: DailyScheduleStatus?
    ) {
        updateState(
            newState = state.value.copy(
                timeTasks = timeTasks,
                currentDate = date,
                dateStatus = dateStatus
            )
        )
    }
}