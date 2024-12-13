package com.kuro.mdp.app.presentation.screen

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.kuro.mdp.app.presentation.navigation.AppNavGraph
import com.kuro.mdp.app.presentation.screen.bottombar.TabsBottomNavigationBar
import com.kuro.mdp.app.presentation.screen.bottombar.shouldBottomBarVisible
import com.kuro.mdp.shared.presentation.navigation.navigator.NavigationIntent
import com.kuro.mdp.shared.presentation.navigation.navigator.Navigator
import com.kuro.mdp.shared.presentation.views.BottomBarItem
import com.kuro.mdp.shared.presentation.views.BottomNavigationBar
import com.kuro.mdp.shared.utils.functional.Constants
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.debounce
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject
import shared.resources.Res
import shared.resources.analyticsTabTitle
import shared.resources.categoriesDrawerTitle
import shared.resources.homeTabTitle
import shared.resources.ic_analytics
import shared.resources.ic_analytics_outline
import shared.resources.ic_categories
import shared.resources.ic_categories_outline
import shared.resources.ic_dashboard
import shared.resources.ic_dashboard_outline
import shared.resources.ic_home
import shared.resources.ic_home_outlined
import shared.resources.ic_settings
import shared.resources.ic_settings_outline
import shared.resources.overviewDrawerTitle
import shared.resources.settingsTabTitle

/**
 * Created by: minhthinh.h on 12/11/2024
 *
 * Description:
 */
@Composable
fun MainScreen(
    navigator: Navigator = koinInject()
) {

    val navController = rememberNavController()
    val currentDestination by navController.currentBackStackEntryAsState()

    NavigationEffect(
        navHostController = navController,
        navigationFlow = navigator.navigationFlow
    )

    Scaffold(
        bottomBar = {
            if (shouldBottomBarVisible(currentDestination?.destination?.parent?.route)) {
                TabsBottomNavigationBar(
                    selectedItem = currentDestination?.destination?.parent?.route,
                    onItemSelected = {
                        navController.navigate(it.destination)
                    }
                )
            }
        }
    ) {
        AppNavGraph(
            modifier = Modifier.padding(it),
            navHostController = navController
        )
    }
}

@OptIn(FlowPreview::class)
@Composable
fun NavigationEffect(
    navigationFlow: SharedFlow<NavigationIntent>,
    navHostController: NavHostController
) {
    LaunchedEffect(navigationFlow, navHostController) {
        navigationFlow.debounce(250).collect { intent ->
            when (intent) {
                is NavigationIntent.NavigateBack -> {
                    intent.route?.let {
                        navHostController.popBackStack(it, intent.inclusive)
                    } ?: run {
                        if (navHostController.previousBackStackEntry != null)
                            navHostController.popBackStack()
                    }
                }

                is NavigationIntent.NavigateTo -> {
                    navHostController.navigate(intent.route) {
                        launchSingleTop = intent.isSingleTop
                        intent.popUpToRoute?.let { popUpToRoute ->
                            popUpTo(popUpToRoute) {
                                inclusive = intent.inclusive
                            }
                        }
                    }
                }
            }
        }
    }
}


