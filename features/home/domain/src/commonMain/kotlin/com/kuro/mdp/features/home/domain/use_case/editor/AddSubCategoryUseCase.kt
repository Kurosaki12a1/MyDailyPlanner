package com.kuro.mdp.features.home.domain.use_case.editor

import com.kuro.mdp.features.home.domain.mapper.categories.mapToDomain
import com.kuro.mdp.features.home.domain.mapper.categories.mapToUi
import com.kuro.mdp.features.home.domain.model.actions.EditorAction
import com.kuro.mdp.features.home.domain.model.categories.MainCategoryHome
import com.kuro.mdp.features.home.domain.model.categories.SubCategoryHome
import com.kuro.mdp.features.home.domain.repository.home.HomeCategoryRepository
import com.kuro.mdp.shared.utils.functional.ResultState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by: minhthinh.h on 2/17/2025
 *
 * Description:
 */
class AddSubCategoryUseCase(
    private val categoryRepository: HomeCategoryRepository
) {

    operator fun invoke(name: String, mainCategory: MainCategoryHome): Flow<ResultState<EditorAction>> = flow {
        val subCategoryHome = SubCategoryHome(
            id = 0,
            name = name,
            mainCategory = mainCategory
        )
        when (val result = categoryRepository.addSubCategory(subCategoryHome.mapToDomain())) {
            is ResultState.Failure -> {
                emit(ResultState.Failure(result.exception))
            }

            is ResultState.Success -> {
                when (val categories = categoryRepository.fetchCategories()) {
                    is ResultState.Failure -> emit(ResultState.Failure(categories.exception))
                    is ResultState.Success -> {
                        emit(ResultState.Success(EditorAction.UpdateCategories(categories.data.map { it.mapToUi() })))
                    }

                    else -> {

                    }
                }
            }

            else -> {}
        }
    }
}