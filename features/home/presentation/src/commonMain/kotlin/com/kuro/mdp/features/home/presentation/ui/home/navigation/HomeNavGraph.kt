package com.kuro.mdp.features.home.presentation.ui.home.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.kuro.mdp.features.home.presentation.ui.home.theme.HomeTheme
import com.kuro.mdp.features.home.presentation.ui.home.ui.HomeScreen
import com.kuro.mdp.shared.presentation.navigation.destination.Destination
import com.kuro.mdp.shared.presentation.navigation.graph.NavigationGraph
import com.kuro.mdp.shared.utils.extensions.mapToDate
import com.kuro.mdp.shared.utils.functional.Constants.Arguments.SCHEDULE_DATE

/**
 * Created by: minhthinh.h on 12/10/2024
 *
 * Description:
 */
fun NavGraphBuilder.homeNavGraph() {
    navigation<NavigationGraph.HomeGraph>(
        startDestination = Destination.Home(),
    ) {
        composable<Destination.Home> {
            val scheduleDate = it.arguments?.getLong(SCHEDULE_DATE)
            HomeTheme {
                HomeScreen(
                    scheduleDate = scheduleDate?.mapToDate()
                )
            }
        }
    }
}