package com.kuro.mdp.features.settings.domain.use_case.templates

import com.kuro.mdp.features.settings.domain.mapper.categories.mapToUi
import com.kuro.mdp.features.settings.domain.model.actions.TemplatesAction
import com.kuro.mdp.features.settings.domain.repository.SettingsCategoriesRepository
import com.kuro.mdp.shared.utils.functional.ResultState
import com.kuro.mdp.shared.utils.functional.collectAndHandle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by: minhthinh.h on 2/4/2025
 *
 * Description:
 */
class LoadCategoriesTemplateUseCase(
    private val categoriesRepository: SettingsCategoriesRepository
) {

    operator fun invoke(): Flow<ResultState<TemplatesAction>> = flow {
        categoriesRepository.fetchCategories().collectAndHandle(
            onFailure = { emit(ResultState.Failure(it)) },
            onSuccess = { categories ->
                emit(ResultState.Success(TemplatesAction.UpdateCategories(categories.map { it.mapToUi() })))
            }
        )
    }
}