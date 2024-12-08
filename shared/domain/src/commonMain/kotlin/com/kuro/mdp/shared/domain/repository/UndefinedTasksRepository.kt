package com.kuro.mdp.shared.domain.repository

import com.kuro.mdp.shared.domain.model.schedules.UndefinedTask
import kotlinx.coroutines.flow.Flow

interface UndefinedTasksRepository {
    suspend fun addOrUpdateUndefinedTasks(tasks: List<UndefinedTask>)
    fun fetchUndefinedTasks(): Flow<List<UndefinedTask>>
    suspend fun removeUndefinedTask(key: Long)
    suspend fun removeAllUndefinedTasks()
}
