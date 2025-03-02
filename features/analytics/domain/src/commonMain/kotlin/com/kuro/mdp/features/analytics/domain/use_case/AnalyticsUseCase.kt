package com.kuro.mdp.features.analytics.domain.use_case

/**
 * Created by: minhthinh.h on 2/19/2025
 *
 * Description:
 */
data class AnalyticsUseCase(
    val loadAnalyticsUseCase: LoadAnalyticsUseCase,
    val loadSettingsUseCase: LoadSettingsUseCase,
    val updateTimePeriodUseCase: UpdateTimePeriodUseCase
)