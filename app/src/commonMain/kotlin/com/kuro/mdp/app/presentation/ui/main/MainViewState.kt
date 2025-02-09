package com.kuro.mdp.app.presentation.ui.main

import com.kuro.mdp.shared.presentation.LanguageUiType
import com.kuro.mdp.shared.presentation.screenmodel.contract.BaseViewState
import com.kuro.mdp.shared.presentation.theme.ColorsUiType
import com.kuro.mdp.shared.presentation.theme.ColorsUiTypeName
import com.kuro.mdp.shared.presentation.theme.ThemeUiType
import com.kuro.mdp.shared.presentation.theme.ThemeUiTypeName
import kotlinx.serialization.Serializable

/**
 * Created by: minhthinh.h on 1/3/2025
 *
 * Description:
 */

@Serializable
data class MainViewState(
    val language: LanguageUiType = LanguageUiType.DEFAULT,
    val theme: ThemeUiType = ThemeUiType(ThemeUiTypeName.DEFAULT),
    val colors: ColorsUiType = ColorsUiType(ColorsUiTypeName.PINK),
    val isEnableDynamicColors: Boolean = false,
    val secureMode: Boolean = false,
) : BaseViewState