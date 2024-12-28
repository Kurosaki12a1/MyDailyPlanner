package com.kuro.mdp.shared.presentation.navigation.destination

import kotlinx.serialization.Serializable

/**
 * Created by: minhthinh.h on 12/10/2024
 *
 * Description:
 */
@Serializable
sealed class Destination {
    @Serializable
    data object Splash : Destination()

    @Serializable
    data object Home : Destination()

    @Serializable
    data object Overview : Destination()

    @Serializable
    data object Analytics : Destination()

    @Serializable
    data object Settings : Destination()

    @Serializable
    data object Donate: Destination()

    @Serializable
    data object Templates : Destination()

    @Serializable
    data object Categories : Destination()
}