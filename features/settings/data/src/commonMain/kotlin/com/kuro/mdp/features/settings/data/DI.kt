package com.kuro.mdp.features.settings.data

import com.kuro.mdp.features.settings.data.repository.SettingsCategoriesRepositoryImpl
import com.kuro.mdp.features.settings.data.repository.SettingsMenuRepositoryImpl
import com.kuro.mdp.features.settings.domain.repository.SettingsCategoriesRepository
import com.kuro.mdp.features.settings.domain.repository.SettingsMenuRepository
import org.koin.dsl.module

/**
 * Created by: minhthinh.h on 1/2/2025
 *
 * Description:
 */
val settingsDataModule = module {
    single<SettingsMenuRepository> { SettingsMenuRepositoryImpl(get(), get()) }
    single<SettingsCategoriesRepository> { SettingsCategoriesRepositoryImpl(get(), get(), get()) }
}