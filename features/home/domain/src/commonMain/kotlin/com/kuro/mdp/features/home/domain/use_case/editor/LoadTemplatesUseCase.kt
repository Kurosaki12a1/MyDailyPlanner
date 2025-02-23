package com.kuro.mdp.features.home.domain.use_case.editor

import com.kuro.mdp.features.home.domain.mapper.templates.mapToUi
import com.kuro.mdp.features.home.domain.model.actions.EditorAction
import com.kuro.mdp.features.home.domain.repository.home.HomeTemplatesRepository
import com.kuro.mdp.shared.utils.functional.ResultState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by: minhthinh.h on 2/11/2025
 *
 * Description:
 */
class LoadTemplatesUseCase(
    private val templatesRepository: HomeTemplatesRepository
) {

    operator fun invoke(): Flow<ResultState<EditorAction>> = flow {
        when (val templates = templatesRepository.fetchTemplates()) {
            is ResultState.Failure -> emit(ResultState.Failure(templates.exception))

            is ResultState.Success -> emit(ResultState.Success(EditorAction.UpdateTemplates(templates.data.map { it.mapToUi() })))

            else -> {
                // TODO: Handle other cases
            }
        }
    }
}