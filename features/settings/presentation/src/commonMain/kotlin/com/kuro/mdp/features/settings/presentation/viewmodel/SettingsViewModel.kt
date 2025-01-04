package com.kuro.mdp.features.settings.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.kuro.mdp.features.settings.domain.repository.MenuSettingsRepository
import com.kuro.mdp.features.settings.presentation.mappers.mapToDomain
import com.kuro.mdp.features.settings.presentation.mappers.mapToUi
import com.kuro.mdp.features.settings.presentation.ui.settings.SettingsEvent
import com.kuro.mdp.features.settings.presentation.ui.settings.SettingsViewState
import com.kuro.mdp.shared.presentation.navigation.navigator.Navigator
import com.kuro.mdp.shared.presentation.screenmodel.BaseViewModel
import com.kuro.mdp.shared.utils.functional.collectAndHandle
import com.kuro.mdp.shared.utils.functional.handle
import kotlinx.coroutines.launch

/**
 * Created by: minhthinh.h on 12/26/2024
 *
 * Description:
 */
internal class SettingsViewModel(
    private val menuSettingsRepository: MenuSettingsRepository,
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
                    menuSettingsRepository.updateTasksSettings(event.tasksSettings.mapToDomain()).handle(
                        onFailure = {
                            updateState(state.value.copy(failure = it.message))
                        },
                        onSuccess =  {
                            println("onSuccess")
                        }
                    )
                }
            }

            is SettingsEvent.ChangedThemeSettings -> {
                viewModelScope.launch {
                    menuSettingsRepository.updateThemeSettings(event.themeSettings.mapToDomain()).handle(
                        onSuccess = {
                            println("onSuccess")
                        },
                        onFailure = {
                            updateState(state.value.copy(failure = it.message))
                        }
                    )
                }
            }

            is SettingsEvent.Init -> {
                viewModelScope.launch {
                    menuSettingsRepository.fetchAllSettings().collectAndHandle(
                        onSuccess = { settings ->
                            updateState(
                                state.value.copy(
                                    themeSettings = settings.mapToUi().themeSettings,
                                    tasksSettings = settings.mapToUi().tasksSettings,
                                    failure = null
                                )
                            )
                        },
                        onFailure = {
                            updateState(state.value.copy(failure = it.message))
                        }
                    )
                }
            }

            is SettingsEvent.PressDonateButton -> {

            }

            is SettingsEvent.ClearFailure -> {
                updateState(state.value.copy(failure = null))
            }
        }
    }
}