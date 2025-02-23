package com.kuro.mdp.features.analytics.presentation.ui.analytics.contract

import androidx.compose.runtime.Stable
import com.kuro.mdp.features.analytics.domain.models.analytics.ScheduleAnalytics
import com.kuro.mdp.shared.presentation.screenmodel.contract.BaseEvent
import com.kuro.mdp.shared.presentation.screenmodel.contract.BaseViewState
import com.kuro.mdp.shared.utils.functional.TimePeriod
import kotlinx.serialization.Serializable

/**
 * Created by: minhthinh.h on 2/19/2025
 *
 * Description:
 */

@Serializable
@Stable
data class AnalyticsViewState(
    val isLoading: Boolean = true,
    val timePeriod: TimePeriod? = null,
    val scheduleAnalytics: ScheduleAnalytics? = null,
) : BaseViewState


@Serializable
sealed class AnalyticsEvent : BaseEvent {
    data object Init : AnalyticsEvent()
    data class ChangeTimePeriod(val period: TimePeriod) : AnalyticsEvent()
}