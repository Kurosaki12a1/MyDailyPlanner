package com.kuro.mdp.features.home.presentation.ui.home.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.kuro.mdp.features.home.domain.api.ScheduleHomeToUiMapper
import com.kuro.mdp.features.home.domain.model.HomeError
import com.kuro.mdp.features.home.domain.repository.common.TimeTaskStatusController
import com.kuro.mdp.features.home.domain.repository.home.HomeScheduleRepository
import com.kuro.mdp.features.home.domain.repository.home.HomeSettingsRepository
import com.kuro.mdp.features.home.domain.repository.home.HomeTimeShiftRepository
import com.kuro.mdp.features.home.presentation.ui.home.ui.HomeEvent
import com.kuro.mdp.features.home.presentation.ui.home.ui.HomeViewState
import com.kuro.mdp.shared.domain.model.settings.TasksSettings
import com.kuro.mdp.shared.presentation.navigation.navigator.Navigator
import com.kuro.mdp.shared.presentation.notifications.TimeTaskAlarmManager
import com.kuro.mdp.shared.presentation.screenmodel.BaseViewModel
import com.kuro.mdp.shared.utils.functional.TimeShiftException
import com.kuro.mdp.shared.utils.functional.TimeTaskImportanceException
import com.kuro.mdp.shared.utils.functional.collectAndHandle
import com.kuro.mdp.shared.utils.managers.DateManager
import kotlinx.coroutines.launch

/**
 * Created by: minhthinh.h on 12/20/2024
 *
 * Description:
 */
internal class HomeViewModel(
    private val scheduleRepository: HomeScheduleRepository,
    private val timeShiftRepository: HomeTimeShiftRepository,
    private val settingsRepository: HomeSettingsRepository,
    private val mapperToUi: ScheduleHomeToUiMapper,
    private val statusController: TimeTaskStatusController,
    private val dateManager: DateManager,
    private val timeTaskAlarmManager: TimeTaskAlarmManager,
    navigator: Navigator
) : BaseViewModel<HomeViewState, HomeEvent>(navigator) {

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
                    settingsRepository.fetchTasksSettings().collectAndHandle(
                        onFailure = { e -> showError(e) },
                        onSuccess = { task -> setUpSettings(task) }
                    )
                }

            }

            is HomeEvent.ChangeTaskDoneStateButton -> {

            }

            is HomeEvent.CreateSchedule -> {

            }


            is HomeEvent.LoadSchedule -> {

            }

            is HomeEvent.PressAddTimeTaskButton -> {

            }

            is HomeEvent.PressEditTimeTaskButton -> {

            }

            is HomeEvent.PressOverviewButton -> {
            }

            is HomeEvent.PressViewToggleButton -> {

            }

            is HomeEvent.SetShowDialog -> {
                _isDateDialogShown.value = event.isShow
            }

            is HomeEvent.TimeTaskShiftDown -> {


            }

            is HomeEvent.TimeTaskShiftUp -> {


            }
        }
    }

    private fun showError(e: Throwable) {
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

}