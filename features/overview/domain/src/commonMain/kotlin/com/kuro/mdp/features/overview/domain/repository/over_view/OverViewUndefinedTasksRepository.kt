package com.kuro.mdp.features.overview.domain.repository.over_view

import com.kuro.mdp.shared.domain.model.schedules.UndefinedTask
import com.kuro.mdp.shared.utils.functional.ResultState
import kotlinx.coroutines.flow.Flow

/**
 * Created by: minhthinh.h on 12/24/2024
 *
 * Description:
 */
interface OverViewUndefinedTasksRepository {
    suspend fun addOrUpdateUndefinedTask(task: UndefinedTask): ResultState<Unit>
    suspend fun fetchAllUndefinedTasks(): Flow<ResultState<List<UndefinedTask>>>
    suspend fun deleteUndefinedTask(task: UndefinedTask): ResultState<Unit>
}