package com.kuro.mdp.features.settings.presentation.ui.categories

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.kuro.mdp.features.settings.presentation.ui.categories.components.CategoriesTopAppBar
import com.kuro.mdp.features.settings.presentation.viewmodel.CategoriesViewModel
import org.koin.compose.viewmodel.koinViewModel

/**
 * Created by: minhthinh.h on 1/24/2025
 *
 * Description:
 */
@Composable
internal fun CategoriesScreen(
    viewModel: CategoriesViewModel = koinViewModel()
) {
    val state by viewModel.state
    val snackBarState = remember { SnackbarHostState() }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { CategoriesTopAppBar(onBack = { viewModel.popBackStack() }) },
        content = {

        },
        snackbarHost = {
            SnackbarHost(hostState = snackBarState)
        }
    )
}