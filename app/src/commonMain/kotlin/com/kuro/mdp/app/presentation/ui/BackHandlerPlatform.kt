package com.kuro.mdp.app.presentation.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

/**
 * Created by: minhthinh.h on 2/5/2025
 *
 * Description:
 */
@Composable
expect fun BackHandlerPlatform(navHostController: NavHostController, tabHistory: List<String>, onBack: () -> Unit)