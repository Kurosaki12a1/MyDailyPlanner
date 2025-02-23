package com.kuro.mdp.features.overview.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.kuro.mdp.features.overview.presentation.theme.OverViewTheme
import com.kuro.mdp.features.overview.presentation.ui.details.DetailsScreen
import com.kuro.mdp.features.overview.presentation.ui.overview.OverViewScreen
import com.kuro.mdp.shared.presentation.navigation.destination.Destination
import com.kuro.mdp.shared.presentation.navigation.graph.NavigationGraph

/**
 * Created by: minhthinh.h on 1/21/2025
 *
 * Description:
 */
fun NavGraphBuilder.overviewNavGraph() {
    navigation<NavigationGraph.OverviewGraph>(
        startDestination = Destination.Overview
    ) {
        composable<Destination.Overview> {
            OverViewTheme {
                OverViewScreen()
            }
        }
        composable<Destination.Details> {
            OverViewTheme {
                DetailsScreen()
            }
        }
    }
}