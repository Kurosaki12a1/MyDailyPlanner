package com.kuro.mdp.shared.presentation.mappers

import androidx.compose.runtime.Composable
import com.kuro.mdp.shared.presentation.theme.AppTheme
import com.kuro.mdp.shared.presentation.theme.resources.AppStrings
import kotlinx.datetime.DayOfWeek

/**
 * Created by: minhthinh.h on 12/9/2024
 *
 * Description:
 */
fun DayOfWeek.mapToString(strings: AppStrings): String = when (this) {
    DayOfWeek.SUNDAY -> strings.sundayTitle
    DayOfWeek.MONDAY -> strings.mondayTitle
    DayOfWeek.TUESDAY -> strings.tuesdayTitle
    DayOfWeek.WEDNESDAY -> strings.wednesdayTitle
    DayOfWeek.THURSDAY -> strings.thursdayTitle
    DayOfWeek.FRIDAY -> strings.fridayTitle
    DayOfWeek.SATURDAY -> strings.saturdayTitle
    else -> strings.sundayTitle
}

@Composable
fun DayOfWeek.mapToString() = mapToString(AppTheme.strings)
