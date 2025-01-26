package com.kuro.mdp.shared.presentation.navigation.graph

import kotlinx.serialization.Serializable

/**
 * Created by: minhthinh.h on 12/10/2024
 *
 * Description:
 */
@Serializable
sealed class NavigationGraph {
    @Serializable
    data object SplashGraph : NavigationGraph()

    @Serializable
    data object HomeGraph : NavigationGraph()

    @Serializable
    data object AnalyticsGraph : NavigationGraph()

    @Serializable
    data object SettingsGraph : NavigationGraph()

    @Serializable
    data object OverviewGraph : NavigationGraph()
}