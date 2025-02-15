package com.kuro.mdp.features.home.domain.use_case.editor

import com.kuro.mdp.features.home.domain.mapper.categories.mapToUi
import com.kuro.mdp.features.home.domain.model.editor.EditorAction
import com.kuro.mdp.features.home.domain.repository.home.HomeCategoryRepository
import com.kuro.mdp.features.home.domain.repository.home.HomeEditorRepository
import com.kuro.mdp.shared.utils.functional.ResultState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by: minhthinh.h on 2/13/2025
 *
 * Description:
 */
class LoadSendModelUseCase(
    private val editorRepository: HomeEditorRepository,
    private val categoryRepository: HomeCategoryRepository
) {
    operator fun invoke(): Flow<ResultState<EditorAction>> = flow {
        val editModel = editorRepository.fetchEditModel()
        when (val result = categoryRepository.fetchCategories()) {
            is ResultState.Failure -> {
                emit(ResultState.Failure(result.exception))
            }
            is ResultState.Success -> {
                emit(ResultState.Success(EditorAction.SetUp(editModel, result.data.map { it.mapToUi() })))
            }
            else -> {}
        }
    }
}