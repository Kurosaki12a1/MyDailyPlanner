package com.kuro.mdp.features.settings.domain.use_case.categories

import com.kuro.mdp.features.settings.domain.model.categories.CategoriesAction
import com.kuro.mdp.features.settings.domain.model.categories.CategoriesUi
import com.kuro.mdp.features.settings.domain.repository.SettingsCategoriesRepository
import com.kuro.mdp.shared.utils.functional.ResultState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CheckSelectedCategoryUseCase(
    private val categoriesRepository: SettingsCategoriesRepository
) {

    operator fun invoke(categories: List<CategoriesUi>): Flow<ResultState<CategoriesAction>> =
        flow {
            val selectedCategoryId = categoriesRepository.fetchFeatureMainCategory()
            val selectedCategories = categories.find { it.mainCategory.id == selectedCategoryId }
            val selectedCategory = selectedCategories?.mainCategory
            if (selectedCategory != null) {
                emit(ResultState.Success(CategoriesAction.ChangeMainCategory(selectedCategory)))
            } else {
                emit(ResultState.Failure(Throwable("No selected category found")))
            }
        }
}