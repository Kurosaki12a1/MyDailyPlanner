package com.kuro.mdp.features.settings.domain.repository.common

import com.kuro.mdp.features.settings.domain.model.SettingsError

/**
 * Created by: minhthinh.h on 12/26/2024
 *
 * Description:
 */
interface SettingsErrorHandler {
    fun handle(error: Throwable): SettingsError
}