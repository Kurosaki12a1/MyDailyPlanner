package com.kuro.mdp.features.settings.domain.use_case.categories

import com.kuro.mdp.features.settings.domain.repository.SettingsCategoriesRepository
import com.kuro.mdp.shared.utils.functional.ResultState
import com.kuro.mdp.shared.utils.functional.handle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RestoreDefaultCategoriesUseCase(
    private val categoriesRepository: SettingsCategoriesRepository
) {
    operator fun invoke() : Flow<ResultState<Unit>> = flow {
        categoriesRepository.restoreDefaultCategories().handle(
            onFailure = { emit (ResultState.Failure(it)) }
        )
    }
}