package com.kuro.mdp.features.overview.presentation

import com.kuro.mdp.features.overview.presentation.viewmodel.OverViewViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by: minhthinh.h on 1/21/2025
 *
 * Description:
 */
val overViewPresentationModule = module {
    viewModel<OverViewViewModel> { OverViewViewModel(get(), get()) }
}