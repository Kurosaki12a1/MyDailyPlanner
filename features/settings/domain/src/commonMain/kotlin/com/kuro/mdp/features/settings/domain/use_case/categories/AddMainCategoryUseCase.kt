package com.kuro.mdp.features.settings.domain.use_case.categories

import com.kuro.mdp.features.settings.domain.mapper.categories.mapToDomain
import com.kuro.mdp.features.settings.domain.model.actions.CategoriesAction
import com.kuro.mdp.features.settings.domain.model.categories.MainCategoryUi
import com.kuro.mdp.features.settings.domain.repository.SettingsCategoriesRepository
import com.kuro.mdp.shared.utils.functional.ResultState
import com.kuro.mdp.shared.utils.functional.handle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AddMainCategoryUseCase(
    private val categoriesRepository: SettingsCategoriesRepository
) {
    operator fun invoke(categoryName: String): Flow<ResultState<CategoriesAction>> = flow {
        val mainCategory = MainCategoryUi(customName = categoryName, defaultType = null)
        categoriesRepository.addMainCategory(mainCategory.mapToDomain()).handle(onFailure = {
            emit(ResultState.Failure(it))
        }, onSuccess = { id ->
            emit(ResultState.Success(CategoriesAction.ChangeMainCategory(mainCategory.copy(id = id))))
        })
    }
}