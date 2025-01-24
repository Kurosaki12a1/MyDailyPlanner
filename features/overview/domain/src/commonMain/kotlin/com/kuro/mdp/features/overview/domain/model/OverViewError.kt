package com.kuro.mdp.features.overview.domain.model

import kotlinx.serialization.Serializable

/**
 * Created by: minhthinh.h on 12/23/2024
 *
 * Description:
 */
@Serializable
sealed class OverViewError {
    data object ShiftError : OverViewError()
    data object ImportanceError : OverViewError()
    data class OtherError(val throwable: Throwable) : OverViewError()
}