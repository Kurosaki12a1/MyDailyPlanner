package com.kuro.mdp.features.analytics.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.kuro.mdp.features.analytics.presentation.theme.AnalyticsTheme
import com.kuro.mdp.features.analytics.presentation.ui.analytics.AnalyticsScreen
import com.kuro.mdp.shared.presentation.navigation.destination.Destination
import com.kuro.mdp.shared.presentation.navigation.graph.NavigationGraph

/**
 * Created by: minhthinh.h on 2/18/2025
 *
 * Description:
 */
fun NavGraphBuilder.analyticsNavGraph() {
    navigation<NavigationGraph.AnalyticsGraph>(
        startDestination = Destination.Analytics::class,
    ) {
        composable<Destination.Analytics> {
            AnalyticsTheme {
                AnalyticsScreen()
            }
        }
    }
}