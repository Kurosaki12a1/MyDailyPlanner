package com.kuro.mdp.app.presentation.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.kuro.mdp.app.presentation.ui.splash.SplashScreen
import com.kuro.mdp.features.analytics.presentation.navigation.analyticsNavGraph
import com.kuro.mdp.features.home.presentation.ui.home.navigation.homeNavGraph
import com.kuro.mdp.features.overview.presentation.navigation.overviewNavGraph
import com.kuro.mdp.features.settings.presentation.navigation.settingsNavGraph
import com.kuro.mdp.shared.presentation.navigation.destination.Destination
import com.kuro.mdp.shared.presentation.navigation.graph.NavigationGraph

/**
 * Created by: minhthinh.h on 12/10/2024
 *
 * Description:
 */

@Composable
fun AppNavGraph(
    modifier: Modifier = Modifier,
    navHostController: NavHostController
) {
    NavHost(
        modifier = modifier,
        navController = navHostController,
        startDestination = Destination.Splash,
        route = NavigationGraph.MainGraph::class,
        enterTransition = { slideInHorizontally(tween(500)) + fadeIn(tween(500)) },
        exitTransition = { slideOutHorizontally(tween(500)) + fadeOut(tween(500)) },
    ) {
        composable<Destination.Splash> {
            SplashScreen()
        }
        homeNavGraph()
        analyticsNavGraph()
        overviewNavGraph()
        settingsNavGraph()
    }
}