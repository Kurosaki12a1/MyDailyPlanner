package com.kuro.mdp.features.overview.domain.use_case.over_view

import com.kuro.mdp.features.overview.domain.mapper.schedules.mapToDomain
import com.kuro.mdp.features.overview.domain.model.schedules.UndefinedTaskOverView
import com.kuro.mdp.features.overview.domain.repository.over_view.OverViewUndefinedTasksRepository
import com.kuro.mdp.shared.utils.functional.ResultState
import com.kuro.mdp.shared.utils.functional.handle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by: minhthinh.h on 1/22/2025
 *
 * Description:
 */
class CreateOrUpdateTaskUseCase(
    private val undefinedTasksRepository: OverViewUndefinedTasksRepository
) {

    operator fun invoke(task: UndefinedTaskOverView): Flow<ResultState<Unit>> = flow {
        undefinedTasksRepository.addOrUpdateUndefinedTask(task.mapToDomain()).handle(
            onFailure = { error -> emit(ResultState.Failure(error)) },
            onSuccess = { emit(ResultState.Success(Unit)) }
        )
    }
}