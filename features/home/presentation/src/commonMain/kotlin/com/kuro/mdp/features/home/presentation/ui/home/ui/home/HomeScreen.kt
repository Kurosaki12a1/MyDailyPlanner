package com.kuro.mdp.features.home.presentation.ui.home.ui.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.kuro.mdp.features.home.domain.model.HomeError
import com.kuro.mdp.features.home.presentation.ui.home.theme.resources.LocalHomeStrings
import com.kuro.mdp.features.home.presentation.ui.home.ui.home.components.HomeContent
import com.kuro.mdp.features.home.presentation.ui.home.ui.home.components.HomeDatePicker
import com.kuro.mdp.features.home.presentation.ui.home.ui.home.components.HomeTopBar
import com.kuro.mdp.features.home.presentation.ui.home.viewmodel.HomeViewModel
import com.kuro.mdp.shared.presentation.views.ErrorSnackBar
import com.kuro.mdp.shared.utils.extensions.getLocalDateTimeNow
import com.kuro.mdp.shared.utils.extensions.startThisDay
import org.jetbrains.compose.resources.getString
import org.koin.compose.viewmodel.koinViewModel

/**
 * Created by: minhthinh.h on 12/17/2024
 *
 * Description:
 */
@Composable
internal fun HomeScreen(
    viewModel: HomeViewModel = koinViewModel()
) {
    val localHomeString = LocalHomeStrings.current
    val state by viewModel.state
    val isDateDialogShow by viewModel.isDateDialogShown
    val snackBarState = remember { SnackbarHostState() }

    Scaffold(
        topBar = {
            HomeTopBar(
                calendarIconBehavior = state.calendarButtonBehavior,
                onMenuIconClick = {},
                onOverviewIconClick = { viewModel.dispatchEvent(HomeEvent.PressOverviewButton) },
                onOpenCalendar = {
                    viewModel.dispatchEvent(HomeEvent.SetShowDialog(true))
                },
                onGoToToday = { viewModel.dispatchEvent(HomeEvent.LoadSchedule(getLocalDateTimeNow().startThisDay())) }
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackBarState) { data -> ErrorSnackBar(data) }
        },
        content = { paddingValues ->
            HomeContent(
                state = state,
                modifier = Modifier.padding(paddingValues),
                onChangeDate = { date ->
                    viewModel.dispatchEvent(HomeEvent.LoadSchedule(date))
                },
                onTimeTaskEdit = {
                    viewModel.dispatchEvent(HomeEvent.PressEditTimeTaskButton(it))
                },
                onTaskDoneChange = {
                    viewModel.dispatchEvent(HomeEvent.ChangeTaskDoneStateButton(it))
                },
                onTimeTaskAdd = { start, end ->
                    viewModel.dispatchEvent(HomeEvent.PressAddTimeTaskButton(start, end))
                },
                onCreateSchedule = {
                    viewModel.dispatchEvent(HomeEvent.CreateSchedule)
                },
                onTimeTaskIncrease = {
                    viewModel.dispatchEvent(HomeEvent.TimeTaskShiftUp(it))
                },
                onTimeTaskReduce = {
                    viewModel.dispatchEvent(HomeEvent.TimeTaskShiftDown(it))
                },
                onChangeToggleStatus = {
                    viewModel.dispatchEvent(HomeEvent.PressViewToggleButton(it))
                }
            )
        }
    )

    LaunchedEffect(state.error) {
        if (state.error != null) {
            val message: String = when (state.error) {
                null -> ""
                is HomeError.ImportanceError -> getString(localHomeString.importanceError)
                is HomeError.ShiftError -> getString(localHomeString.shiftError)
                is HomeError.OtherError -> getString(localHomeString.otherError)
            }
            snackBarState.showSnackbar(
                message = message,
                withDismissAction = true
            )
            viewModel.updateState(newState = state.copy(error = null))
        }
    }

    HomeDatePicker(
        isOpenDialog = isDateDialogShow,
        onDismiss = { viewModel.dispatchEvent(HomeEvent.SetShowDialog(false)) },
        onSelectedDate = {
            viewModel.dispatchEvent(HomeEvent.SetShowDialog(false))
            viewModel.dispatchEvent(HomeEvent.LoadSchedule(it))
        }
    )

    LaunchedEffect(Unit) {
        viewModel.dispatchEvent(HomeEvent.LoadSchedule(state.currentDate))
    }
}