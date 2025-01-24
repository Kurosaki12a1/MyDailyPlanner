package com.kuro.mdp.features.settings.domain.model.settings

import com.kuro.mdp.shared.presentation.LanguageUiType
import com.kuro.mdp.shared.presentation.theme.ColorsUiType
import com.kuro.mdp.shared.presentation.theme.ColorsUiTypeName
import com.kuro.mdp.shared.presentation.theme.ThemeUiType
import com.kuro.mdp.shared.presentation.theme.ThemeUiTypeName
import kotlinx.serialization.Serializable

/**
 * Created by: minhthinh.h on 12/26/2024
 *
 * Description:
 */
@Serializable
data class ThemeSettingsUi(
    val language: LanguageUiType = LanguageUiType.DEFAULT,
    val themeColors: ThemeUiType = ThemeUiType(ThemeUiTypeName.DEFAULT),
    val colorsType: ColorsUiType = ColorsUiType(ColorsUiTypeName.PINK),
    val isDynamicColorEnable: Boolean = false,
)