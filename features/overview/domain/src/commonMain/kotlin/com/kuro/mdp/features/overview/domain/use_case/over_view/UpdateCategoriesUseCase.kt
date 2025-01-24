package com.kuro.mdp.features.overview.domain.use_case.over_view

import com.kuro.mdp.features.overview.domain.mapper.categories.mapToUi
import com.kuro.mdp.features.overview.domain.model.categories.CategoriesOverView
import com.kuro.mdp.features.overview.domain.repository.over_view.OverViewCategoryRepository
import com.kuro.mdp.shared.utils.functional.ResultState
import com.kuro.mdp.shared.utils.functional.collectAndHandle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by: minhthinh.h on 1/22/2025
 *
 * Description:
 */
class UpdateCategoriesUseCase(
    private val categoriesRepository: OverViewCategoryRepository
) {

    operator fun invoke(): Flow<ResultState<List<CategoriesOverView>>> = flow {
        categoriesRepository.fetchCategories().collectAndHandle(
            onFailure = {
                emit(ResultState.Failure(it))
            }, onSuccess = { categories ->
                emit(ResultState.Success(categories.map { it.mapToUi() }))
            }
        )
    }
}