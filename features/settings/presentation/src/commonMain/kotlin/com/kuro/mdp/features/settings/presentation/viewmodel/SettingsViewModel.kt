package com.kuro.mdp.features.settings.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.kuro.mdp.features.settings.domain.use_case.SettingsUseCase
import com.kuro.mdp.features.settings.presentation.mappers.mapToDomain
import com.kuro.mdp.features.settings.presentation.mappers.mapToUi
import com.kuro.mdp.features.settings.presentation.ui.settings.SettingsEvent
import com.kuro.mdp.features.settings.presentation.ui.settings.SettingsViewState
import com.kuro.mdp.shared.presentation.navigation.destination.Destination
import com.kuro.mdp.shared.presentation.navigation.navigator.Navigator
import com.kuro.mdp.shared.presentation.screenmodel.BaseViewModel
import com.kuro.mdp.shared.utils.functional.collectAndHandle
import kotlinx.coroutines.launch

/**
 * Created by: minhthinh.h on 12/26/2024
 *
 * Description:
 */
internal class SettingsViewModel(
    private val settingsUseCase: SettingsUseCase,
    navigator: Navigator
) : BaseViewModel<SettingsViewState, SettingsEvent>(navigator) {

    init {
        dispatchEvent(SettingsEvent.Init)
    }


    override fun initState(): SettingsViewState = SettingsViewState()

    override fun handleEvent(event: SettingsEvent) {
        when (event) {
            is SettingsEvent.ChangedTasksSettings -> {
                viewModelScope.launch {
                    settingsUseCase.updateTasksSettingsUseCase(event.tasksSettings.mapToDomain()).collectAndHandle(
                        onFailure = { showError(it) }
                    )
                }
            }

            is SettingsEvent.ChangedThemeSettings -> {
                viewModelScope.launch {
                    settingsUseCase.updateThemeSettingsUseCase(event.themeSettings.mapToDomain()).collectAndHandle(
                        onFailure = { showError(it) }
                    )
                }
            }

            is SettingsEvent.Init -> {
                viewModelScope.launch {
                    settingsUseCase.loadAllSettingsUseCase().collectAndHandle(
                        onFailure = {
                            updateState(state.value.copy(failure = it.message))
                        },
                        onSuccess = { settings ->
                            updateState(
                                state.value.copy(
                                    themeSettings = settings.mapToUi().themeSettings,
                                    tasksSettings = settings.mapToUi().tasksSettings,
                                    failure = null
                                )
                            )
                        }
                    )
                }
            }

            is SettingsEvent.PressDonateButton -> {
                navigateTo(Destination.Donate)
            }

            is SettingsEvent.ClearFailure -> {
                updateState(state.value.copy(failure = null))
            }

            is SettingsEvent.ResetToDefault -> {
                viewModelScope.launch {
                    settingsUseCase.resetToDefaultUseCase().collectAndHandle(
                        onFailure = { updateState(state.value.copy(failure = it.message)) }
                    )
                }
            }

            is SettingsEvent.OpenCategoryScreen -> {
                navigateTo(Destination.Categories)
            }
            is SettingsEvent.OpenTemplateScreen -> {

            }
        }
    }

    override fun showError(e: Throwable) {
        updateState(state.value.copy(failure = e.message))
    }
}