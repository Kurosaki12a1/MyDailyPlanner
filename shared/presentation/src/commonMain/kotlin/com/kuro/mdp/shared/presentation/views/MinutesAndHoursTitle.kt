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
    return this.toMinutesOrHoursString(
        AppTheme.strings.minutesTitle.string(),
        AppTheme.strings.hoursTitle.string()
    )
}

@Composable
fun Long.toMinutesAndHoursTitle(): String {
    return this.toMinutesAndHoursString(
        AppTheme.strings.minutesTitle.string(),
        AppTheme.strings.hoursTitle.string()
    )
}

@Composable
fun Long.toDaysTitle(dayTitle: String = AppTheme.strings.dayTitle.string()): String {
    return this.toDaysString(dayTitle)
}
