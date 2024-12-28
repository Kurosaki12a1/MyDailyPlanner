package com.kuro.mdp.features.home.data.repository.home

import com.kuro.mdp.features.home.domain.repository.home.HomeUndefinedTasksRepository
import com.kuro.mdp.shared.domain.model.schedules.UndefinedTask
import com.kuro.mdp.shared.domain.repository.UndefinedTasksRepository
import com.kuro.mdp.shared.utils.functional.ResultState
import com.kuro.mdp.shared.utils.functional.wrap
import com.kuro.mdp.shared.utils.functional.wrapFlow
import kotlinx.coroutines.flow.Flow

/**
 * Created by: minhthinh.h on 12/24/2024
 *
 * Description:
 */
class HomeUndefinedTasksRepositoryImpl(
    private val undefinedTasksRepository: UndefinedTasksRepository
) : HomeUndefinedTasksRepository {
    override suspend fun addOrUpdateUndefinedTask(task: UndefinedTask): ResultState<Unit> = wrap {
        undefinedTasksRepository.addOrUpdateUndefinedTasks(listOf(task))
    }

    override suspend fun fetchAllUndefinedTasks(): Flow<ResultState<List<UndefinedTask>>> = wrapFlow {
        undefinedTasksRepository.fetchUndefinedTasks()
    }

    override suspend fun deleteUndefinedTask(task: UndefinedTask): ResultState<Unit> = wrap {
        undefinedTasksRepository.removeUndefinedTask(task.id)
    }
}