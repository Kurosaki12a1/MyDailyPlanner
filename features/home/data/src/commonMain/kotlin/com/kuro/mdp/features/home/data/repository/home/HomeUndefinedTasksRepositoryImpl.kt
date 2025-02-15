package com.kuro.mdp.features.home.data.repository.home

import com.kuro.mdp.features.home.domain.repository.home.HomeUndefinedTasksRepository
import com.kuro.mdp.shared.domain.repository.UndefinedTasksRepository
import com.kuro.mdp.shared.utils.functional.wrap
import kotlinx.coroutines.flow.first

/**
 * Created by: minhthinh.h on 12/24/2024
 *
 * Description:
 */
class HomeUndefinedTasksRepositoryImpl(
    private val undefinedTasksRepository: UndefinedTasksRepository
) : HomeUndefinedTasksRepository {

    override suspend fun fetchAllUndefinedTasks() = wrap {
        undefinedTasksRepository.fetchUndefinedTasks().first()
    }

    override suspend fun deleteUndefinedTask(taskId: Long) = wrap {
        undefinedTasksRepository.removeUndefinedTask(taskId)
    }
}