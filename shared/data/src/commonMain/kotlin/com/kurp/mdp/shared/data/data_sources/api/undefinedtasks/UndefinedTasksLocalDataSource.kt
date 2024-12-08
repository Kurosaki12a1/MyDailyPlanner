package com.kurp.mdp.shared.data.data_sources.api.undefinedtasks

import com.kurp.mdp.shared.data.entities.tasks.UndefinedTaskDetails
import com.kurp.mdp.shared.data.entities.tasks.UndefinedTaskEntity
import kotlinx.coroutines.flow.Flow

interface UndefinedTasksLocalDataSource {

    suspend fun addOrUpdateUndefinedTasks(tasks: List<UndefinedTaskEntity>)
    fun fetchUndefinedTasks(): Flow<List<UndefinedTaskDetails>>
    suspend fun removeUndefinedTask(key: Long)
    suspend fun removeAllUndefinedTasks()
}