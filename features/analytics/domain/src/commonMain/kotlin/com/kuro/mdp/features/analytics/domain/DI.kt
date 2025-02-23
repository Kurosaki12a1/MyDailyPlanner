package com.kuro.mdp.features.analytics.domain

import com.kuro.mdp.features.analytics.domain.use_case.AnalyticsUseCase
import com.kuro.mdp.features.analytics.domain.use_case.LoadAnalyticsUseCase
import com.kuro.mdp.features.analytics.domain.use_case.LoadSettingsUseCase
import com.kuro.mdp.features.analytics.domain.use_case.UpdateTimePeriodUseCase
import org.koin.dsl.module

/**
 * Created by: minhthinh.h on 2/18/2025
 *
 * Description:
 */
val analyticsDomainModule = module {
    factory {
        AnalyticsUseCase(
            loadAnalyticsUseCase = LoadAnalyticsUseCase(get()),
            loadSettingsUseCase = LoadSettingsUseCase(get()),
            updateTimePeriodUseCase = UpdateTimePeriodUseCase(get())
        )
    }
}