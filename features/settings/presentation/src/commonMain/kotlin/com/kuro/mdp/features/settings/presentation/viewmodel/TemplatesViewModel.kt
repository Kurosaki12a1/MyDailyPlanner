package com.kuro.mdp.features.settings.presentation.viewmodel

import com.kuro.mdp.features.settings.presentation.ui.template.TemplateViewState
import com.kuro.mdp.features.settings.presentation.ui.template.TemplatesEvent
import com.kuro.mdp.shared.presentation.navigation.navigator.Navigator
import com.kuro.mdp.shared.presentation.screenmodel.BaseViewModel

class TemplatesViewModel(
    navigator: Navigator
) : BaseViewModel<TemplateViewState, TemplatesEvent>(navigator) {

    override fun initState(): TemplateViewState = TemplateViewState()

    override fun handleEvent(event: TemplatesEvent) {
    }

    override fun showError(e: Throwable) {
        updateState(state.value.copy(failure = e.message))
    }
}