package com.kuro.mdp.features.analytics.presentation.mappers

import androidx.compose.runtime.Composable
import com.kuro.mdp.features.analytics.presentation.theme.AnalyticsTheme
import com.kuro.mdp.shared.presentation.extensions.string
import com.kuro.mdp.shared.utils.functional.TimePeriod

/**
 * Created by: minhthinh.h on 2/19/2025
 *
 * Description:
 */
@Composable
internal fun TimePeriod.mapToString() = when (this) {
    TimePeriod.WEEK -> AnalyticsTheme.strings.weekTimePeriod.string()
    TimePeriod.MONTH -> AnalyticsTheme.strings.monthTimePeriod.string()
    TimePeriod.HALF_YEAR -> AnalyticsTheme.strings.halfYearTimePeriod.string()
    TimePeriod.YEAR -> AnalyticsTheme.strings.yearTimePeriod.string()
}
