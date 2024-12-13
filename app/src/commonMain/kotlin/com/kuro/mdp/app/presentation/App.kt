package com.kuro.mdp.app.presentation

import androidx.compose.runtime.Composable
import com.kuro.mdp.app.presentation.screen.MainScreen
import com.kuro.mdp.shared.presentation.theme.MyDailyPlannerTheme

@Composable
fun App() {
    MyDailyPlannerTheme {
        MainScreen()
    }
}