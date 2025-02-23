package com.kuro.mdp.features.overview.presentation.ui.details

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.kuro.mdp.features.overview.presentation.ui.details.components.DetailsContent
import com.kuro.mdp.features.overview.presentation.ui.details.components.DetailsTopAppBar
import com.kuro.mdp.features.overview.presentation.viewmodel.DetailsViewModel
import com.kuro.mdp.shared.presentation.views.ErrorSnackBar
import org.koin.compose.viewmodel.koinViewModel

/**
 * Created by: minhthinh.h on 2/18/2025
 *
 * Description:
 */
@Composable
fun DetailsScreen(
    viewModel: DetailsViewModel = koinViewModel()
) {
    val state by viewModel.state
    val snackBarState = remember { SnackbarHostState() }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        content = { paddingValues ->
            DetailsContent(
                state = state,
                modifier = Modifier.padding(paddingValues),
                onOpenSchedule = { viewModel.dispatchEvent(DetailsEvent.OpenSchedule(it)) },
            )
        },
        topBar = {
            DetailsTopAppBar(onNavIconClick = { viewModel.dispatchEvent(DetailsEvent.PressBackButton) })
        },
        snackbarHost = {
            SnackbarHost(
                hostState = snackBarState,
                snackbar = { ErrorSnackBar(it) },
            )
        },
    )
}