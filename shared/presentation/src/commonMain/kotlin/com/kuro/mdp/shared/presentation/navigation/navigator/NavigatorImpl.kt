package com.kuro.mdp.shared.presentation.navigation.navigator

import com.kuro.mdp.shared.presentation.navigation.destination.Destination
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow

/**
 * Created by: minhthinh.h on 12/10/2024
 *
 * Description:
 */
class NavigatorImpl : Navigator {

    private val _navigationFlow = MutableSharedFlow<NavigationIntent>(
        replay = 0,
        extraBufferCapacity = Int.MAX_VALUE,
        onBufferOverflow = BufferOverflow.DROP_LATEST
    )
    override val navigationFlow: SharedFlow<NavigationIntent>
        get() = _navigationFlow.asSharedFlow()

    override suspend fun navigateBack(route: Destination?, inclusive: Boolean) {
        _navigationFlow.emit(
            NavigationIntent.NavigateBack(
                route = route,
                inclusive = inclusive
            )
        )
    }

    override fun tryNavigateBack(route: Destination?, inclusive: Boolean) {
        _navigationFlow.tryEmit(
            NavigationIntent.NavigateBack(
                route = route,
                inclusive = inclusive
            )
        )
    }

    override suspend fun navigateTo(route: Destination, popUpToRoute: Destination?, inclusive: Boolean, isSingleTop: Boolean) {
        _navigationFlow.emit(
            NavigationIntent.NavigateTo(
                route = route,
                popUpToRoute = popUpToRoute,
                inclusive = inclusive,
                isSingleTop = isSingleTop
            )
        )
    }

    override fun tryNavigateTo(route: Destination, popUpToRoute: Destination?, inclusive: Boolean, isSingleTop: Boolean) {
        _navigationFlow.tryEmit(
            NavigationIntent.NavigateTo(
                route = route,
                popUpToRoute = popUpToRoute,
                inclusive = inclusive,
                isSingleTop = isSingleTop
            )
        )
    }
}