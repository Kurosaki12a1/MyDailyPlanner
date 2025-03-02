package com.kuro.mdp.app.presentation.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.kuro.mdp.app.domain.models.MainAction
import com.kuro.mdp.app.domain.use_case.MainUseCase
import com.kuro.mdp.app.presentation.ui.main.MainEvent
import com.kuro.mdp.app.presentation.ui.main.MainViewState
import com.kuro.mdp.shared.presentation.navigation.graph.NavigationGraph
import com.kuro.mdp.shared.presentation.navigation.navigator.NavigationIntent
import com.kuro.mdp.shared.presentation.navigation.navigator.Navigator
import com.kuro.mdp.shared.presentation.screenmodel.BaseViewModel
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

/**
 * Created by: minhthinh.h on 1/3/2025
 *
 * Description:
 */
class MainViewModel(
    private val mainUseCase: MainUseCase,
    private val navigator: Navigator
) : BaseViewModel<MainViewState, MainEvent, MainAction>(navigator) {
    val navigationFlow: SharedFlow<NavigationIntent>
        get() = navigator.navigationFlow

    private val _graphBackStack: MutableState<List<String>> = mutableStateOf(listOf(NavigationGraph.HomeGraph.toString()))
    val graphBackStack: State<List<String>>
        get() = _graphBackStack

    init {
        dispatchEvent(MainEvent.Init)
    }

    /**
     * Adds the next graph to the back stack, ensuring no duplicates and the current graph is at the end.
     *
     * @param currentGraph The current graph in the back stack, which can be null.
     * @param nextGraph The next graph to be added to the back stack, which is a non-null string.
     *
     */
    fun addGraph(currentGraph: String?, nextGraph: String) {
        // If the current graph is null, return immediately and do nothing
        if (currentGraph == null) return

        // Initialize the result back stack with the current value of _graphBackStack
        // If _graphBackStack is empty, initialize it with the Home Graph
        var resultBackStack = _graphBackStack.value.ifEmpty { listOf(NavigationGraph.HomeGraph.toString()) }

        // If the next graph is already in the back stack and it's not the Home Graph,
        // remove it from the back stack to ensure it's not duplicated
        if (nextGraph in resultBackStack && nextGraph != NavigationGraph.HomeGraph.toString()) {
            resultBackStack = resultBackStack - nextGraph
        }

        // Ensure that the current graph is at the end of the back stack if it's not the Home Graph
        // This ensures that the current graph is always the most recent entry
        if (currentGraph != NavigationGraph.HomeGraph.toString()) {
            resultBackStack = (resultBackStack - currentGraph) + currentGraph
        }

        // Update the value of _graphBackStack with the new result back stack
        _graphBackStack.value = resultBackStack
    }

    /**
     * Remove the last element of backstack list. This will be used for back button press action.
     */
    fun removeGraph() {
        // Remove the last item from back stack list. This will be used for back button press action.
        _graphBackStack.value = _graphBackStack.value.dropLast(1)
    }

    override fun showError(e: Throwable?) {

    }

    override fun updateState(action: MainAction) {
        when (action) {
            is MainAction.UpdateEditor -> {}
            is MainAction.UpdateMainCategoryId -> {}
            is MainAction.UpdateScheduleDate -> {}
            is MainAction.UpdateSettings -> update {
                it.copy(
                    secureMode = action.secureMode,
                    isEnableDynamicColors = action.isEnableDynamicColors,
                    language = action.language,
                    theme = action.theme,
                    colors = action.colors
                )
            }
        }
    }

    override fun initState(): MainViewState = MainViewState()

    override fun handleEvent(event: MainEvent) {
        when (event) {
            is MainEvent.Init -> {
                viewModelScope.launch {
                    mainUseCase.fetchSettingsUseCase().collectAndHandleWork()
                }
            }

            is MainEvent.UpdateMainCategoryId -> {
                viewModelScope.launch {
                    mainUseCase.updateMainCategoryUseCase(event.id).collectAndHandleWork()
                }
            }

            is MainEvent.UpdateScheduleDate -> {
                viewModelScope.launch {
                    mainUseCase.updateScheduleDateUseCase(event.date).collectAndHandleWork()
                }
            }

            is MainEvent.UpdateEditor -> {
                viewModelScope.launch {
                    mainUseCase.updateEditorUseCase(event.timeTask, event.template, event.undefinedTaskId).collectAndHandleWork()
                }
            }
        }
    }

}