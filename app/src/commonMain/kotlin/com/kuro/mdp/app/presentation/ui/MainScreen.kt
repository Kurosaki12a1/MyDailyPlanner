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
import com.kuro.mdp.app.presentation.ui.bottom_bar.TabsBottomNavigationBar
import com.kuro.mdp.app.presentation.ui.bottom_bar.shouldBottomBarVisible
import com.kuro.mdp.app.presentation.viewmodel.MainViewModel
import com.kuro.mdp.shared.presentation.navigation.destination.Destination
import com.kuro.mdp.shared.presentation.navigation.navigator.NavigationIntent
import com.kuro.mdp.shared.presentation.theme.MyDailyPlannerTheme
import com.kuro.mdp.shared.utils.ScreenProtection
import com.kuro.mdp.shared.utils.functional.Constants.Delay.NAVIGATION_FLOW
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
        navigationFlow = viewModel.navigationFlow,
        updateMainCategoryId = {
            viewModel.dispatchEvent(MainEvent.UpdateMainCategoryId(it))
        },
        updateScheduleDate = {
            viewModel.dispatchEvent(MainEvent.UpdateScheduleDate(it))
        }
    )

    MyDailyPlannerTheme(
        languageUiType = viewState.language,
        themeUiType = viewState.theme,
        colorsType = viewState.colors,
        dynamicColor = viewState.isEnableDynamicColors
    ) {
        Scaffold(
            bottomBar = {
                if (shouldBottomBarVisible(currentDestination?.destination?.route)) {
                    TabsBottomNavigationBar(
                        selectedItem = currentDestination?.destination?.route,
                        onItemSelected = { navController.navigate(it.destination) }
                    )
                }
            }
        ) {
            AppNavGraph(
                modifier = Modifier.padding(it),
                navHostController = navController
            )
            ScreenProtection(viewState.secureMode)
        }
    }

}

@OptIn(FlowPreview::class)
@Composable
fun NavigationEffect(
    navigationFlow: SharedFlow<NavigationIntent>,
    navHostController: NavHostController,
    updateScheduleDate: (Long?) -> Unit,
    updateMainCategoryId: (Int?) -> Unit
) {
    LaunchedEffect(navigationFlow, navHostController) {
        navigationFlow.debounce(NAVIGATION_FLOW).collect { intent ->
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
                    if (intent.route is Destination.Home) {
                        updateScheduleDate((intent.route as Destination.Home).scheduleDate)
                    } else if (intent.route is Destination.Categories) {
                        updateMainCategoryId((intent.route as Destination.Categories).mainCategoryId)
                    }
                }
            }
        }
    }
}


