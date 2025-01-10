package com.kuro.mdp.app.presentation.ui

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
import com.kuro.mdp.app.presentation.ui.bottombar.TabsBottomNavigationBar
import com.kuro.mdp.app.presentation.ui.bottombar.shouldBottomBarVisible
import com.kuro.mdp.app.presentation.viewmodel.MainViewModel
import com.kuro.mdp.shared.presentation.navigation.navigator.NavigationIntent
import com.kuro.mdp.shared.presentation.theme.MyDailyPlannerTheme
import com.kuro.mdp.shared.utils.ScreenProtection
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.debounce
import org.koin.compose.viewmodel.koinViewModel

/**
 * Created by: minhthinh.h on 12/11/2024
 *
 * Description:
 */
@Composable
fun MainScreen(
    viewModel: MainViewModel = koinViewModel()
) {
    val viewState by viewModel.state
    val navController = rememberNavController()
    val currentDestination by navController.currentBackStackEntryAsState()

    NavigationEffect(
        navHostController = navController,
        navigationFlow = viewModel.navigationFlow
    )

    MyDailyPlannerTheme(
        languageUiType = viewState.language,
        themeUiType = viewState.theme,
        colorsType = viewState.colors,
        dynamicColor = viewState.isEnableDynamicColors
    ) {
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
        ScreenProtection(viewState.secureMode)
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


