package com.kurp.mdp.shared.data.data_sources.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import com.kurp.mdp.shared.data.data_sources.dao.categories.MainCategoriesDao
import com.kurp.mdp.shared.data.data_sources.dao.schedules.SchedulesDao
import com.kurp.mdp.shared.data.data_sources.dao.subcategories.SubCategoriesDao
import com.kurp.mdp.shared.data.data_sources.dao.templates.TemplatesDao
import com.kurp.mdp.shared.data.data_sources.dao.undefinedtasks.UndefinedTasksDao
import com.kurp.mdp.shared.data.entities.categories.MainCategoryEntity
import com.kurp.mdp.shared.data.entities.categories.SubCategoryEntity
import com.kurp.mdp.shared.data.entities.schedules.DailyScheduleEntity
import com.kurp.mdp.shared.data.entities.tasks.TimeTaskEntity
import com.kurp.mdp.shared.data.entities.tasks.UndefinedTaskEntity
import com.kurp.mdp.shared.data.entities.template.RepeatTimeEntity
import com.kurp.mdp.shared.data.entities.template.TemplateEntity

@Database(
    version = 1,
    entities = [
        TemplateEntity::class,
        RepeatTimeEntity::class,
        DailyScheduleEntity::class,
        TimeTaskEntity::class,
        UndefinedTaskEntity::class,
        MainCategoryEntity::class,
        SubCategoryEntity::class,
    ]
)
@ConstructedBy(SchedulesDatabaseConstructor::class)
abstract class SchedulesDataBase : RoomDatabase() {
    abstract fun fetchSchedulesDao(): SchedulesDao
    abstract fun fetchMainCategoriesDao(): MainCategoriesDao
    abstract fun fetchSubCategoriesDao(): SubCategoriesDao
    abstract fun fetchTemplatesDao(): TemplatesDao
    abstract fun fetchUndefinedTasksDao(): UndefinedTasksDao
}

// The Room compiler generates the `actual` implementations.
@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object SchedulesDatabaseConstructor : RoomDatabaseConstructor<SchedulesDataBase> {
    override fun initialize(): SchedulesDataBase
}