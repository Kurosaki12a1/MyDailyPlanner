package com.kuro.mdp.features.settings.domain.use_case.categories

import com.kuro.mdp.features.settings.domain.mapper.categories.mapToDomain
import com.kuro.mdp.features.settings.domain.model.categories.MainCategoryUi
import com.kuro.mdp.features.settings.domain.model.categories.SubCategoryUi
import com.kuro.mdp.features.settings.domain.repository.SettingsSubCategoriesRepository
import com.kuro.mdp.shared.utils.functional.ResultState
import com.kuro.mdp.shared.utils.functional.handle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AddSubCategoryUseCase(
    private val subCategoryRepository: SettingsSubCategoriesRepository
) {
    operator fun invoke(
        name: String,
        mainCategory: MainCategoryUi
    ): Flow<ResultState<Unit>> = flow {
        val subCategory = SubCategoryUi(name = name, mainCategory = mainCategory)
        subCategoryRepository.addSubCategory(subCategory.mapToDomain()).handle(
            onFailure = { ResultState.Failure(it) },
            onSuccess = { ResultState.Success(Unit) }
        )
    }
}