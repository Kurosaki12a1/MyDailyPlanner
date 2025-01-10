package com.kuro.mdp.app.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.kuro.mdp.app.presentation.ui.MainEvent
import com.kuro.mdp.app.presentation.ui.MainViewState
import com.kuro.mdp.features.settings.domain.repository.MenuSettingsRepository
import com.kuro.mdp.features.settings.presentation.mappers.mapToUi
import com.kuro.mdp.shared.presentation.navigation.navigator.NavigationIntent
import com.kuro.mdp.shared.presentation.navigation.navigator.Navigator
import com.kuro.mdp.shared.presentation.screenmodel.BaseViewModel
import com.kuro.mdp.shared.utils.functional.collectAndHandle
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

/**
 * Created by: minhthinh.h on 1/3/2025
 *
 * Description:
 */
class MainViewModel(
    private val menuSettingsRepository: MenuSettingsRepository,
    private val navigator: Navigator
) : BaseViewModel<MainViewState, MainEvent>(navigator) {
    val navigationFlow: SharedFlow<NavigationIntent>
        get() = navigator.navigationFlow

    init {
        dispatchEvent(MainEvent.Init)
    }

    override fun initState(): MainViewState = MainViewState()

    override fun handleEvent(event: MainEvent) {
        when (event) {
            is MainEvent.Init -> {
                viewModelScope.launch {
                    menuSettingsRepository.fetchAllSettings().collectAndHandle(
                        onSuccess = {
                            updateState(
                                state.value.copy(
                                    secureMode = it.tasksSettings.secureMode,
                                    isEnableDynamicColors = it.themeSettings.isDynamicColorEnable,
                                    language = it.themeSettings.language.mapToUi(),
                                    theme = it.themeSettings.themeColors.mapToUi(),
                                    colors = it.themeSettings.colorsType.mapToUi()
                                )
                            )
                        },
                        onFailure = {
                        }
                    )
                }
            }
        }
    }
}