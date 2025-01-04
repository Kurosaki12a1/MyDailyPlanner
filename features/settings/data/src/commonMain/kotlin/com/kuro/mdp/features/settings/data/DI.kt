package com.kuro.mdp.features.settings.data

import com.kuro.mdp.features.settings.data.repository.MenuSettingsRepositoryImpl
import com.kuro.mdp.features.settings.domain.repository.MenuSettingsRepository
import org.koin.dsl.module

/**
 * Created by: minhthinh.h on 1/2/2025
 *
 * Description:
 */
val settingsDataModule = module {
    single<MenuSettingsRepository> { MenuSettingsRepositoryImpl(get(), get()) }
}