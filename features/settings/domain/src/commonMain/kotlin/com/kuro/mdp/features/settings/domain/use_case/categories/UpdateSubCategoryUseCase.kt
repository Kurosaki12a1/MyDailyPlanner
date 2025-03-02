package com.kuro.mdp.features.settings.domain.use_case.categories

import com.kuro.mdp.features.settings.domain.mapper.categories.mapToDomain
import com.kuro.mdp.features.settings.domain.model.actions.CategoriesAction
import com.kuro.mdp.features.settings.domain.model.categories.SubCategoryUi
import com.kuro.mdp.features.settings.domain.repository.SettingsSubCategoriesRepository
import com.kuro.mdp.shared.utils.functional.ResultState
import com.kuro.mdp.shared.utils.functional.handle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UpdateSubCategoryUseCase(
    private val subCategoriesRepository: SettingsSubCategoriesRepository
) {
    operator fun invoke(subCategory: SubCategoryUi): Flow<ResultState<CategoriesAction>> = flow {
        subCategoriesRepository.updateSubCategory(subCategory.mapToDomain()).handle(
            onFailure = { emit(ResultState.Failure(it)) }
        )
    }
}