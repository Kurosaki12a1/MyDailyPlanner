package com.kuro.mdp.features.home.domain.use_case.editor

import com.kuro.mdp.features.home.domain.mapper.schedules.mapToUi
import com.kuro.mdp.features.home.domain.model.editor.EditorAction
import com.kuro.mdp.features.home.domain.repository.home.HomeUndefinedTasksRepository
import com.kuro.mdp.shared.utils.functional.ResultState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by: minhthinh.h on 2/11/2025
 *
 * Description:
 */
class LoadUndefinedTasksUseCase(
    private val undefinedTasksRepository: HomeUndefinedTasksRepository
) {

    operator fun invoke(): Flow<ResultState<EditorAction>> = flow {
        when (val tasks = undefinedTasksRepository.fetchAllUndefinedTasks()) {
            is ResultState.Failure -> {
                emit(ResultState.Failure(tasks.exception))
            }

            is ResultState.Success -> {
                emit(ResultState.Success(EditorAction.UpdateUndefinedTasks(tasks.data.map { it.mapToUi() })))
            }

            else -> {

            }
        }
    }
}