package com.kurp.mdp.shared.data.data_sources.impl.undefinedtasks

import com.kurp.mdp.shared.data.data_sources.api.undefinedtasks.UndefinedTasksLocalDataSource
import com.kurp.mdp.shared.data.data_sources.dao.undefinedtasks.UndefinedTasksDao
import com.kurp.mdp.shared.data.entities.tasks.UndefinedTaskDetails
import com.kurp.mdp.shared.data.entities.tasks.UndefinedTaskEntity
import kotlinx.coroutines.flow.Flow

class UndefinedTasksLocalDataSourceImpl (
    private val undefinedTasksDao: UndefinedTasksDao,
) : UndefinedTasksLocalDataSource {

    override suspend fun addOrUpdateUndefinedTasks(tasks: List<UndefinedTaskEntity>) {
        undefinedTasksDao.addOrUpdateUndefinedTasks(tasks)
    }

    override fun fetchUndefinedTasks(): Flow<List<UndefinedTaskDetails>> {
        return undefinedTasksDao.fetchAllUndefinedTasks()
    }

    override suspend fun removeUndefinedTask(key: Long) {
        undefinedTasksDao.removeUndefinedTask(key)
    }

    override suspend fun removeAllUndefinedTasks() {
        undefinedTasksDao.removeAllUndefinedTasks()
    }
}