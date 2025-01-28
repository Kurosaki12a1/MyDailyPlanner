package com.kuro.mdp.features.settings.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.kuro.mdp.features.settings.domain.model.categories.CategoriesAction
import com.kuro.mdp.features.settings.domain.use_case.categories.CategoriesUseCase
import com.kuro.mdp.features.settings.presentation.ui.categories.CategoriesEvent
import com.kuro.mdp.features.settings.presentation.ui.categories.CategoriesViewState
import com.kuro.mdp.shared.presentation.navigation.navigator.Navigator
import com.kuro.mdp.shared.presentation.screenmodel.BaseViewModel
import com.kuro.mdp.shared.utils.functional.collectAndHandle
import kotlinx.coroutines.launch

/**
 * Created by: minhthinh.h on 1/24/2025
 *
 * Description:
 */
internal class CategoriesViewModel(
    private val categoriesUseCase: CategoriesUseCase,
    navigator: Navigator
) : BaseViewModel<CategoriesViewState, CategoriesEvent>(navigator) {
    override fun initState(): CategoriesViewState = CategoriesViewState()

    init {
        dispatchEvent(CategoriesEvent.Init)
    }

    override fun handleEvent(event: CategoriesEvent) {
        when (event) {
            is CategoriesEvent.AddMainCategory -> {
                viewModelScope.launch {
                    categoriesUseCase.addMainCategoryUseCase(event.name)
                        .collectAndHandle(
                            onFailure = { showError(it) },
                            onSuccess = { updateState(it) }
                        )
                }
            }

            is CategoriesEvent.AddSubCategory -> {
                viewModelScope.launch {
                    categoriesUseCase.addSubCategoryUseCase(event.name, event.mainCategory)
                        .collectAndHandle(
                            onFailure = { showError(it) }
                        )
                }
            }

            is CategoriesEvent.ChangeMainCategory -> {
                updateState(CategoriesAction.ChangeMainCategory(event.mainCategory))
            }
            is CategoriesEvent.CheckSelectedCategory -> { }
            is CategoriesEvent.ClearFailure -> { }
            is CategoriesEvent.DeleteMainCategory -> { }
            is CategoriesEvent.DeleteSubCategory -> { }
            is CategoriesEvent.Init -> {
                viewModelScope.launch {
                    categoriesUseCase.loadCategoriesUseCase()
                        .collectAndHandle(
                            onFailure = { showError(it) },
                            onSuccess = { updateState(it) }
                        )
                }
            }

            is CategoriesEvent.RestoreDefaultCategories -> { }
            is CategoriesEvent.ShowSubCategoryDialog -> {
                updateState(state.value.copy(isShowSubCategoryDialog = event.shouldShow))
            }
            is CategoriesEvent.UpdateMainCategory -> { }
            is CategoriesEvent.UpdateSubCategory -> { }
        }
    }

    private fun updateState(action: CategoriesAction) {
        when (action) {
            is CategoriesAction.ChangeMainCategory -> {
                updateState(state.value.copy(selectedMainCategory = action.category))
            }

            is CategoriesAction.SetUp -> {
                updateState(
                    state.value.copy(
                        categories = action.categories,
                        selectedMainCategory = action.selected
                    )
                )
            }

            is CategoriesAction.UpdateCategories -> {
                updateState(state.value.copy(categories = action.categories))
            }
        }
    }

    override fun showError(e: Throwable) {
        updateState(state.value.copy(failure = e.message))
    }

}