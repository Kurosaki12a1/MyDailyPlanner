package com.kuro.mdp.features.settings.presentation.ui.categories

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
import com.kuro.mdp.features.settings.presentation.ui.categories.components.CategoriesContent
import com.kuro.mdp.features.settings.presentation.ui.categories.components.CategoriesTopAppBar
import com.kuro.mdp.features.settings.presentation.ui.categories.components.SubCategoryEditorDialog
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
    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = { CategoriesTopAppBar(onBack = { viewModel.popBackStack() }) },
        content = { paddingValues ->
            CategoriesContent(
                state = state,
                modifier = Modifier.padding(paddingValues),
                onAddMainCategory = { viewModel.dispatchEvent(CategoriesEvent.AddMainCategory(it)) },
                onAddSubCategory = {
                    viewModel.dispatchEvent(
                        CategoriesEvent.ShowSubCategoryDialog(true)
                    )
                },
                onChangeMainCategory = {
                    viewModel.dispatchEvent(
                        CategoriesEvent.ChangeMainCategory(it)
                    )
                },
                onMainCategoryUpdate = {
                    viewModel.dispatchEvent(
                        CategoriesEvent.UpdateMainCategory(it)
                    )
                },
                onSubCategoryUpdate = { viewModel.dispatchEvent(CategoriesEvent.UpdateSubCategory(it)) },
                onMainCategoryDelete = {
                    viewModel.dispatchEvent(
                        CategoriesEvent.DeleteMainCategory(it)
                    )
                },
                onSubCategoryDelete = { viewModel.dispatchEvent(CategoriesEvent.DeleteSubCategory(it)) },
                onRestoreDefaultCategories = { viewModel.dispatchEvent(CategoriesEvent.RestoreDefaultCategories) },
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackBarState)
        })

    if (state.isShowSubCategoryDialog) {
        val mainCategory = state.selectedMainCategory
        if (mainCategory != null) {
            SubCategoryEditorDialog(
                mainCategory = mainCategory,
                onDismiss = { viewModel.dispatchEvent(CategoriesEvent.ShowSubCategoryDialog(false)) },
                onConfirm = { name ->
                    viewModel.dispatchEvent(CategoriesEvent.AddSubCategory(name, mainCategory))
                    viewModel.dispatchEvent(CategoriesEvent.ShowSubCategoryDialog(false))
                },
            )
        }

    }

    LaunchedEffect(state.failure) {
        if (state.failure != null) {
            snackBarState.showSnackbar(
                message = state.failure ?: "", withDismissAction = true
            )
            viewModel.dispatchEvent(CategoriesEvent.ClearFailure)
        }
    }
}