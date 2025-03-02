package com.kuro.mdp.features.settings.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.kuro.mdp.features.settings.domain.model.actions.CategoriesAction
import com.kuro.mdp.features.settings.domain.use_case.categories.CategoriesUseCase
import com.kuro.mdp.features.settings.presentation.ui.categories.CategoriesEvent
import com.kuro.mdp.features.settings.presentation.ui.categories.CategoriesViewState
import com.kuro.mdp.shared.presentation.navigation.navigator.Navigator
import com.kuro.mdp.shared.presentation.screenmodel.BaseViewModel
import kotlinx.coroutines.launch

/**
 * Created by: minhthinh.h on 1/24/2025
 *
 * Description:
 */
internal class CategoriesViewModel(
    private val categoriesUseCase: CategoriesUseCase,
    navigator: Navigator
) : BaseViewModel<CategoriesViewState, CategoriesEvent, CategoriesAction>(navigator) {

    override fun initState(): CategoriesViewState = CategoriesViewState()

    init {
        dispatchEvent(CategoriesEvent.Init)
    }

    override fun handleEvent(event: CategoriesEvent) {
        when (event) {
            is CategoriesEvent.AddMainCategory -> {
                viewModelScope.launch {
                    categoriesUseCase.addMainCategoryUseCase(event.name).collectAndHandleWork()
                }
            }

            is CategoriesEvent.AddSubCategory -> {
                viewModelScope.launch {
                    categoriesUseCase.addSubCategoryUseCase(event.name, event.mainCategory).collectAndHandleWork()
                }
            }

            is CategoriesEvent.ChangeMainCategory -> {
                updateState(CategoriesAction.ChangeMainCategory(event.mainCategory))
            }

            is CategoriesEvent.CheckSelectedCategory -> {
                viewModelScope.launch {
                    categoriesUseCase.checkSelectedCategoryUseCase(state.value.categories).collectAndHandleWork()
                }
            }

            is CategoriesEvent.ClearFailure -> {
                showError(null)
            }

            is CategoriesEvent.DeleteMainCategory -> {
                viewModelScope.launch {
                    categoriesUseCase.deleteMainCategoryUseCase(event.mainCategory).collectAndHandleWork()
                }
            }

            is CategoriesEvent.DeleteSubCategory -> {
                viewModelScope.launch {
                    categoriesUseCase.deleteSubCategoryUseCase(event.subCategory).collectAndHandleWork()
                }
            }

            is CategoriesEvent.Init -> {
                viewModelScope.launch {
                    categoriesUseCase.loadCategoriesUseCase().collectAndHandleWork()
                }
            }

            is CategoriesEvent.RestoreDefaultCategories -> {
                viewModelScope.launch {
                    categoriesUseCase.restoreDefaultCategoriesUseCase().collectAndHandleWork()
                }
            }

            is CategoriesEvent.ShowSubCategoryDialog -> {
                update { it.copy(isShowSubCategoryDialog = event.shouldShow) }
            }

            is CategoriesEvent.UpdateMainCategory -> {
                viewModelScope.launch {
                    categoriesUseCase.updateMainCategoryUseCase(event.mainCategory).collectAndHandleWork()
                }
            }

            is CategoriesEvent.UpdateSubCategory -> {
                viewModelScope.launch {
                    categoriesUseCase.updateSubCategoryUseCase(event.subCategory).collectAndHandleWork()
                }
            }
        }
    }

    override fun updateState(action: CategoriesAction) {
        when (action) {
            is CategoriesAction.ChangeMainCategory -> {
                update { it.copy(selectedMainCategory = action.category) }
            }

            is CategoriesAction.SetUp -> {
                update {
                    it.copy(
                        categories = action.categories,
                        selectedMainCategory = action.selected
                    )
                }
            }

            is CategoriesAction.UpdateCategories -> {
                update { it.copy(categories = action.categories) }
            }
        }
    }

    override fun showError(e: Throwable?) {
        update { it.copy(failure = e?.message) }
    }
}