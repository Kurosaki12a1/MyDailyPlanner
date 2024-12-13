package com.kuro.mdp.shared.presentation.navigation.graph

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Created by: minhthinh.h on 12/10/2024
 *
 * Description:
 */
@Serializable
sealed class NavigationGraph() {
    @Serializable
    @SerialName("Splash")
    data object SplashGraph : NavigationGraph()
    @Serializable
    @SerialName("Home")
    data object HomeGraph : NavigationGraph()
    @Serializable
    @SerialName("Analytics")
    data object AnalyticsGraph : NavigationGraph()
    @Serializable
    @SerialName("Settings")
    data object SettingsGraph : NavigationGraph()
    @Serializable
    @SerialName("Overview")
    data object OverviewGraph : NavigationGraph()
    @Serializable
    @SerialName("Categories")
    data object CategoriesGraph : NavigationGraph()
}