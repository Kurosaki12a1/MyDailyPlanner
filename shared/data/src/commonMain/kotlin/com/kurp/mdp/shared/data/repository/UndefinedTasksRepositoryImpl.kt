package com.kurp.mdp.shared.data.repository

import com.kuro.mdp.shared.domain.model.schedules.UndefinedTask
import com.kuro.mdp.shared.domain.repository.UndefinedTasksRepository
import com.kurp.mdp.shared.data.data_sources.api.undefinedtasks.UndefinedTasksLocalDataSource
import com.kurp.mdp.shared.data.mappers.schedules.mapToData
import com.kurp.mdp.shared.data.mappers.schedules.mapToDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Created by: minhthinh.h on 12/23/2024
 *
 * Description:
 */
class UndefinedTasksRepositoryImpl(
    private val localDataSource : UndefinedTasksLocalDataSource
) : UndefinedTasksRepository{
    override suspend fun addOrUpdateUndefinedTasks(tasks: List<UndefinedTask>) {
        localDataSource.addOrUpdateUndefinedTasks(tasks.map { it.mapToData() })
    }

    override fun fetchUndefinedTasks(): Flow<List<UndefinedTask>> {
        return localDataSource.fetchUndefinedTasks().map { undefinedTasks ->
            undefinedTasks.map { it.mapToDomain() }
        }
    }

    override suspend fun removeUndefinedTask(key: Long) {
        localDataSource.removeUndefinedTask(key)
    }

    override suspend fun removeAllUndefinedTasks() {
        localDataSource.removeAllUndefinedTasks()
    }
}