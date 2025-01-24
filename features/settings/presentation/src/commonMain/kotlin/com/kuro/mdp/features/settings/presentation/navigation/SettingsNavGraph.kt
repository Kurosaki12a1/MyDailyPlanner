package com.kuro.mdp.features.settings.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.kuro.mdp.features.settings.presentation.theme.SettingsTheme
import com.kuro.mdp.features.settings.presentation.ui.categories.CategoriesScreen
import com.kuro.mdp.features.settings.presentation.ui.donate.DonateScreen
import com.kuro.mdp.features.settings.presentation.ui.settings.SettingsScreen
import com.kuro.mdp.shared.presentation.navigation.destination.Destination
import com.kuro.mdp.shared.presentation.navigation.graph.NavigationGraph

/**
 * Created by: minhthinh.h on 12/25/2024
 *
 * Description:
 */
fun NavGraphBuilder.settingsNavGraph() {
    navigation<NavigationGraph.SettingsGraph>(
        startDestination = Destination.Settings
    ) {
        composable<Destination.Donate> {
            SettingsTheme {
                DonateScreen()
            }
        }
        composable<Destination.Settings> {
            SettingsTheme {
                SettingsScreen()
            }
        }
        composable<Destination.Categories> {
            SettingsTheme {
                CategoriesScreen()
            }
        }
        composable<Destination.Templates> {

        }
    }
}