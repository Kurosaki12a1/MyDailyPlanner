package com.kurp.mdp.shared.data

import com.kurp.mdp.shared.data.data_sources.api.categories.CategoriesLocalDataSource
import com.kurp.mdp.shared.data.data_sources.api.schedules.SchedulesLocalDataSource
import com.kurp.mdp.shared.data.data_sources.api.settings.TasksSettingsLocalDataSource
import com.kurp.mdp.shared.data.data_sources.api.settings.ThemeSettingsLocalDataSource
import com.kurp.mdp.shared.data.data_sources.api.subcategories.SubCategoriesLocalDataSource
import com.kurp.mdp.shared.data.data_sources.api.templates.TemplatesLocalDataSource
import com.kurp.mdp.shared.data.data_sources.api.undefinedtasks.UndefinedTasksLocalDataSource
import com.kurp.mdp.shared.data.data_sources.dao.categories.MainCategoriesDao
import com.kurp.mdp.shared.data.data_sources.dao.schedules.SchedulesDao
import com.kurp.mdp.shared.data.data_sources.dao.settings.TasksSettingsDao
import com.kurp.mdp.shared.data.data_sources.dao.settings.ThemeSettingsDao
import com.kurp.mdp.shared.data.data_sources.dao.subcategories.SubCategoriesDao
import com.kurp.mdp.shared.data.data_sources.dao.templates.TemplatesDao
import com.kurp.mdp.shared.data.data_sources.dao.undefinedtasks.UndefinedTasksDao
import com.kurp.mdp.shared.data.data_sources.database.SchedulesDataBase
import com.kurp.mdp.shared.data.data_sources.database.SettingsDataBase
import com.kurp.mdp.shared.data.data_sources.impl.categories.CategoriesLocalDataSourceImpl
import com.kurp.mdp.shared.data.data_sources.impl.schedules.SchedulesLocalDataSourceImpl
import com.kurp.mdp.shared.data.data_sources.impl.settings.TasksSettingsLocalDataSourceImpl
import com.kurp.mdp.shared.data.data_sources.impl.settings.ThemeSettingsLocalDataSourceImpl
import com.kurp.mdp.shared.data.data_sources.impl.subcategories.SubCategoriesLocalDataSourceImpl
import com.kurp.mdp.shared.data.data_sources.impl.templates.TemplatesLocalDataSourceImpl
import com.kurp.mdp.shared.data.data_sources.impl.undefinedtasks.UndefinedTasksLocalDataSourceImpl
import org.koin.core.module.Module
import org.koin.dsl.module

internal expect fun databasePlatformModule(): Module

internal val databaseModule = module {
    single<MainCategoriesDao> { createMainCategoriesDao(get()) }
    single<SchedulesDao> { createSchedulesDao(get()) }
    single<SubCategoriesDao> { createSubCategoriesDao(get()) }
    single<TemplatesDao> { createTemplatesDao(get()) }
    single<UndefinedTasksDao> { createUndefinedTaskDao(get()) }

    single<ThemeSettingsDao> { createThemeSettingsDao(get()) }
    single<TasksSettingsDao> { createTasksSettingsDao(get()) }

    single<CategoriesLocalDataSource> { CategoriesLocalDataSourceImpl(get()) }
    single<SchedulesLocalDataSource> { SchedulesLocalDataSourceImpl(get()) }
    single<SubCategoriesLocalDataSource> { SubCategoriesLocalDataSourceImpl(get()) }
    single<TemplatesLocalDataSource> { TemplatesLocalDataSourceImpl(get()) }
    single<UndefinedTasksLocalDataSource> { UndefinedTasksLocalDataSourceImpl(get()) }

    single<TasksSettingsLocalDataSource> { TasksSettingsLocalDataSourceImpl(get()) }
    single<ThemeSettingsLocalDataSource> { ThemeSettingsLocalDataSourceImpl(get()) }
}

val sharedDatabaseModule = listOf(databasePlatformModule(), databaseModule)

internal fun createMainCategoriesDao(database: SchedulesDataBase): MainCategoriesDao =
    database.fetchMainCategoriesDao()

internal fun createSchedulesDao(database: SchedulesDataBase): SchedulesDao =
    database.fetchSchedulesDao()

internal fun createSubCategoriesDao(database: SchedulesDataBase): SubCategoriesDao =
    database.fetchSubCategoriesDao()

internal fun createTemplatesDao(database: SchedulesDataBase): TemplatesDao =
    database.fetchTemplatesDao()

internal fun createUndefinedTaskDao(database: SchedulesDataBase): UndefinedTasksDao =
    database.fetchUndefinedTasksDao()

internal fun createThemeSettingsDao(database: SettingsDataBase): ThemeSettingsDao =
    database.fetchThemeSettingsDao()

internal fun createTasksSettingsDao(database: SettingsDataBase): TasksSettingsDao =
    database.fetchTasksSettingsDao()