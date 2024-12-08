package com.kurp.mdp.shared.data.data_sources.dao.undefinedtasks

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.kurp.mdp.shared.data.entities.tasks.UndefinedTaskDetails
import com.kurp.mdp.shared.data.entities.tasks.UndefinedTaskEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UndefinedTasksDao {

    @Query("SELECT * FROM undefinedTasks")
    @Transaction
    fun fetchAllUndefinedTasks(): Flow<List<UndefinedTaskDetails>>

    @Insert(entity = UndefinedTaskEntity::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun addOrUpdateUndefinedTasks(entity: List<UndefinedTaskEntity>)

    @Query("DELETE FROM undefinedTasks WHERE key = :key")
    suspend fun removeUndefinedTask(key: Long)

    @Query("DELETE FROM undefinedTasks")
    suspend fun removeAllUndefinedTasks()
}
