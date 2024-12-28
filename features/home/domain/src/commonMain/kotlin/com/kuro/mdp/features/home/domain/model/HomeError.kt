package com.kuro.mdp.features.home.domain.model

import kotlinx.serialization.Serializable

/**
 * Created by: minhthinh.h on 12/23/2024
 *
 * Description:
 */
@Serializable
sealed class HomeError {
    data object ShiftError : HomeError()
    data object ImportanceError : HomeError()
    data class OtherError(val throwable: Throwable) : HomeError()
}