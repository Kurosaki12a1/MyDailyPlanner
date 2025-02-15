package com.kuro.mdp.features.home.domain.repository.home

import com.kuro.mdp.shared.domain.model.schedules.UndefinedTask
import com.kuro.mdp.shared.utils.functional.ResultState

/**
 * Created by: minhthinh.h on 12/24/2024
 *
 * Description:
 */
interface HomeUndefinedTasksRepository {
    suspend fun fetchAllUndefinedTasks(): ResultState<List<UndefinedTask>>

    suspend fun deleteUndefinedTask(taskId: Long): ResultState<Unit>
}