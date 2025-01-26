package com.kuro.mdp.features.settings.presentation

import com.kuro.mdp.features.settings.presentation.viewmodel.CategoriesViewModel
import com.kuro.mdp.features.settings.presentation.viewmodel.SettingsViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by: minhthinh.h on 12/26/2024
 *
 * Description:
 */
val settingsPresentationModule = module {
    viewModel<SettingsViewModel> { SettingsViewModel(get(), get()) }
    viewModel<CategoriesViewModel> { CategoriesViewModel(get(), get()) }
}