package com.kuro.mdp.app.di

import com.kuro.mdp.features.home.data.homeDataModule
import com.kuro.mdp.features.home.presentation.ui.home.homePresentationModule
import com.kuro.mdp.features.settings.presentation.settingsPresentationModule
import org.koin.dsl.module

/**
 * Created by: minhthinh.h on 12/20/2024
 *
 * Description:
 */

val featureModules = module {
    includes(homeDataModule, homePresentationModule, settingsPresentationModule)
}