package com.kuro.mdp.shared.presentation.screenmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kuro.mdp.shared.presentation.navigation.destination.Destination
import com.kuro.mdp.shared.presentation.navigation.graph.NavigationGraph
import com.kuro.mdp.shared.presentation.navigation.navigator.Navigator
import com.kuro.mdp.shared.presentation.screenmodel.contract.BaseEvent
import com.kuro.mdp.shared.presentation.screenmodel.contract.BaseViewState
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

/**
 * Created by: minhthinh.h on 12/20/2024
 *
 * Description:
 */
abstract class BaseViewModel<S : BaseViewState, E : BaseEvent>(
    private val navigator: Navigator
) : ViewModel() {

    private val _state = mutableStateOf(initState())
    val state: State<S>
        get() = _state

    private val event = MutableSharedFlow<E>(replay = 0, extraBufferCapacity = Int.MAX_VALUE)

    private var eventJob: Job? = null

    init {
        collectEvent()
    }

    fun dispatchEvent(event: E) {
        viewModelScope.launch {
            this@BaseViewModel.event.emit(event)
        }
    }

    fun updateState(newState: S) {
        _state.value = newState
    }

    /**
     * Navigate to certain given destination
     * @param route Route which you want navigate to
     * @param popUpToRoute define a route up to which the backstack should be popped.
     * @param inclusive work with popUpToRoute, true -> remove backstack until popUpToRoute include
     * popUpToRoute, false -> popUp until to popUpToRoute
     * @param isSingleTop make sure only one instance of destination exists
     */
    fun navigateTo(
        route: Destination,
        popUpToRoute: Destination? = null,
        inclusive: Boolean = false,
        isSingleTop: Boolean = true,
    ) {
        viewModelScope.launch {
            navigator.navigateTo(route, popUpToRoute, inclusive, isSingleTop)
        }
    }

    /**
     * Navigate to certain given graph
     * @param route Route which you want navigate to
     */
    fun navigateTo(
        route: NavigationGraph
    ) {
        viewModelScope.launch {
            navigator.navigateToGraph(route)
        }
    }

    /**
     * Remove current destination from backstack, if backstack only 1 item, it won't remove
     */
    fun popBackStack() {
        viewModelScope.launch {
            navigator.navigateBack(route = null, inclusive = false)
        }
    }

    /**
     * Pop up to route destination should be popped
     * @param route Route you want popUp to
     * @param inclusive if true, the destination associated with the route will also be removed
     */
    fun popUpTo(
        route: Destination,
        inclusive: Boolean = false,
    ) {
        viewModelScope.launch {
            navigator.navigateBack(route, inclusive)
        }
    }

    private fun collectEvent() {
        eventJob?.cancel()
        eventJob = viewModelScope.launch {
            this@BaseViewModel.event.collect { event -> handleEvent(event) }
        }
    }

    protected abstract fun showError(e: Throwable)

    abstract fun initState(): S

    abstract fun handleEvent(event: E)

    override fun onCleared() {
        super.onCleared()
        eventJob?.cancel()
    }


}