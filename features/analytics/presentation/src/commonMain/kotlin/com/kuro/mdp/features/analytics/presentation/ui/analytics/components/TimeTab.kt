package com.kuro.mdp.features.analytics.presentation.ui.analytics.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
 * Created by: minhthinh.h on 2/19/2025
 *
 * Description:
 */
@Composable
internal fun TimeTab(
    modifier: Modifier = Modifier,
    state: AnalyticsViewState,
    onTimePeriodChanged: (TimePeriod) -> Unit,
) {
    val analytics = state.scheduleAnalytics
    val scrollState = rememberScrollState()
    Column(
        modifier = modifier.fillMaxSize().padding(top = 24.dp).verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        PlanningAnalyticsSection(
            modifier = Modifier.fillMaxWidth(),
            isLoading = state.isLoading,
            planningAnalytics = analytics?.planningAnalytics,
        )
        HorizontalDivider()
        CategoriesAnalyticsSection(
            isLoading = state.isLoading,
            timePeriod = state.timePeriod,
            categoriesAnalytics = analytics?.categoriesAnalytics,
            onTimePeriodChanged = onTimePeriodChanged,
        )
    }
}
