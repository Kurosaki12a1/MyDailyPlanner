package com.kurp.mdp.shared.data

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.SQLiteConnection
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import androidx.sqlite.execSQL
import com.kuro.mdp.shared.utils.functional.Constants.DATABASE.INIT_TASK_SETTINGS_DB
import com.kuro.mdp.shared.utils.functional.Constants.DATABASE.INIT_THEME_SETTINGS_DB
import com.kuro.mdp.shared.utils.functional.Constants.DATABASE.SCHEDULES_DB_NAME
import com.kuro.mdp.shared.utils.functional.Constants.DATABASE.SETTINGS_DB_NAME
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
    val dbFile = appContext.getDatabasePath("$SCHEDULES_DB_NAME.db")
    return Room.databaseBuilder<SchedulesDataBase>(
        context = appContext,
        name = dbFile.absolutePath
    )
        .setDriver(BundledSQLiteDriver())
        .addCallback(object : RoomDatabase.Callback() {
            override fun onCreate(connection: SQLiteConnection) {
                super.onCreate(connection)
                connection.execSQL("INSERT INTO mainCategories (id, custom_name, default_category_type) VALUES (1, NULL, 'SHOPPING');")
                connection.execSQL("INSERT INTO mainCategories (id, custom_name, default_category_type) VALUES (2, NULL, 'HEALTH');")
                connection.execSQL("INSERT INTO mainCategories (id, custom_name, default_category_type) VALUES (3, NULL, 'EMPTY');")
                connection.execSQL("INSERT INTO mainCategories (id, custom_name, default_category_type) VALUES (4, NULL, 'STUDY');")
                connection.execSQL("INSERT INTO mainCategories (id, custom_name, default_category_type) VALUES (5, NULL, 'SLEEP');")
                connection.execSQL("INSERT INTO mainCategories (id, custom_name, default_category_type) VALUES (6, NULL, 'WORK');")
                connection.execSQL("INSERT INTO mainCategories (id, custom_name, default_category_type) VALUES (7, NULL, 'SPORT');")
                connection.execSQL("INSERT INTO mainCategories (id, custom_name, default_category_type) VALUES (8, NULL, 'REST');")
                connection.execSQL("INSERT INTO mainCategories (id, custom_name, default_category_type) VALUES (9, NULL, 'CULTURE');")
                connection.execSQL("INSERT INTO mainCategories (id, custom_name, default_category_type) VALUES (10, NULL, 'AFFAIRS');")
                connection.execSQL("INSERT INTO mainCategories (id, custom_name, default_category_type) VALUES (11, NULL, 'TRANSPORT');")
                connection.execSQL("INSERT INTO mainCategories (id, custom_name, default_category_type) VALUES (12, NULL, 'ENTERTAINMENTS');")
                connection.execSQL("INSERT INTO mainCategories (id, custom_name, default_category_type) VALUES (13, NULL, 'HYGIENE');")
                connection.execSQL("INSERT INTO mainCategories (id, custom_name, default_category_type) VALUES (14, NULL, 'EAT');")
                connection.execSQL("INSERT INTO mainCategories (id, custom_name, default_category_type) VALUES (15, NULL, 'OTHER');")
            }
        })
        .setQueryCoroutineContext(Dispatchers.IO)
}

fun getSchedulesDatabase(ctx: Context): SchedulesDataBase {
    return getSchedulesDatabaseBuilder(ctx).build()
}

fun getSettingsDatabaseBuilder(ctx: Context): RoomDatabase.Builder<SettingsDataBase> {
    val appContext = ctx.applicationContext
    val dbFile = appContext.getDatabasePath("$SETTINGS_DB_NAME.db")
    return Room.databaseBuilder<SettingsDataBase>(
        context = appContext,
        name = dbFile.absolutePath
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

fun getSettingsDatabase(ctx: Context): SettingsDataBase {
    return getSettingsDatabaseBuilder(ctx).build()
}
