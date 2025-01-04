package com.kuro.mdp.app.presentation

import androidx.compose.runtime.Composable
import com.kuro.mdp.app.presentation.ui.MainScreen
import org.koin.compose.KoinContext

@Composable
fun App() {
    KoinContext {
        MainScreen()
    }
}