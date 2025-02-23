package com.kuro.mdp.features.analytics.domain.repository

import com.kuro.mdp.features.analytics.domain.models.analytics.ScheduleAnalytics
import com.kuro.mdp.shared.utils.functional.ResultState
import com.kuro.mdp.shared.utils.functional.TimePeriod
import kotlinx.coroutines.flow.Flow

/**
 * Created by: minhthinh.h on 2/18/2025
 *
 * Description:
 */
interface AnalyticsRepository {
    suspend fun fetchAnalytics(period: TimePeriod): Flow<ResultState<ScheduleAnalytics>>
}