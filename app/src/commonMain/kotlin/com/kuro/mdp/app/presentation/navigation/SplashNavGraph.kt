package com.kuro.mdp.app.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.kuro.mdp.app.presentation.screen.splash.SplashScreen
import com.kuro.mdp.shared.presentation.navigation.destination.Destination
import com.kuro.mdp.shared.presentation.navigation.graph.NavigationGraph

/**
 * Created by: minhthinh.h on 12/10/2024
 *
 * Description:
 */
fun NavGraphBuilder.splashNavGraph() {
    navigation<NavigationGraph.SplashGraph>(
        startDestination = Destination.Splash
    ) {
        composable<Destination.Splash> {
           SplashScreen()
        }
    }
}