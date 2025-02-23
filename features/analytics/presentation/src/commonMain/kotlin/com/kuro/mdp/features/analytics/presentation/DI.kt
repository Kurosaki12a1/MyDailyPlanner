package com.kuro.mdp.features.analytics.presentation

import com.kuro.mdp.features.analytics.presentation.viewmodel.AnalyticsViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by: minhthinh.h on 2/18/2025
 *
 * Description:
 */
val analyticsPresentationModule = module {
    viewModel { AnalyticsViewModel(get(), get()) }
}