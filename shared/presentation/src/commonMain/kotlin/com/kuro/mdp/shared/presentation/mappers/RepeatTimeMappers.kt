package com.kuro.mdp.shared.presentation.mappers

import androidx.compose.runtime.Composable
import com.kuro.mdp.shared.domain.model.template.RepeatTimeType
import com.kuro.mdp.shared.presentation.theme.AppTheme
import com.kuro.mdp.shared.presentation.theme.resources.AppStrings

/**
 * Created by: minhthinh.h on 12/9/2024
 *
 * Description:
 */
fun RepeatTimeType.mapToString(strings: AppStrings): String = when (this) {
    RepeatTimeType.WEEK_DAY -> strings.repeatTimeDayInWeekTitle
    RepeatTimeType.WEEK_DAY_IN_MONTH -> strings.repeatTimeWeekDayInMonthTitle
    RepeatTimeType.MONTH_DAY -> strings.repeatTimeDayInMonthTitle
    RepeatTimeType.YEAR_DAY -> strings.repeatTimeDayInYearTitle
}

@Composable
fun RepeatTimeType.mapToString() = mapToString(AppTheme.strings)
