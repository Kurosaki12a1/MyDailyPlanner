package com.kuro.mdp.features.settings.domain.use_case.categories

import com.kuro.mdp.features.settings.domain.mapper.categories.mapToUi
import com.kuro.mdp.features.settings.domain.model.categories.CategoriesAction
import com.kuro.mdp.features.settings.domain.repository.SettingsCategoriesRepository
import com.kuro.mdp.shared.utils.functional.ResultState
import com.kuro.mdp.shared.utils.functional.collectAndHandle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LoadCategoriesUseCase(
    private val categoryRepository: SettingsCategoriesRepository
) {
    operator fun invoke(): Flow<ResultState<CategoriesAction>> = flow {
        var isSetUp = false
        val selectedCategoryId = categoryRepository.fetchFeatureMainCategory()
        categoryRepository.fetchCategories()
            .collectAndHandle(onFailure = { emit(ResultState.Failure(it)) },
                onSuccess = { domainCategories ->
                    val categories = domainCategories.map { it.mapToUi() }
                    if (!isSetUp) {
                        isSetUp = true
                        val emptyCategory = categories.find { it.mainCategory.id == 0 }
                        val selectedCategories =
                            categories.find { it.mainCategory.id == selectedCategoryId }
                        val selectedCategory =
                            selectedCategories?.mainCategory ?: emptyCategory?.mainCategory
                        emit(
                            ResultState.Success(
                                CategoriesAction.SetUp(
                                    categories, selectedCategory
                                )
                            )
                        )
                    } else {
                        emit(
                            ResultState.Success(
                                CategoriesAction.UpdateCategories(
                                    categories
                                )
                            )
                        )
                    }
                })
    }
}
