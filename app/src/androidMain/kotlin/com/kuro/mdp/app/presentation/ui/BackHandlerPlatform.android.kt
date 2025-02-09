package com.kuro.mdp.app.presentation.ui

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.kuro.mdp.app.MainActivity

/**
 * Created by: minhthinh.h on 2/5/2025
 *
 * Description:
 */
@Composable
actual fun BackHandlerPlatform(navHostController: NavHostController, tabHistory: List<String>, onBack: () -> Unit) {
    val context = LocalContext.current
    BackHandler {
        if (tabHistory.isNotEmpty()) {
            tabHistory.lastOrNull()?.let {
                navHostController.navigate(it) {
                    navHostController.graph.route?.let { route ->
                        popUpTo(route)
                    }
                    launchSingleTop = true
                    restoreState = true
                }
                onBack.invoke()
            }
        } else if (navHostController.previousBackStackEntry != null) {
            navHostController.popBackStack()
        } else {
            (context as? MainActivity)?.finish()
        }
    }
}