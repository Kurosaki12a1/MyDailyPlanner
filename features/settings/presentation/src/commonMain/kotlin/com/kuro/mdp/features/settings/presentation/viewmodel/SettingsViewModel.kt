package com.kuro.mdp.features.settings.presentation.viewmodel

import com.kuro.mdp.features.settings.domain.model.TasksSettingsUi
import com.kuro.mdp.features.settings.domain.model.ThemeSettingsUi
import com.kuro.mdp.features.settings.presentation.ui.settings.SettingsEvent
import com.kuro.mdp.features.settings.presentation.ui.settings.SettingsViewState
import com.kuro.mdp.shared.presentation.navigation.navigator.Navigator
import com.kuro.mdp.shared.presentation.screenmodel.BaseViewModel

/**
 * Created by: minhthinh.h on 12/26/2024
 *
 * Description:
 */
internal class SettingsViewModel(
    navigator: Navigator
) : BaseViewModel<SettingsViewState, SettingsEvent>(navigator) {

    init {
        dispatchEvent(SettingsEvent.Init)
    }

    override fun initState(): SettingsViewState = SettingsViewState()

    override fun handleEvent(event: SettingsEvent) {
        when (event) {
            is SettingsEvent.ChangedTasksSettings -> {

            }

            is SettingsEvent.ChangedThemeSettings -> {

            }

            is SettingsEvent.Init -> {
                updateState(
                    state.value.copy(
                        themeSettings = ThemeSettingsUi(),
                        tasksSettings = TasksSettingsUi()
                    )
                )
            }

            is SettingsEvent.PressDonateButton -> {

            }

        }
    }
}