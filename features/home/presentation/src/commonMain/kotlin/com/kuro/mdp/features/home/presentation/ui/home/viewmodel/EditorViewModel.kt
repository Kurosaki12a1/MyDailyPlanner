package com.kuro.mdp.features.home.presentation.ui.home.viewmodel

import com.kuro.mdp.features.home.presentation.Home.home.Home.editor.EditorEvent
import com.kuro.mdp.features.home.presentation.ui.home.ui.editor.EditorViewState
import com.kuro.mdp.shared.presentation.navigation.navigator.Navigator
import com.kuro.mdp.shared.presentation.screenmodel.BaseViewModel

/**
 * Created by: minhthinh.h on 2/6/2025
 *
 * Description:
 */
class EditorViewModel(
    navigator: Navigator
) : BaseViewModel<EditorViewState, EditorEvent>(navigator) {

    override fun initState(): EditorViewState = EditorViewState()

    override fun handleEvent(event: EditorEvent) {

    }

    override fun showError(e: Throwable) {


    }
}