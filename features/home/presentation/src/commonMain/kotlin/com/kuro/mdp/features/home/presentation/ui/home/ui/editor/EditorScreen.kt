package com.kuro.mdp.features.home.presentation.ui.home.ui.editor

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.kuro.mdp.features.home.presentation.Home.home.Home.editor.EditorEvent
import com.kuro.mdp.features.home.presentation.ui.home.ui.editor.components.EditorTopAppBar
import com.kuro.mdp.features.home.presentation.ui.home.ui.editor.components.TemplatesBottomSheet
import com.kuro.mdp.features.home.presentation.ui.home.ui.editor.components.UndefinedTasksBottomSheet
import com.kuro.mdp.features.home.presentation.ui.home.viewmodel.EditorViewModel
import com.kuro.mdp.shared.presentation.views.ErrorSnackBar
import org.koin.compose.viewmodel.koinViewModel

/**
 * Created by: minhthinh.h on 2/6/2025
 *
 * Description:
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditorScreen(
    viewModel: EditorViewModel = koinViewModel()
) {
    val state by viewModel.state
    val snackBarState = remember { SnackbarHostState() }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            EditorTopAppBar(
                actionsEnabled = !(state.editModel?.checkDateIsRepeat() ?: false),
                countUndefinedTasks = state.undefinedTasks?.size ?: 0,
                onBackIconClick = { viewModel.dispatchEvent(EditorEvent.PressBackButton) },
                onDeleteActionClick = { viewModel.dispatchEvent(EditorEvent.PressDeleteButton) },
                onOpenUndefinedTasks = { viewModel.dispatchEvent(EditorEvent.OpenUndefinedTasksSheet(true)) },
                onTemplatesActionClick = { viewModel.dispatchEvent(EditorEvent.OpenTemplatesSheet(true)) }
            )
        },
        content = {

        },
        snackbarHost = {
            SnackbarHost(hostState = snackBarState) { data -> ErrorSnackBar(data) }
        }
    )

    TemplatesBottomSheet(
        isShow = state.isTemplatesSheetOpen,
        templates = state.templates,
        currentTemplateId = state.editModel?.templateId,
        onDismiss = { viewModel.dispatchEvent(EditorEvent.OpenTemplatesSheet(false)) },
        onControlClick = { viewModel.dispatchEvent(EditorEvent.PressControlTemplateButton) },
        onChooseTemplate = { template ->
            viewModel.dispatchEvent(EditorEvent.ApplyTemplate(template))
            viewModel.dispatchEvent(EditorEvent.OpenTemplatesSheet(false))
        }
    )

    UndefinedTasksBottomSheet(
        isShow = state.isUndefinedTasksSheetOpen,
        undefinedTasks = state.undefinedTasks,
        currentUndefinedTaskId = state.editModel?.undefinedTaskId,
        onDismiss = { viewModel.dispatchEvent(EditorEvent.OpenUndefinedTasksSheet(false)) },
        onChooseUndefinedTask = {
            viewModel.dispatchEvent(EditorEvent.ApplyUndefinedTask(it))
            viewModel.dispatchEvent(EditorEvent.OpenUndefinedTasksSheet(false))
        },
    )
}