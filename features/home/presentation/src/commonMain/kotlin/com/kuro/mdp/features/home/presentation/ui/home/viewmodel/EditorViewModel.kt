package com.kuro.mdp.features.home.presentation.ui.home.viewmodel

import androidx.lifecycle.viewModelScope
import com.kuro.mdp.features.home.domain.model.actions.EditorAction
import com.kuro.mdp.features.home.domain.model.editor.EditModelHome
import com.kuro.mdp.features.home.domain.use_case.editor.EditorUseCase
import com.kuro.mdp.features.home.presentation.ui.home.ui.editor.contract.EditorError
import com.kuro.mdp.features.home.presentation.ui.home.ui.editor.contract.EditorEvent
import com.kuro.mdp.features.home.presentation.ui.home.ui.editor.contract.EditorViewState
import com.kuro.mdp.shared.presentation.navigation.destination.Destination
import com.kuro.mdp.shared.presentation.navigation.navigator.Navigator
import com.kuro.mdp.shared.presentation.screenmodel.BaseViewModel
import com.kuro.mdp.shared.utils.extensions.duration
import com.kuro.mdp.shared.utils.managers.TimeOverlayException
import kotlinx.coroutines.launch

/**
 * Created by: minhthinh.h on 2/6/2025
 *
 * Description:
 */
class EditorViewModel(
    private val editorUseCase: EditorUseCase,
    navigator: Navigator
) : BaseViewModel<EditorViewState, EditorEvent, EditorAction>(navigator) {

    init {
        dispatchEvent(EditorEvent.Init)
    }

    override fun initState(): EditorViewState = EditorViewState()

    override fun handleEvent(event: EditorEvent) {
        when (event) {
            is EditorEvent.AddSubCategory -> {
                viewModelScope.launch {
                    state.value.editModel?.mainCategory?.let {
                        editorUseCase.addSubCategoryUseCase(event.name, it).collectAndHandleWork()
                    }
                }
            }

            is EditorEvent.ApplyTemplate -> {
                editorUseCase.applyTemplateUseCase(state.value.editModel, event.template)
                    .handleWork()
            }

            is EditorEvent.ApplyUndefinedTask -> {
                editorUseCase.applyUndefinedTaskUseCase(state.value.editModel, event.task)
                    .handleWork()
            }

            is EditorEvent.ChangeCategories -> updateEditModel {
                copy(mainCategory = event.category, subCategory = event.subCategory)
            }

            is EditorEvent.ChangeNote -> updateEditModel {
                copy(note = event.note)
            }

            is EditorEvent.ChangeParameters -> updateEditModel {
                copy(parameters = event.parameters)
            }

            is EditorEvent.ChangeTime -> updateEditModel {
                copy(timeRange = event.timeRange, duration = duration(event.timeRange))
            }

            is EditorEvent.ClearFailure -> {
                showError(null)
            }

            is EditorEvent.CreateTemplate -> {
                viewModelScope.launch {
                    editorUseCase.createTemplateUseCase(state.value.editModel)
                        .collectAndHandleWork()
                }
            }

            is EditorEvent.Init -> {
                viewModelScope.launch {
                    launch {
                        editorUseCase.loadTemplatesUseCase().collectAndHandleWork()
                    }
                    launch {
                        editorUseCase.loadUndefinedTasksUseCase().collectAndHandleWork()
                    }
                    launch {
                        editorUseCase.loadSendModelUseCase().collectAndHandleWork()
                    }
                }
            }

            is EditorEvent.NavigateToCategoryEditor -> {
                navigateTo(Destination.Categories(event.category.id))
            }

            is EditorEvent.NavigateToSubCategoryEditor -> {
                navigateTo(Destination.Categories(event.category.mainCategory.id))
            }

            is EditorEvent.OpenTemplatesSheet -> {
                update { it.copy(isTemplatesSheetOpen = event.shouldOpen) }
            }

            is EditorEvent.OpenUndefinedTasksSheet -> {
                update { it.copy(isUndefinedTasksSheetOpen = event.shouldOpen) }
            }

            is EditorEvent.PressBackButton -> {
                popBackStack()
            }

            is EditorEvent.PressControlTemplateButton -> {
                navigateTo(Destination.Templates)
            }

            is EditorEvent.PressDeleteButton -> {
                viewModelScope.launch {
                    editorUseCase.deleteModelUseCase(state.value.editModel).collectAndHandleWork()
                }
            }

            is EditorEvent.PressSaveButton -> {
                viewModelScope.launch {
                    editorUseCase.saveModelUseCase(state.value.editModel).collectAndHandleWork()
                }
            }
        }
    }

    override fun showError(e: Throwable?) {
        when (e) {
            null -> {
                update {
                    it.copy(error = null)
                }
            }

            is TimeOverlayException -> {
                update {
                    it.copy(
                        error = EditorError.ShowOverLayError(
                            currentTimeRange = state.value.editModel!!.timeRange,
                            startOverlay = e.startOverlay,
                            endOverlay = e.endOverlay
                        )
                    )
                }
            }

            else -> {
                update {
                    it.copy(
                        error = EditorError.ShowError(e)
                    )
                }
            }
        }
    }

    override fun updateState(action: EditorAction) {
        when (action) {
            is EditorAction.Navigate -> {
                update { it.copy() }
            }

            is EditorAction.SetUp -> update {
                it.copy(
                    editModel = action.editModel,
                    categories = action.categories,
                    timeRangeValid = null,
                    categoryValid = null,
                )
            }

            is EditorAction.UpdateCategories -> update {
                it.copy(categories = action.categories)
            }

            is EditorAction.UpdateTemplates -> update {
                it.copy(templates = action.templates)
            }

            is EditorAction.UpdateUndefinedTasks -> update {
                it.copy(undefinedTasks = action.tasks)
            }

            is EditorAction.UpdateEditModel -> update {
                it.copy(editModel = action.editModel)
            }

            is EditorAction.UpdateTemplateId -> update {
                it.copy(editModel = state.value.editModel?.copy(templateId = action.templateId))
            }

            is EditorAction.SetValidError -> update {
                it.copy(
                    timeRangeValid = action.timeRange,
                    categoryValid = action.category
                )
            }
        }
    }

    private fun updateEditModel(
        onTransform: EditModelHome.() -> EditModelHome,
    ) {
        val editModel = checkNotNull(state.value.editModel)
        update { it.copy(editModel = onTransform(editModel)) }
    }
}