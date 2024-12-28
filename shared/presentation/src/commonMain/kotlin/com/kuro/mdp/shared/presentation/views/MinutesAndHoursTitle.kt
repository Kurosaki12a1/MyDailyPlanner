package com.kuro.mdp.shared.presentation.views

import androidx.compose.runtime.Composable
import com.kuro.mdp.shared.presentation.theme.AppTheme
import com.kuro.mdp.shared.utils.extensions.string
import com.kuro.mdp.shared.utils.extensions.toDaysString
import com.kuro.mdp.shared.utils.extensions.toMinutesAndHoursString
import com.kuro.mdp.shared.utils.extensions.toMinutesOrHoursString

/**
 * Created by: minhthinh.h on 12/19/2024
 *
 * Description:
 */
@Composable
fun Long.toMinutesOrHoursTitle(): String {
    return this.toMinutesOrHoursString(AppTheme.strings.minutesSymbol.string(), AppTheme.strings.hoursSymbol.string())
}

@Composable
fun Long.toMinutesAndHoursTitle(): String {
    return this.toMinutesAndHoursString(AppTheme.strings.minutesSymbol.string(), AppTheme.strings.hoursSymbol.string())
}

@Composable
fun Long.toDaysTitle(): String {
    return this.toDaysString(AppTheme.strings.dayTitle.string())
}
