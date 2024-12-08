package com.kurp.mdp.shared.data.data_sources.dao.settings

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import com.kurp.mdp.shared.data.entities.settings.ThemeSettingsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ThemeSettingsDao {

    @Query("SELECT * FROM ThemeSettings WHERE id = 0")
    fun fetchSettingsFlow(): Flow<ThemeSettingsEntity>

    @Query("SELECT * FROM ThemeSettings WHERE id = 0")
    suspend fun fetchSettings(): ThemeSettingsEntity

    @Update
    suspend fun updateSettings(entity: ThemeSettingsEntity)
}
