package com.kuro.mdp.features.analytics.presentation.ui.analytics.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kuro.mdp.features.analytics.presentation.ui.analytics.contract.AnalyticsViewState
import com.kuro.mdp.shared.utils.functional.TimePeriod

/**
 * Created by: minhthinh.h on 2/20/2025
 *
 * Description:
 */
@Composable
internal fun WorkLoadTab(
    modifier: Modifier = Modifier,
    state: AnalyticsViewState,
    onTimePeriodChanged: (TimePeriod) -> Unit,
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = modifier.padding(top = 8.dp).verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        val analytics = state.scheduleAnalytics
        WorkLoadSection(
            isLoading = state.isLoading,
            timePeriod = state.timePeriod,
            workLoadMap = analytics?.dateWorkLoadMap,
            onTimePeriodChanged = onTimePeriodChanged,
        )
        HorizontalDivider()
        ExecutedAnalyticsSection(
            isLoading = state.isLoading,
            timePeriod = state.timePeriod,
            workLoadMap = analytics?.dateWorkLoadMap,
            onTimePeriodChanged = onTimePeriodChanged,
        )
        HorizontalDivider()
        StatisticsSection(
            isLoading = state.isLoading,
            schedulesAnalytics = analytics,
        )
    }
}
