package com.kuro.mdp.features.settings.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.kuro.mdp.features.settings.domain.model.template.TemplatesAction
import com.kuro.mdp.features.settings.domain.use_case.templates.TemplatesUseCase
import com.kuro.mdp.features.settings.presentation.ui.template.TemplateViewState
import com.kuro.mdp.features.settings.presentation.ui.template.TemplatesEvent
import com.kuro.mdp.shared.presentation.navigation.navigator.Navigator
import com.kuro.mdp.shared.presentation.screenmodel.BaseViewModel
import com.kuro.mdp.shared.utils.functional.collectAndHandle
import kotlinx.coroutines.launch

class TemplatesViewModel(
    private val templatesUseCase: TemplatesUseCase,
    navigator: Navigator
) : BaseViewModel<TemplateViewState, TemplatesEvent>(navigator) {

    init {
        dispatchEvent(TemplatesEvent.Init)
    }

    override fun initState(): TemplateViewState = TemplateViewState()

    override fun handleEvent(event: TemplatesEvent) {
        when (event) {
            is TemplatesEvent.AddRepeatTemplate -> {
                viewModelScope.launch {
                    templatesUseCase.addRepeatTemplatesUseCase(event.time, event.template).collectAndHandle(
                        onFailure = { showError(it) }
                    )
                }
            }

            is TemplatesEvent.AddTemplate -> {
                viewModelScope.launch {
                    templatesUseCase.addTemplatesUseCase(event.template).collectAndHandle(
                        onFailure = { showError(it) }
                    )
                }
            }

            is TemplatesEvent.ClearFailure -> {
                updateState(state.value.copy(failure = null))
            }

            is TemplatesEvent.DeleteRepeatTemplate -> {
                viewModelScope.launch {
                    templatesUseCase.deleteRepeatTemplatesUseCase(event.time, event.template).collectAndHandle(
                        onFailure = { showError(it) }
                    )
                }
            }

            is TemplatesEvent.DeleteTemplate -> {
                viewModelScope.launch {
                    templatesUseCase.deleteTemplatesUseCase(event.id).collectAndHandle(
                        onFailure = { showError(it) }
                    )
                }
            }

            is TemplatesEvent.Init -> {
                viewModelScope.launch {
                    launch {
                        templatesUseCase.loadCategoriesTemplateUseCase().collectAndHandle(
                            onFailure = { showError(it) },
                            onSuccess = { updateState(it) }
                        )
                    }
                    launch {
                        templatesUseCase.loadTemplatesUseCase(state.value.sortedType).collectAndHandle(
                            onFailure = { showError(it) },
                            onSuccess = { updateState(it) }
                        )
                    }
                }
            }

            is TemplatesEvent.NavigateToSettings -> {
                popBackStack()
            }

            is TemplatesEvent.RestartTemplateRepeat -> {
                viewModelScope.launch {
                    templatesUseCase.restartTemplateRepeatUseCase(event.template).collectAndHandle(
                        onFailure = { showError(it) }
                    )
                }
            }

            is TemplatesEvent.ShowTemplateCreator -> {
                updateState(state.value.copy(isShowTemplateCreator = event.shouldShow))
            }

            is TemplatesEvent.StopTemplateRepeat -> {
                viewModelScope.launch {
                    templatesUseCase.stopTemplatesRepeatUseCase(event.template).collectAndHandle(
                        onFailure = { showError(it) }
                    )
                }
            }

            is TemplatesEvent.UpdateTemplate -> {
                val oldModel = state.value.templates?.find { it.templateId == event.template.templateId }
                viewModelScope.launch {
                    templatesUseCase.updateTemplatesUseCase(oldModel!!, event.template).collectAndHandle(
                        onFailure = { showError(it) }
                    )
                }
            }

            is TemplatesEvent.UpdatedSortedType -> {
                updateState(TemplatesAction.ChangeSortedType(event.type))
                viewModelScope.launch {
                    templatesUseCase.loadTemplatesUseCase(event.type).collectAndHandle(
                        onFailure = { showError(it) },
                        onSuccess = { updateState(it) }
                    )
                }
            }
        }
    }

    private fun updateState(action: TemplatesAction) {
        when (action) {
            is TemplatesAction.ChangeSortedType -> {
                updateState(state.value.copy(sortedType = action.type))
            }

            is TemplatesAction.UpdateCategories -> {
                updateState(state.value.copy(categories = action.categories))
            }

            is TemplatesAction.UpdateTemplates -> {
                updateState(state.value.copy(templates = action.templates))
            }
        }
    }

    override fun showError(e: Throwable) {
        updateState(state.value.copy(failure = e.message))
    }
}