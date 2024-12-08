package com.kurp.mdp.shared.data.data_sources.dao.settings

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import com.kurp.mdp.shared.data.entities.settings.TasksSettingsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TasksSettingsDao {

    @Query("SELECT * FROM TasksSettings WHERE id = 0")
    fun fetchSettingsFlow(): Flow<TasksSettingsEntity>

    @Update
    suspend fun updateSettings(entity: TasksSettingsEntity)
}
