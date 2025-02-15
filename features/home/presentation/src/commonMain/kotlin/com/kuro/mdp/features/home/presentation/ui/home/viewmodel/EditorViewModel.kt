package com.kuro.mdp.features.home.presentation.ui.home.viewmodel

import androidx.lifecycle.viewModelScope
import com.kuro.mdp.features.home.domain.model.editor.EditModelHome
import com.kuro.mdp.features.home.domain.model.editor.EditorAction
import com.kuro.mdp.features.home.domain.use_case.editor.EditorUseCase
import com.kuro.mdp.features.home.presentation.Home.home.Home.editor.EditorEvent
import com.kuro.mdp.features.home.presentation.ui.home.ui.editor.EditorError
import com.kuro.mdp.features.home.presentation.ui.home.ui.editor.EditorViewState
import com.kuro.mdp.shared.presentation.navigation.destination.Destination
import com.kuro.mdp.shared.presentation.navigation.navigator.Navigator
import com.kuro.mdp.shared.presentation.screenmodel.BaseViewModel
import com.kuro.mdp.shared.utils.extensions.duration
import com.kuro.mdp.shared.utils.functional.collectAndHandle
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
) : BaseViewModel<EditorViewState, EditorEvent>(navigator) {

    init {
        dispatchEvent(EditorEvent.Init)
    }

    override fun initState(): EditorViewState = EditorViewState()

    override fun handleEvent(event: EditorEvent) {
        when (event) {
            is EditorEvent.AddSubCategory -> {}
            is EditorEvent.ApplyTemplate -> {}
            is EditorEvent.ApplyUndefinedTask -> {}
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
                updateState(newState = state.value.copy(error = null))
            }

            is EditorEvent.CreateTemplate -> {
                viewModelScope.launch {
                    editorUseCase.createTemplateUseCase(state.value.editModel).collectAndHandle(
                        onFailure = { showError(it) },
                        onSuccess = { updateState(it) }
                    )
                }
            }

            is EditorEvent.Init -> {
                viewModelScope.launch {
                    launch {
                        editorUseCase.loadTemplatesUseCase().collectAndHandle(
                            onFailure = { showError(it) },
                            onSuccess = { updateState(it) }
                        )
                    }
                    launch {
                        editorUseCase.loadUndefinedTasksUseCase().collectAndHandle(
                            onFailure = { showError(it) },
                            onSuccess = { updateState(it) }
                        )
                    }
                    launch {
                        editorUseCase.loadSendModelUseCase().collectAndHandle(
                            onFailure = { showError(it) },
                            onSuccess = { updateState(it) }
                        )
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
                updateState(state.value.copy(isTemplatesSheetOpen = event.shouldOpen))
            }

            is EditorEvent.OpenUndefinedTasksSheet -> {
                updateState(state.value.copy(isUndefinedTasksSheetOpen = event.shouldOpen))
            }

            is EditorEvent.PressBackButton -> {
                popBackStack()
            }

            is EditorEvent.PressControlTemplateButton -> {
                navigateTo(Destination.Templates)
            }

            is EditorEvent.PressDeleteButton -> {
                viewModelScope.launch {
                    editorUseCase.deleteModelUseCase(state.value.editModel).collectAndHandle(
                        onFailure = { showError(it) }
                    )
                }
            }

            is EditorEvent.PressSaveButton -> {
                viewModelScope.launch {
                    editorUseCase.saveModelUseCase(state.value.editModel).collectAndHandle(
                        onFailure = { showError(it) },
                        onSuccess = { updateState(it) }
                    )
                }
            }
        }
    }

    override fun showError(e: Throwable) {
        when (e) {
            is TimeOverlayException -> {
                updateState(
                    state.value.copy(
                        error = EditorError.ShowOverLayError(
                            currentTimeRange = state.value.editModel!!.timeRange,
                            startOverlay = e.startOverlay,
                            endOverlay = e.endOverlay
                        )
                    )
                )
            }

            else -> {
                updateState(
                    state.value.copy(
                        error = EditorError.ShowError(e)
                    )
                )
            }
        }

    }

    private fun updateState(action: EditorAction) {
        when (action) {
            is EditorAction.Navigate -> {

            }

            is EditorAction.SetUp -> updateState(
                state.value.copy(
                    editModel = action.editModel,
                    categories = action.categories,
                    timeRangeValid = null,
                    categoryValid = null,
                )
            )

            is EditorAction.UpdateCategories -> updateState(
                state.value.copy(
                    categories = action.categories,
                )
            )

            is EditorAction.UpdateTemplates -> updateState(
                state.value.copy(
                    templates = action.templates,
                )
            )

            is EditorAction.UpdateUndefinedTasks -> updateState(
                state.value.copy(
                    undefinedTasks = action.tasks,
                )
            )

            is EditorAction.UpdateEditModel -> updateState(
                state.value.copy(
                    editModel = action.editModel,
                )
            )

            is EditorAction.UpdateTemplateId -> updateState(
                state.value.copy(
                    editModel = state.value.editModel?.copy(templateId = action.templateId),
                )
            )

            is EditorAction.SetValidError -> updateState(
                state.value.copy(
                    timeRangeValid = action.timeRange,
                    categoryValid = action.category,
                )
            )
        }
    }

    private fun updateEditModel(
        onTransform: EditModelHome.() -> EditModelHome,
    ) {
        val editModel = checkNotNull(state.value.editModel)
        updateState(state.value.copy(editModel = onTransform(editModel)))
    }
}