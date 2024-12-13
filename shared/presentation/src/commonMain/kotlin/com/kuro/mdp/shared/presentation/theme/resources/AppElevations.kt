package com.kuro.mdp.shared.presentation.theme.resources

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Created by: minhthinh.h on 12/9/2024
 *
 * Description:
 */
data class AppElevations(
    val levelZero: Dp,
    val levelOne: Dp,
    val levelTwo: Dp,
    val levelThree: Dp,
    val levelFour: Dp,
    val levelFive: Dp,
)

val baseTimePlannerElevations = AppElevations(
    levelZero = 0.dp,
    levelOne = 1.dp,
    levelTwo = 3.dp,
    levelThree = 6.dp,
    levelFour = 8.dp,
    levelFive = 12.dp,
)

val LocalAppElevations = staticCompositionLocalOf<AppElevations> {
    error("Elevations is not provided")
}

fun fetchAppElevations() = baseTimePlannerElevations
