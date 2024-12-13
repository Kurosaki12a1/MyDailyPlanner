package com.kuro.mdp.shared.presentation.navigation.navigator

import com.kuro.mdp.shared.presentation.navigation.destination.Destination
import kotlinx.coroutines.flow.SharedFlow

/**
 * Created by: minhthinh.h on 12/10/2024
 *
 * Description:
 */
interface Navigator {
    val navigationFlow: SharedFlow<NavigationIntent>

    suspend fun navigateBack(
        route: Destination? = null,
        inclusive: Boolean = false
    )

    fun tryNavigateBack(
        route: Destination? = null,
        inclusive: Boolean = false
    )

    suspend fun navigateTo(
        route: Destination,
        popUpToRoute: Destination? = null,
        inclusive: Boolean = false,
        isSingleTop: Boolean = true
    )

    fun tryNavigateTo(
        route: Destination,
        popUpToRoute: Destination? = null,
        inclusive: Boolean = false,
        isSingleTop: Boolean = true
    )

}

sealed class NavigationIntent {
    data class NavigateBack(
        val route: Destination? = null,
        val inclusive: Boolean = false,
    ) : NavigationIntent()

    data class NavigateTo(
        val route: Destination,
        val popUpToRoute: Destination? = null,
        val inclusive: Boolean = false,
        val isSingleTop: Boolean = false,
    ) : NavigationIntent()
}
