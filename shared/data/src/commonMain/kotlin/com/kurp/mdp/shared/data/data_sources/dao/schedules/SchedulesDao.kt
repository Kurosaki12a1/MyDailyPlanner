package com.kurp.mdp.shared.data.data_sources.dao.schedules

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.kurp.mdp.shared.data.entities.schedules.DailyScheduleEntity
import com.kurp.mdp.shared.data.entities.schedules.ScheduleDetails
import com.kurp.mdp.shared.data.entities.tasks.TimeTaskEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SchedulesDao {

    @Transaction
    @Query("SELECT * FROM dailySchedules WHERE date >= :start AND date <= :end")
    fun fetchDailySchedulesByRange(start: Long, end: Long): Flow<List<ScheduleDetails>>

    @Transaction
    @Query("SELECT * FROM dailySchedules")
    fun fetchAllSchedules(): Flow<List<ScheduleDetails>>

    @Transaction
    @Query("SELECT * FROM dailySchedules WHERE date >= :startOfDay AND date <= :endOfDay")
    fun fetchDailyScheduleByDate(startOfDay: Long, endOfDay : Long): Flow<ScheduleDetails?>

    @Update
    suspend fun updateDailySchedules(schedules: List<DailyScheduleEntity>)

    @Update
    suspend fun updateTimeTasks(schedules: List<TimeTaskEntity>)

    @Insert(entity = DailyScheduleEntity::class)
    suspend fun addDailySchedules(schedules: List<DailyScheduleEntity>)

    @Insert(entity = TimeTaskEntity::class)
    suspend fun addTimeTasks(tasks: List<TimeTaskEntity>): List<Long?>

    @Delete
    suspend fun removeDailySchedule(schedule: DailyScheduleEntity)

    @Query("DELETE FROM timeTasks WHERE `key` IN (:keys)")
    suspend fun removeTimeTasksByKey(keys: List<Long>)

    @Query("DELETE FROM dailySchedules")
    suspend fun removeAllSchedules()
}
