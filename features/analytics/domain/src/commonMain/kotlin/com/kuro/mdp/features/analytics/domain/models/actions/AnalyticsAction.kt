package com.kuro.mdp.features.analytics.domain.models.actions

import com.kuro.mdp.features.analytics.domain.models.analytics.ScheduleAnalytics
import com.kuro.mdp.shared.presentation.screenmodel.contract.BaseAction
import com.kuro.mdp.shared.utils.functional.TimePeriod

/**
 * Created by: minhthinh.h on 2/19/2025
 *
 * Description:
 */
sealed class AnalyticsAction : BaseAction {
    data class UpdateAnalytics(val analytics: ScheduleAnalytics) : AnalyticsAction()
    data class UpdateTimePeriod(val period: TimePeriod) : AnalyticsAction()
    data class UpdateLoading(val isLoading: Boolean) : AnalyticsAction()
}