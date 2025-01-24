package com.kuro.mdp.features.overview.domain.use_case.over_view

import com.kuro.mdp.features.overview.domain.mapper.schedules.mapToUi
import com.kuro.mdp.features.overview.domain.model.schedules.UndefinedTaskOverView
import com.kuro.mdp.features.overview.domain.repository.over_view.OverViewUndefinedTasksRepository
import com.kuro.mdp.shared.utils.functional.ResultState
import com.kuro.mdp.shared.utils.functional.collectAndHandle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by: minhthinh.h on 1/22/2025
 *
 * Description:
 */
class UpdateUndefinedTasksUseCase(
    private val undefinedTaskRepository: OverViewUndefinedTasksRepository
) {
    operator fun invoke(): Flow<ResultState<List<UndefinedTaskOverView>>> = flow {
        undefinedTaskRepository.fetchAllUndefinedTasks().collectAndHandle(
            onFailure = { emit(ResultState.Failure(it)) },
            onSuccess = { tasks -> emit(ResultState.Success(tasks.map { it.mapToUi() })) }
        )
    }
}