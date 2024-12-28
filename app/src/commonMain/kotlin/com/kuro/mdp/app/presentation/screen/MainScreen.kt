package com.kuro.mdp.app.presentation.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.kuro.mdp.app.presentation.navigation.AppNavGraph
import com.kuro.mdp.app.presentation.screen.bottombar.TabsBottomNavigationBar
import com.kuro.mdp.app.presentation.screen.bottombar.shouldBottomBarVisible
import com.kuro.mdp.shared.presentation.navigation.navigator.NavigationIntent
import com.kuro.mdp.shared.presentation.navigation.navigator.Navigator
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.debounce
import org.koin.compose.koinInject

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

//    AppNavGraph(
//        navHostController = navController
//    )
    Scaffold(
//        topBar = {
//            when (val destination = currentDestination?.destination?.parent?.route) {
//                null -> {
//
//                }
//                Constants.NavigationGraph.HOME -> {
//                    HomeTopBar()
//                }
//            }
//        },
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


