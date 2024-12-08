package com.kurp.mdp.shared.data

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.kurp.mdp.shared.data.data_sources.database.SchedulesDataBase
import com.kurp.mdp.shared.data.data_sources.database.SettingsDataBase
import kotlinx.coroutines.Dispatchers
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun databasePlatformModule(): Module = module {
    single<SchedulesDataBase> { getSchedulesDatabase(get()) }
    single<SettingsDataBase> { getSettingsDatabase(get()) }
}

fun getSchedulesDatabaseBuilder(ctx: Context): RoomDatabase.Builder<SchedulesDataBase> {
    val appContext = ctx.applicationContext
    val dbFile = appContext.getDatabasePath("schedules_db.db")
    return Room.databaseBuilder<SchedulesDataBase>(
        context = appContext,
        name = dbFile.absolutePath
    ).setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
}

fun getSchedulesDatabase(ctx: Context): SchedulesDataBase {
    return getSchedulesDatabaseBuilder(ctx).build()
}

fun getSettingsDatabaseBuilder(ctx: Context): RoomDatabase.Builder<SettingsDataBase> {
    val appContext = ctx.applicationContext
    val dbFile = appContext.getDatabasePath("settings_db.db")
    return Room.databaseBuilder<SettingsDataBase>(
        context = appContext,
        name = dbFile.absolutePath
    )
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
}

fun getSettingsDatabase(ctx: Context): SettingsDataBase {
    return getSettingsDatabaseBuilder(ctx).build()
}