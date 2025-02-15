package com.kuro.mdp.features.home.presentation.ui.home

import com.kuro.mdp.features.home.presentation.ui.home.viewmodel.EditorViewModel
import com.kuro.mdp.features.home.presentation.ui.home.viewmodel.HomeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by: minhthinh.h on 12/26/2024
 *
 * Description:
 */
val homePresentationModule = module {
    viewModel { HomeViewModel(get(), get()) }
    viewModel { EditorViewModel(get(), get()) }
}