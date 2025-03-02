package com.kuro.mdp.features.settings.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.kuro.mdp.features.settings.domain.model.actions.TemplatesAction
import com.kuro.mdp.features.settings.domain.use_case.templates.TemplatesUseCase
import com.kuro.mdp.features.settings.presentation.ui.template.TemplateViewState
import com.kuro.mdp.features.settings.presentation.ui.template.TemplatesEvent
import com.kuro.mdp.shared.presentation.navigation.navigator.Navigator
import com.kuro.mdp.shared.presentation.screenmodel.BaseViewModel
import kotlinx.coroutines.launch

class TemplatesViewModel(
    private val templatesUseCase: TemplatesUseCase,
    navigator: Navigator
) : BaseViewModel<TemplateViewState, TemplatesEvent, TemplatesAction>(navigator) {

    init {
        dispatchEvent(TemplatesEvent.Init)
    }

    override fun initState(): TemplateViewState = TemplateViewState()

    override fun handleEvent(event: TemplatesEvent) {
        when (event) {
            is TemplatesEvent.AddRepeatTemplate -> {
                viewModelScope.launch {
                    templatesUseCase.addRepeatTemplatesUseCase(event.time, event.template).collectAndHandleWork()
                }
            }

            is TemplatesEvent.AddTemplate -> {
                viewModelScope.launch {
                    templatesUseCase.addTemplatesUseCase(event.template).collectAndHandleWork()
                }
            }

            is TemplatesEvent.ClearFailure -> {
                showError(null)
            }

            is TemplatesEvent.DeleteRepeatTemplate -> {
                viewModelScope.launch {
                    templatesUseCase.deleteRepeatTemplatesUseCase(event.time, event.template).collectAndHandleWork()
                }
            }

            is TemplatesEvent.DeleteTemplate -> {
                viewModelScope.launch {
                    templatesUseCase.deleteTemplatesUseCase(event.id).collectAndHandleWork()
                }
            }

            is TemplatesEvent.Init -> {
                viewModelScope.launch {
                    launch { templatesUseCase.loadCategoriesTemplateUseCase().collectAndHandleWork() }
                    launch { templatesUseCase.loadTemplatesUseCase(state.value.sortedType).collectAndHandleWork() }
                }
            }

            is TemplatesEvent.NavigateToSettings -> {
                popBackStack()
            }

            is TemplatesEvent.RestartTemplateRepeat -> {
                viewModelScope.launch {
                    templatesUseCase.restartTemplateRepeatUseCase(event.template).collectAndHandleWork()
                }
            }

            is TemplatesEvent.ShowTemplateCreator -> {
                update { it.copy(isShowTemplateCreator = event.shouldShow) }
            }

            is TemplatesEvent.StopTemplateRepeat -> {
                viewModelScope.launch {
                    templatesUseCase.stopTemplatesRepeatUseCase(event.template).collectAndHandleWork()
                }
            }

            is TemplatesEvent.UpdateTemplate -> {
                val oldModel = state.value.templates?.find { it.templateId == event.template.templateId }
                viewModelScope.launch {
                    templatesUseCase.updateTemplatesUseCase(oldModel!!, event.template).collectAndHandleWork()
                }
            }

            is TemplatesEvent.UpdatedSortedType -> {
                updateState(TemplatesAction.ChangeSortedType(event.type))
                viewModelScope.launch {
                    templatesUseCase.loadTemplatesUseCase(event.type).collectAndHandleWork()
                }
            }
        }
    }

    override fun showError(e: Throwable?) {
        update { it.copy(failure = e?.message) }
    }

    override fun updateState(action: TemplatesAction) {
        when (action) {
            is TemplatesAction.UpdateTemplates -> update {
                it.copy(
                    templates = action.templates
                )
            }

            is TemplatesAction.ChangeSortedType -> update {
                it.copy(
                    sortedType = action.type
                )
            }

            is TemplatesAction.UpdateCategories -> update {
                it.copy(
                    categories = action.categories
                )
            }

            else -> {

            }
        }
    }

}