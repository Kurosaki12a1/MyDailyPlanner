package com.kuro.mdp.app.presentation

import androidx.compose.runtime.Composable
import com.kuro.mdp.app.presentation.screen.MainScreen
import com.kuro.mdp.shared.presentation.theme.MyDailyPlannerTheme
import org.koin.compose.KoinContext

@Composable
fun App() {
    MyDailyPlannerTheme {
        KoinContext {
            MainScreen()
        }
    }
}