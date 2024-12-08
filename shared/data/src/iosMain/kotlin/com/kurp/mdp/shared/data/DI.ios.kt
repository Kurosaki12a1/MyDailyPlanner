package com.kurp.mdp.shared.data

import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.kurp.mdp.shared.data.data_sources.database.SchedulesDataBase
import com.kurp.mdp.shared.data.data_sources.database.SettingsDataBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.core.module.Module
import org.koin.dsl.module
import platform.Foundation.NSHomeDirectory

actual fun sharedModulePlatform(): Module = module {
    single<SchedulesDataBase> { getScheduleDatabase() }
    single<SettingsDataBase> { getSettingsDatabase() }
}

fun getSchedulesDataBuilder(): RoomDatabase.Builder<SchedulesDataBase> {
    val dbFilePath = NSHomeDirectory() + "/schedules_db.db"
    return Room.databaseBuilder<SchedulesDataBase>(
        name = dbFilePath,
        factory = { SchedulesDataBase::class.instantiateImpl() }
    ).setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
}

fun getScheduleDatabase(): SchedulesDataBase {
    return getSchedulesDataBuilder().build()
}

fun getSettingsDataBuilder(): RoomDatabase.Builder<SettingsDataBase> {
    val dbFilePath = NSHomeDirectory() + "/settings_db.db"
    return Room.databaseBuilder<SettingsDataBase>(
        name = dbFilePath,
        factory = { SettingsDataBase::class.instantiateImpl() }
    ).setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
}

fun getSettingsDatabase(): SettingsDataBase {
    return getSettingsDataBuilder().build()
}