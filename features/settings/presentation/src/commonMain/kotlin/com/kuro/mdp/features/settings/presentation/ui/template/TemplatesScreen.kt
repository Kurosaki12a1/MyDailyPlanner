package com.kuro.mdp.features.settings.presentation.ui.template

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.kuro.mdp.features.settings.domain.mapper.categories.mapToDomain
import com.kuro.mdp.features.settings.domain.mapper.templates.mapToUi
import com.kuro.mdp.features.settings.presentation.theme.SettingsTheme
import com.kuro.mdp.features.settings.presentation.ui.template.components.TemplatesTopAppBar
import com.kuro.mdp.features.settings.presentation.viewmodel.TemplatesViewModel
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
        content = {

        },
        topBar = {
            TemplatesTopAppBar(
                onMenuIconClick = {  },
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackBarState) {
                ErrorSnackBar(snackBarData = it)
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { viewModel.dispatchEvent(TemplatesEvent.ShowTemplateCreator(true)) },
                content = {
                    Text(
                        text = SettingsTheme.strings.addTemplatesFabTitle,
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
}