package com.kuro.mdp.shared.presentation.navigation.graph

import com.kuro.mdp.shared.utils.functional.Constants
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Created by: minhthinh.h on 12/10/2024
 *
 * Description:
 */
@Serializable
sealed class NavigationGraph(val mainDestination: String) {
    @Serializable
    @SerialName(Constants.NavigationGraph.MAIN)
    data object MainGraph : NavigationGraph(mainDestination = "")

    @Serializable
    @SerialName(Constants.NavigationGraph.HOME)
    data object HomeGraph : NavigationGraph(mainDestination = Constants.Destination.HOME)

    @Serializable
    @SerialName(Constants.NavigationGraph.ANALYTICS)
    data object AnalyticsGraph : NavigationGraph(mainDestination = Constants.Destination.ANALYTICS)

    @Serializable
    @SerialName(Constants.NavigationGraph.SETTINGS)
    data object SettingsGraph : NavigationGraph(mainDestination = Constants.Destination.SETTINGS)

    @Serializable
    @SerialName(Constants.NavigationGraph.OVERVIEW)
    data object OverviewGraph : NavigationGraph(mainDestination = Constants.Destination.OVERVIEW)
}