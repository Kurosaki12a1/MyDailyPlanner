package com.kuro.mdp.features.settings.domain.model

import kotlinx.serialization.Serializable

/**
 * Created by: minhthinh.h on 12/26/2024
 *
 * Description:
 */
@Serializable
sealed class SettingsError {
    data class BackupError(val throwable: Throwable) : SettingsError()
    data class OtherError(val throwable: Throwable) : SettingsError()
}
