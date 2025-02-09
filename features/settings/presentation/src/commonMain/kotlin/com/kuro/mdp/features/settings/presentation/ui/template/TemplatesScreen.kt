package com.kuro.mdp.features.settings.presentation.ui.template

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.kuro.mdp.features.settings.domain.mapper.categories.mapToDomain
import com.kuro.mdp.features.settings.domain.mapper.templates.mapToUi
import com.kuro.mdp.features.settings.presentation.theme.SettingsTheme
import com.kuro.mdp.features.settings.presentation.ui.template.components.TemplatesContent
import com.kuro.mdp.features.settings.presentation.ui.template.components.TemplatesTopAppBar
import com.kuro.mdp.features.settings.presentation.viewmodel.TemplatesViewModel
import com.kuro.mdp.shared.presentation.extensions.string
import com.kuro.mdp.shared.presentation.views.ErrorSnackBar
import com.kuro.mdp.shared.presentation.views.TemplateEditorDialog
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun TemplatesScreen(
    viewModel: TemplatesViewModel = koinViewModel()
) {
    val state by viewModel.state
    val snackBarState = remember { SnackbarHostState() }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        content = { paddingValues ->
            TemplatesContent(
                state = state,
                modifier = Modifier.padding(paddingValues),
                onChangeSortedType = { viewModel.dispatchEvent(TemplatesEvent.UpdatedSortedType(it)) },
                onDeleteTemplate = { viewModel.dispatchEvent(TemplatesEvent.DeleteTemplate(it.templateId)) },
                onUpdateTemplate = { viewModel.dispatchEvent(TemplatesEvent.UpdateTemplate(it)) },
                onRestartRepeat = { viewModel.dispatchEvent(TemplatesEvent.RestartTemplateRepeat(it)) },
                onStopRepeat = { viewModel.dispatchEvent(TemplatesEvent.StopTemplateRepeat(it)) },
                onAddRepeatTemplate = { time, template ->
                    viewModel.dispatchEvent(TemplatesEvent.AddRepeatTemplate(time, template))
                },
                onDeleteRepeatTemplate = { time, template ->
                    viewModel.dispatchEvent(TemplatesEvent.DeleteRepeatTemplate(time, template))
                }
            )
        },
        topBar = {
            TemplatesTopAppBar(
                onButtonClick = { viewModel.dispatchEvent(TemplatesEvent.NavigateToSettings) },
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackBarState) {
                ErrorSnackBar(snackBarData = it)
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                shape = CircleShape,
                onClick = { viewModel.dispatchEvent(TemplatesEvent.ShowTemplateCreator(true)) },
                content = {
                    Text(
                        text = SettingsTheme.strings.addTemplatesFabTitle.string(),
                        style = MaterialTheme.typography.titleLarge,
                    )
                },
            )
        }
    )

    if (state.isShowTemplateCreator) {
        TemplateEditorDialog(
            categories = state.categories.map { it.mapToDomain() },
            model = null,
            onDismiss = { viewModel.dispatchEvent(TemplatesEvent.ShowTemplateCreator(false)) },
            onConfirm = { template ->
                viewModel.dispatchEvent(TemplatesEvent.AddTemplate(template.mapToUi()))
                viewModel.dispatchEvent(TemplatesEvent.ShowTemplateCreator(false))
            },
        )
    }

    LaunchedEffect(state.failure) {
        if (state.failure != null) {
            snackBarState.showSnackbar(
                message = state.failure ?: "", withDismissAction = true
            )
            viewModel.dispatchEvent(TemplatesEvent.ClearFailure)
        }
    }
}