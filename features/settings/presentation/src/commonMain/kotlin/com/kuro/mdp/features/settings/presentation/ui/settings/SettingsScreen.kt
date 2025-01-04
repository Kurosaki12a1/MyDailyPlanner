package com.kuro.mdp.features.settings.presentation.ui.settings

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
import com.kuro.mdp.features.settings.presentation.ui.settings.components.SettingsContent
import com.kuro.mdp.features.settings.presentation.ui.settings.components.SettingsTopAppBar
import com.kuro.mdp.features.settings.presentation.viewmodel.SettingsViewModel
import com.kuro.mdp.shared.presentation.theme.LocalThemeUiType
import org.koin.compose.viewmodel.koinViewModel

/**
 * Created by: minhthinh.h on 12/25/2024
 *
 * Description:
 */
@Composable
internal fun SettingsScreen(
    viewModel: SettingsViewModel = koinViewModel()
) {
    val state by viewModel.state
    val snackBarState = remember { SnackbarHostState() }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            SettingsTopAppBar(
                onMenuButtonClick = {

                },
                onResetToDefaultClick = {

                })
        }, content = { paddingValues ->
            SettingsContent(
                state = state,
                modifier = Modifier.padding(paddingValues),
                onDonateButtonClick = {
                    viewModel.dispatchEvent(SettingsEvent.PressDonateButton)
                },
                onUpdateTasksSettings = {
                    viewModel.dispatchEvent(SettingsEvent.ChangedTasksSettings(it))
                },
                onUpdateThemeSettings = {
                    viewModel.dispatchEvent(SettingsEvent.ChangedThemeSettings(it))
                }
            )

        }, snackbarHost = {
            SnackbarHost(hostState = snackBarState)
        }
    )

    LaunchedEffect(state.failure) {
        if (state.failure != null) {
            snackBarState.showSnackbar(
                message = state.failure ?: "",
                withDismissAction = true
            )
            viewModel.dispatchEvent(SettingsEvent.ClearFailure)
        }
    }
}