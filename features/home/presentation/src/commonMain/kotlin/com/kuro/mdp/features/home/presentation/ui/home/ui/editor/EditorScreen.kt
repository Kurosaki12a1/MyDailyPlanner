package com.kuro.mdp.features.home.presentation.ui.home.ui.editor

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.kuro.mdp.features.home.presentation.Home.home.Home.editor.EditorEvent
import com.kuro.mdp.features.home.presentation.ui.home.mappers.mapToMessage
import com.kuro.mdp.features.home.presentation.ui.home.theme.resources.LocalHomeStrings
import com.kuro.mdp.features.home.presentation.ui.home.ui.editor.components.EditorContent
import com.kuro.mdp.features.home.presentation.ui.home.ui.editor.components.EditorTopAppBar
import com.kuro.mdp.features.home.presentation.ui.home.ui.editor.components.TemplatesBottomSheet
import com.kuro.mdp.features.home.presentation.ui.home.ui.editor.components.UndefinedTasksBottomSheet
import com.kuro.mdp.features.home.presentation.ui.home.viewmodel.EditorViewModel
import com.kuro.mdp.shared.presentation.views.ErrorSnackBar
import com.kuro.mdp.shared.utils.functional.TimeRange
import org.jetbrains.compose.resources.getString
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
    val homeStrings = LocalHomeStrings.current
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
        content = { paddingValues ->
            EditorContent(
                state = state,
                modifier = Modifier.padding(paddingValues),
                onCategoriesChange = { main, sub -> viewModel.dispatchEvent(EditorEvent.ChangeCategories(main, sub)) },
                onNoteChange = { viewModel.dispatchEvent(EditorEvent.ChangeNote(it)) },
                onAddSubCategory = { viewModel.dispatchEvent(EditorEvent.AddSubCategory(it)) },
                onTimeRangeChange = { viewModel.dispatchEvent(EditorEvent.ChangeTime(it)) },
                onChangeParameters = { viewModel.dispatchEvent(EditorEvent.ChangeParameters(it)) },
                onEditCategory = { viewModel.dispatchEvent(EditorEvent.NavigateToCategoryEditor(it)) },
                onEditSubCategory = { viewModel.dispatchEvent(EditorEvent.NavigateToSubCategoryEditor(it)) },
                onControlTemplate = { viewModel.dispatchEvent(EditorEvent.PressControlTemplateButton) },
                onCreateTemplate = { viewModel.dispatchEvent(EditorEvent.CreateTemplate) },
                onSaveClick = { viewModel.dispatchEvent(EditorEvent.PressSaveButton) },
                onCancelClick = { viewModel.dispatchEvent(EditorEvent.PressBackButton) },
            )
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

    LaunchedEffect(state.error) {
        when (val error = state.error) {
            null -> {
                // Do nothing bro
            }

            is EditorError.ShowError -> {
                snackBarState.showSnackbar(message = getString(error.mapToMessage(homeStrings)))
                viewModel.dispatchEvent(EditorEvent.ClearFailure)
            }

            is EditorError.ShowOverLayError -> {
                val result = snackBarState.showSnackbar(
                    message = getString(error.mapToMessage(homeStrings)),
                    withDismissAction = true,
                    actionLabel = getString(homeStrings.correctOverlayTitle),
                )
                if (result == SnackbarResult.ActionPerformed) {
                    val currentTimeRange = error.currentTimeRange
                    val start = error.startOverlay ?: currentTimeRange.from
                    val end = error.endOverlay ?: currentTimeRange.to
                    viewModel.dispatchEvent(EditorEvent.ChangeTime(TimeRange(start, end)))
                }
                viewModel.dispatchEvent(EditorEvent.ClearFailure)
            }
        }
    }
}