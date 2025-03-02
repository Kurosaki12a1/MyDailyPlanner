package com.kuro.mdp.features.settings.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.kuro.mdp.features.settings.domain.model.actions.SettingsAction
import com.kuro.mdp.features.settings.domain.use_case.settings.SettingsUseCase
import com.kuro.mdp.features.settings.presentation.mappers.mapToDomain
import com.kuro.mdp.features.settings.presentation.mappers.mapToUi
import com.kuro.mdp.features.settings.presentation.ui.settings.SettingsEvent
import com.kuro.mdp.features.settings.presentation.ui.settings.SettingsViewState
import com.kuro.mdp.shared.presentation.navigation.destination.Destination
import com.kuro.mdp.shared.presentation.navigation.navigator.Navigator
import com.kuro.mdp.shared.presentation.screenmodel.BaseViewModel
import kotlinx.coroutines.launch

/**
 * Created by: minhthinh.h on 12/26/2024
 *
 * Description:
 */
internal class SettingsViewModel(
    private val settingsUseCase: SettingsUseCase,
    navigator: Navigator
) : BaseViewModel<SettingsViewState, SettingsEvent, SettingsAction>(navigator) {

    init {
        dispatchEvent(SettingsEvent.Init)
    }

    override fun showError(e: Throwable?) {
        update { it.copy(failure = e?.message) }
    }

    override fun updateState(action: SettingsAction) {
        when (action) {
            is SettingsAction.ChangeAllSettings -> {
                update {
                    it.copy(
                        themeSettings = action.settings.mapToUi().themeSettings,
                        tasksSettings = action.settings.mapToUi().tasksSettings,
                        failure = null
                    )
                }
            }

            is SettingsAction.ChangedTasksSettings -> {}

            is SettingsAction.ChangedThemeSettings -> {}

            is SettingsAction.ResetToDefault -> {}
        }
    }

    override fun initState(): SettingsViewState = SettingsViewState()

    override fun handleEvent(event: SettingsEvent) {
        when (event) {
            is SettingsEvent.ChangedTasksSettings -> {
                viewModelScope.launch {
                    settingsUseCase.updateTasksSettingsUseCase(event.tasksSettings.mapToDomain()).collectAndHandleWork()
                }
            }

            is SettingsEvent.ChangedThemeSettings -> {
                viewModelScope.launch {
                    settingsUseCase.updateThemeSettingsUseCase(event.themeSettings.mapToDomain()).collectAndHandleWork()
                }
            }

            is SettingsEvent.Init -> {
                viewModelScope.launch {
                    settingsUseCase.loadAllSettingsUseCase().collectAndHandleWork()
                }
            }

            is SettingsEvent.PressDonateButton -> {
                navigateTo(Destination.Donate)
            }

            is SettingsEvent.ClearFailure -> {
                showError(null)
            }

            is SettingsEvent.ResetToDefault -> {
                viewModelScope.launch {
                    settingsUseCase.resetToDefaultUseCase().collectAndHandleWork()
                }
            }

            is SettingsEvent.OpenCategoryScreen -> {
                navigateTo(Destination.Categories())
            }

            is SettingsEvent.OpenTemplateScreen -> {
                navigateTo(Destination.Templates)
            }
        }
    }
}