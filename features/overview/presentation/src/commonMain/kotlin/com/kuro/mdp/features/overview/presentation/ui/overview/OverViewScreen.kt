package com.kuro.mdp.features.overview.presentation.ui.overview

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.kuro.mdp.features.overview.domain.model.OverViewError
import com.kuro.mdp.features.overview.presentation.theme.resources.LocalOverViewStrings
import com.kuro.mdp.features.overview.presentation.ui.overview.component.OverviewContent
import com.kuro.mdp.features.overview.presentation.ui.overview.component.OverviewTopAppBar
import com.kuro.mdp.features.overview.presentation.viewmodel.OverViewViewModel
import com.kuro.mdp.shared.presentation.views.ErrorSnackBar
import org.jetbrains.compose.resources.getString
import org.koin.compose.viewmodel.koinViewModel

/**
 * Created by: minhthinh.h on 1/20/2025
 *
 * Description:
 */
@Composable
fun OverViewScreen(
    viewModel: OverViewViewModel = koinViewModel()
) {
    val state by viewModel.state
    val localOverViewString = LocalOverViewStrings.current
    val snackBarState = remember { SnackbarHostState() }
    Scaffold(
        topBar = {
            OverviewTopAppBar(
                onMenuIconClick = {

                },
                onOpenSchedule = {
                    viewModel.dispatchEvent(OverViewEvent.OpenSchedule(null))
                }
            )
        },
        snackbarHost = {
            SnackbarHost(
                hostState = snackBarState,
                snackbar = { ErrorSnackBar(it) }
            )
        },
        content = { paddingValues ->
            OverviewContent(
                state = state,
                modifier = Modifier.fillMaxSize().padding(paddingValues),
                onRefresh = { viewModel.dispatchEvent(OverViewEvent.Refresh) },
                onOpenSchedule = {
                    viewModel.dispatchEvent(OverViewEvent.OpenSchedule(it))
                },
                onOpenAllSchedules = {
                    viewModel.dispatchEvent(OverViewEvent.OpenAllSchedules)
                },
                onAddOrUpdateTask = { viewModel.dispatchEvent(OverViewEvent.CreateOrUpdateUndefinedTask(it)) },
                onDeleteTask = {
                    viewModel.dispatchEvent(OverViewEvent.DeleteUndefinedTask(it))
                },
                onExecuteTask = { date, task ->
                    viewModel.dispatchEvent(OverViewEvent.ExecuteUndefinedTask(date, task))
                },
            )

        }
    )

    LaunchedEffect(state.error) {
        if (state.error != null) {
            val message: String = when (state.error) {
                null -> ""
                is OverViewError.ImportanceError -> getString(localOverViewString.importanceError)
                is OverViewError.ShiftError -> getString(localOverViewString.shiftError)
                is OverViewError.OtherError -> getString(localOverViewString.otherError)
            }
            snackBarState.showSnackbar(
                message = message,
                withDismissAction = true
            )
            viewModel.updateState(newState = state.copy(error = null))
        }
    }
}