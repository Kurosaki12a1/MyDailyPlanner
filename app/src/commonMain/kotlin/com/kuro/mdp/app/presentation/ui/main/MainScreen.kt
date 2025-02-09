package com.kuro.mdp.app.presentation.ui.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.kuro.mdp.app.presentation.navigation.AppNavGraph
import com.kuro.mdp.app.presentation.ui.BackHandlerPlatform
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

/**
 * MainScreen is a composable function that represents the main screen of the application.
 * It uses a ViewModel to manage its state and interacts with navigation and UI components.
 *
 * @param viewModel The ViewModel that holds the state and logic for this screen.
 */
@Composable
fun MainScreen(
    viewModel: MainViewModel = koinViewModel()
) {
    val viewState by viewModel.state
    val navController = rememberNavController()
    val currentDestination by navController.currentBackStackEntryAsState()
    val graphBackStack by viewModel.graphBackStack

    NavigationEffect(
        navHostController = navController,
        navigationFlow = viewModel.navigationFlow,
        onAddBackStack = { currentRoute, targetRoute ->
            viewModel.addGraph(currentRoute, targetRoute)
        },
        updateMainCategoryId = {
            viewModel.dispatchEvent(MainEvent.UpdateMainCategoryId(it))
        },
        updateScheduleDate = {
            viewModel.dispatchEvent(MainEvent.UpdateScheduleDate(it))
        },
        updateEditor = { editor ->
            viewModel.dispatchEvent(MainEvent.UpdateEditor(editor.timeTask, editor.template, editor.undefinedTaskId))
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
                // Show the bottom navigation bar only if needed
                if (shouldBottomBarVisible(currentDestination?.destination?.route)) {
                    TabsBottomNavigationBar(
                        selectedItem = currentDestination?.destination?.route,
                        onItemSelected = { viewModel.navigateTo(it.destination) }
                    )
                }
            }
        ) {
            // Define the navigation graph for the app
            AppNavGraph(
                modifier = Modifier.padding(it),
                navHostController = navController
            )
            // Display screen protection if secure mode is enabled
            ScreenProtection(viewState.secureMode)
        }
    }

    // Handle back button presses on different platforms
    BackHandlerPlatform(
        navHostController = navController,
        tabHistory = graphBackStack.toList(),
        onBack = {
            viewModel.removeGraph()
        }
    )
}

/**
 * NavigationEffect is a composable function that manages navigation flows and updates the UI.
 *
 * @param navigationFlow The SharedFlow that emits navigation intents.
 * @param navHostController The NavController to manage navigation.
 * @param onAddBackStack Callback to add a new graph to the back stack.
 * @param updateScheduleDate Callback to update the schedule date.
 * @param updateMainCategoryId Callback to update the main category ID.
 */
@OptIn(FlowPreview::class)
@Composable
fun NavigationEffect(
    navigationFlow: SharedFlow<NavigationIntent>,
    navHostController: NavHostController,
    onAddBackStack: (String?, String) -> Unit,
    updateScheduleDate: (Long?) -> Unit,
    updateMainCategoryId: (Int?) -> Unit,
    updateEditor: (Destination.Editor) -> Unit
) {
    LaunchedEffect(navigationFlow, navHostController) {
        // Debounce events to avoid handling too frequent updates.
        navigationFlow.debounce(NAVIGATION_FLOW).collect { intent ->
            when (intent) {
                // Handle back navigation by popping the back stack.
                is NavigationIntent.NavigateBack -> {
                    intent.route?.let {
                        navHostController.popBackStack(it, intent.inclusive)
                    } ?: run {
                        if (navHostController.previousBackStackEntry != null)
                            navHostController.popBackStack()
                    }
                }

                // Handle navigation to a specific route and update relevant state
                is NavigationIntent.NavigateTo -> {
                    when (intent.route) {
                        is Destination.Home -> {
                            updateScheduleDate((intent.route as Destination.Home).scheduleDate)
                        }

                        is Destination.Categories -> {
                            updateMainCategoryId((intent.route as Destination.Categories).mainCategoryId)
                        }

                        is Destination.Editor -> {
                            updateEditor(intent.route as Destination.Editor)
                        }

                        else -> {

                        }
                    }
                    navHostController.navigate(intent.route) {
                        intent.popUpToRoute?.let { popUpToRoute ->
                            popUpTo(popUpToRoute) {
                                inclusive = intent.inclusive
                            }
                        }
                        launchSingleTop = intent.isSingleTop
                    }
                }

                // Handle navigation to a new graph and update the GRAPH back stack
                is NavigationIntent.NavigateToGraph -> {
                    val currentRoute = navHostController.currentBackStackEntry?.destination?.parent?.route
                    if (currentRoute != intent.route.toString()) {
                        onAddBackStack(currentRoute, intent.route.toString())
                        navHostController.navigate(intent.route) {
                            if (currentRoute != null) {
                                popUpTo(currentRoute) {
                                    saveState = true
                                    inclusive = true
                                }
                            } else {
                                popUpTo(navHostController.graph.findStartDestination().id) {
                                    saveState = true
                                    inclusive = true
                                }
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            }
        }
    }
}


