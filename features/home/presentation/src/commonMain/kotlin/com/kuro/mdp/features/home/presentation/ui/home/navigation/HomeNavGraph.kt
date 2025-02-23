package com.kuro.mdp.features.home.presentation.ui.home.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.kuro.mdp.features.home.presentation.ui.home.theme.HomeTheme
import com.kuro.mdp.features.home.presentation.ui.home.ui.editor.EditorScreen
import com.kuro.mdp.features.home.presentation.ui.home.ui.home.HomeScreen
import com.kuro.mdp.shared.presentation.navigation.destination.Destination
import com.kuro.mdp.shared.presentation.navigation.graph.NavigationGraph

/**
 * Created by: minhthinh.h on 12/10/2024
 *
 * Description:
 */
fun NavGraphBuilder.homeNavGraph() {
    navigation<NavigationGraph.HomeGraph>(
        startDestination = Destination.Home::class,
    ) {
        composable<Destination.Home> {
            HomeTheme {
                HomeScreen()
            }
        }
        composable<Destination.EditorRoute> {
            HomeTheme {
                EditorScreen()
            }
        }
    }
}