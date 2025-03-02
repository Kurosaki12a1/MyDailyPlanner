package com.kuro.mdp.app.domain.models

import com.kuro.mdp.shared.presentation.LanguageUiType
import com.kuro.mdp.shared.presentation.screenmodel.contract.BaseAction
import com.kuro.mdp.shared.presentation.theme.ColorsUiType
import com.kuro.mdp.shared.presentation.theme.ThemeUiType
import kotlinx.datetime.LocalDateTime

/**
 * Created by: minhthinh.h on 2/25/2025
 *
 * Description:
 */
sealed class MainAction : BaseAction {
    data class UpdateSettings(
        val language: LanguageUiType,
        val theme: ThemeUiType,
        val colors: ColorsUiType,
        val isEnableDynamicColors: Boolean,
        val secureMode: Boolean
    ) : MainAction()

    data class UpdateMainCategoryId(val id: Int) : MainAction()

    data class UpdateScheduleDate(val date: LocalDateTime) : MainAction()

    data object UpdateEditor : MainAction()
}