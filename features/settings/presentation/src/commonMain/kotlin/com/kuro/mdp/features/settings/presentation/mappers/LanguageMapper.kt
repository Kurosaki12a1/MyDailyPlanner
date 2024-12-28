package com.kuro.mdp.features.settings.presentation.mappers

import androidx.compose.runtime.Composable
import com.kuro.mdp.features.settings.presentation.theme.SettingsTheme
import com.kuro.mdp.shared.presentation.LanguageUiType
import org.jetbrains.compose.resources.stringResource

/**
 * Created by: minhthinh.h on 12/26/2024
 *
 * Description:
 */
@Composable
fun LanguageUiType.toLanguageName(): String = when (this) {
    LanguageUiType.DEFAULT -> stringResource(SettingsTheme.strings.defaultLanguageTitle)
    LanguageUiType.EN -> stringResource(SettingsTheme.strings.engLanguageTitle)
    LanguageUiType.RU -> stringResource(SettingsTheme.strings.rusLanguageTitle)
    LanguageUiType.DE -> stringResource(SettingsTheme.strings.gerLanguageTitle)
    LanguageUiType.ES -> stringResource(SettingsTheme.strings.spaLanguageTitle)
    LanguageUiType.FA -> stringResource(SettingsTheme.strings.perLanguageTitle)
    LanguageUiType.FR -> stringResource(SettingsTheme.strings.freLanguageTitle)
    LanguageUiType.PT_BR -> stringResource(SettingsTheme.strings.brazilLanguageTitle)
    LanguageUiType.TR -> stringResource(SettingsTheme.strings.turLanguageTitle)
    LanguageUiType.VN -> stringResource(SettingsTheme.strings.vieLanguageTitle)
    LanguageUiType.PL -> stringResource(SettingsTheme.strings.polLanguageTitle)
}
