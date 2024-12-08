package com.kurp.mdp.shared.data.data_sources.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import com.kurp.mdp.shared.data.data_sources.dao.settings.TasksSettingsDao
import com.kurp.mdp.shared.data.data_sources.dao.settings.ThemeSettingsDao
import com.kurp.mdp.shared.data.entities.settings.TasksSettingsEntity
import com.kurp.mdp.shared.data.entities.settings.ThemeSettingsEntity

@Database(
    version = 1,
    entities = [ThemeSettingsEntity::class, TasksSettingsEntity::class],
)
@ConstructedBy(SettingsDatabaseConstructor::class)
abstract class SettingsDataBase : RoomDatabase() {

    abstract fun fetchThemeSettingsDao(): ThemeSettingsDao
    abstract fun fetchTasksSettingsDao(): TasksSettingsDao
}

// The Room compiler generates the `actual` implementations.
@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object SettingsDatabaseConstructor : RoomDatabaseConstructor<SettingsDataBase> {
    override fun initialize(): SettingsDataBase
}