package com.kuro.mdp.features.settings.domain.use_case.categories

import com.kuro.mdp.features.settings.domain.mapper.categories.mapToDomain
import com.kuro.mdp.features.settings.domain.model.categories.MainCategoryUi
import com.kuro.mdp.features.settings.domain.repository.SettingsCategoriesRepository
import com.kuro.mdp.shared.utils.functional.ResultState
import com.kuro.mdp.shared.utils.functional.handle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DeleteMainCategoryUseCase(
    private val categoriesRepository: SettingsCategoriesRepository
) {

    operator fun invoke(category: MainCategoryUi): Flow<ResultState<Unit>> = flow {
        categoriesRepository.deleteMainCategory(category.mapToDomain()).handle(
            onFailure = { emit(ResultState.Failure(it)) },
            onSuccess = { emit(ResultState.Success(Unit)) }
        )
    }
}