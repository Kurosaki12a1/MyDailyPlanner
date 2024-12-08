package com.kuro.mdp.shared.domain.repository

import com.kuro.mdp.shared.domain.model.schedules.Schedule
import com.kuro.mdp.shared.utils.functional.TimeRange
import kotlinx.coroutines.flow.Flow


interface ScheduleRepository {
    suspend fun createSchedules(schedules: List<Schedule>)
    suspend fun fetchSchedulesByRange(timeRange: TimeRange?): Flow<List<Schedule>>
    fun fetchScheduleByDate(date: Long): Flow<Schedule?>
    suspend fun updateSchedule(schedule: Schedule)
    suspend fun deleteAllSchedules(): List<Schedule>
}
