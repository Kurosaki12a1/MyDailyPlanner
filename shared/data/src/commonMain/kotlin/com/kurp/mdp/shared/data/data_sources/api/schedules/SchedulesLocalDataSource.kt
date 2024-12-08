package com.kurp.mdp.shared.data.data_sources.api.schedules

import com.kuro.mdp.shared.utils.functional.TimeRange
import com.kurp.mdp.shared.data.entities.schedules.DailyScheduleEntity
import com.kurp.mdp.shared.data.entities.schedules.ScheduleDetails
import com.kurp.mdp.shared.data.entities.tasks.TimeTaskEntity
import kotlinx.coroutines.flow.Flow

interface SchedulesLocalDataSource {

    suspend fun addSchedules(schedules: List<DailyScheduleEntity>, timeTasks: List<TimeTaskEntity>)
    suspend fun addTimeTasks(tasks: List<TimeTaskEntity>)
    fun fetchScheduleByDate(date: Long): Flow<ScheduleDetails?>
    suspend fun fetchScheduleByRange(timeRange: TimeRange?): Flow<List<ScheduleDetails>>
    suspend fun updateTimeTasks(timeTasks: List<TimeTaskEntity>)
    suspend fun removeDailySchedule(schedule: DailyScheduleEntity)
    suspend fun removeAllSchedules(): List<ScheduleDetails>
    suspend fun removeTimeTasksByKey(keys: List<Long>)
}