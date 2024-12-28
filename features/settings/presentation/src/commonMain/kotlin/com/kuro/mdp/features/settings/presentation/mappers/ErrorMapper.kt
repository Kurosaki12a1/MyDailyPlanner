package com.kuro.mdp.features.settings.presentation.mappers

import com.kuro.mdp.features.settings.domain.model.SettingsError
import com.kuro.mdp.features.settings.presentation.theme.resource.SettingsStrings

/**
 * Created by: minhthinh.h on 12/26/2024
 *
 * Description:
 */
internal fun SettingsError.mapToMessage(string: SettingsStrings) = when (this) {
    is SettingsError.BackupError -> string.errorBackupMessage
    is SettingsError.OtherError -> string.otherError
}
