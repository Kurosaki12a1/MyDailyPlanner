package com.kuro.mdp.shared.presentation.mappers

import androidx.compose.runtime.Composable
import com.kuro.mdp.shared.presentation.theme.AppTheme
import com.kuro.mdp.shared.presentation.theme.resources.AppStrings
import kotlinx.datetime.Month

/**
 * Created by: minhthinh.h on 12/9/2024
 *
 * Description:
 */
fun Month.mapToString(strings: AppStrings): String = when (this) {
    Month.JANUARY -> strings.januaryTitle
    Month.FEBRUARY -> strings.februaryTitle
    Month.MARCH -> strings.marchTitle
    Month.APRIL -> strings.aprilTitle
    Month.MAY -> strings.mayTitle
    Month.JUNE -> strings.juneTitle
    Month.JULY -> strings.julyTitle
    Month.AUGUST -> strings.augustTitle
    Month.SEPTEMBER -> strings.septemberTitle
    Month.OCTOBER -> strings.octoberTitle
    Month.NOVEMBER -> strings.novemberTitle
    Month.DECEMBER -> strings.decemberTitle
    else -> strings.januaryTitle
}

@Composable
fun Month.mapToString(): String = mapToString(AppTheme.strings)
