package com.kurp.mdp.shared.data

import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.SQLiteConnection
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import androidx.sqlite.execSQL
import com.kuro.mdp.shared.utils.functional.Constants.DATABASE.INIT_CATEGORIES_SCHEDULES_DB
import com.kuro.mdp.shared.utils.functional.Constants.DATABASE.INIT_TASK_SETTINGS_DB
import com.kuro.mdp.shared.utils.functional.Constants.DATABASE.INIT_THEME_SETTINGS_DB
import com.kuro.mdp.shared.utils.functional.Constants.DATABASE.SCHEDULES_DB_NAME
import com.kuro.mdp.shared.utils.functional.Constants.DATABASE.SETTINGS_DB_NAME
import com.kurp.mdp.shared.data.data_sources.database.SchedulesDataBase
import com.kurp.mdp.shared.data.data_sources.database.SettingsDataBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.core.module.Module
import org.koin.dsl.module
import platform.Foundation.NSHomeDirectory

actual fun databasePlatformModule(): Module = module {
    single<SchedulesDataBase> { getScheduleDatabase() }
    single<SettingsDataBase> { getSettingsDatabase() }
}

fun getSchedulesDataBuilder(): RoomDatabase.Builder<SchedulesDataBase> {
    val dbFilePath = NSHomeDirectory() + "/$SCHEDULES_DB_NAME.db"
    return Room.databaseBuilder<SchedulesDataBase>(
        name = dbFilePath,
        factory = { SchedulesDataBase::class.instantiateImpl() }
    )
        .setDriver(BundledSQLiteDriver())
        .addCallback(object : RoomDatabase.Callback() {
            override fun onCreate(connection: SQLiteConnection) {
                super.onCreate(connection)
                connection.execSQL(INIT_CATEGORIES_SCHEDULES_DB.trimIndent())
            }
        })
        .setQueryCoroutineContext(Dispatchers.IO)
}

fun getScheduleDatabase(): SchedulesDataBase {
    return getSchedulesDataBuilder().build()
}

fun getSettingsDataBuilder(): RoomDatabase.Builder<SettingsDataBase> {
    val dbFilePath = NSHomeDirectory() + "/$SETTINGS_DB_NAME.db"
    return Room.databaseBuilder<SettingsDataBase>(
        name = dbFilePath,
        factory = { SettingsDataBase::class.instantiateImpl() }
    )
        .setDriver(BundledSQLiteDriver())
        .addCallback(object : RoomDatabase.Callback() {
            override fun onCreate(connection: SQLiteConnection) {
                super.onCreate(connection)
                connection.execSQL(INIT_TASK_SETTINGS_DB.trimIndent())
                connection.execSQL(INIT_THEME_SETTINGS_DB.trimIndent())
            }
        })
        .setQueryCoroutineContext(Dispatchers.IO)
}

fun getSettingsDatabase(): SettingsDataBase {
    return getSettingsDataBuilder().build()
}