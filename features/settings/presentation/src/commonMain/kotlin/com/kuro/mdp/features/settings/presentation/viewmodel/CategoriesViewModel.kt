package com.kuro.mdp.features.settings.presentation.viewmodel

import com.kuro.mdp.features.settings.presentation.ui.categories.CategoriesEvent
import com.kuro.mdp.features.settings.presentation.ui.categories.CategoriesViewState
import com.kuro.mdp.shared.presentation.navigation.navigator.Navigator
import com.kuro.mdp.shared.presentation.screenmodel.BaseViewModel

/**
 * Created by: minhthinh.h on 1/24/2025
 *
 * Description:
 */
internal class CategoriesViewModel(
    navigator: Navigator
) : BaseViewModel<CategoriesViewState, CategoriesEvent>(navigator) {
    override fun initState(): CategoriesViewState = CategoriesViewState()

    init {
        dispatchEvent(CategoriesEvent.Init)
    }

    override fun handleEvent(event: CategoriesEvent) {
    }

    override fun showError(e: Throwable) {
    }

}