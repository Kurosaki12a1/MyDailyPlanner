package com.kuro.mdp.features.analytics.data

import com.kuro.mdp.features.analytics.data.repository.AnalyticsRepositoryImpl
import com.kuro.mdp.features.analytics.data.repository.SettingsAnalyticsRepositoryImpl
import com.kuro.mdp.features.analytics.domain.repository.AnalyticsRepository
import com.kuro.mdp.features.analytics.domain.repository.SettingsAnalyticsRepository
import org.koin.dsl.module

/**
 * Created by: minhthinh.h on 2/18/2025
 *
 * Description:
 */
val analyticsDataModule = module {
    single<AnalyticsRepository> { AnalyticsRepositoryImpl(get(), get(), get()) }
    single<SettingsAnalyticsRepository> { SettingsAnalyticsRepositoryImpl(get()) }
}