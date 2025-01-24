package com.kuro.mdp.app.di

import com.kuro.mdp.app.presentation.viewmodel.MainViewModel
import com.kuro.mdp.features.home.data.homeDataModule
import com.kuro.mdp.features.home.domain.homeDomainModule
import com.kuro.mdp.features.home.presentation.ui.home.homePresentationModule
import com.kuro.mdp.features.overview.data.overViewDataModule
import com.kuro.mdp.features.overview.domain.overViewDomainModule
import com.kuro.mdp.features.overview.presentation.overViewPresentationModule
import com.kuro.mdp.features.settings.data.settingsDataModule
import com.kuro.mdp.features.settings.domain.settingsDomainModule
import com.kuro.mdp.features.settings.presentation.settingsPresentationModule
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by: minhthinh.h on 12/20/2024
 *
 * Description:
 */

val featureModules = module {
    includes(
        homeDataModule,
        homePresentationModule,
        homeDomainModule,
        settingsDataModule,
        settingsDomainModule,
        settingsPresentationModule,
        overViewDataModule,
        overViewDomainModule,
        overViewPresentationModule,
    )
    viewModel<MainViewModel> { MainViewModel(get(), get()) }
}