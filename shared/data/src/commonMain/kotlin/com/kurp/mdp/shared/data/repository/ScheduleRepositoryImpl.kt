package com.kurp.mdp.shared.data.repository

import com.kuro.mdp.shared.domain.model.schedules.Schedule
import com.kuro.mdp.shared.domain.repository.ScheduleRepository
import com.kuro.mdp.shared.utils.functional.TimeRange
import com.kurp.mdp.shared.data.data_sources.api.schedules.SchedulesLocalDataSource
import com.kurp.mdp.shared.data.entities.tasks.TimeTaskEntity
import com.kurp.mdp.shared.data.mappers.schedules.ScheduleDataToDomainMapper
import com.kurp.mdp.shared.data.mappers.schedules.mapToData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Created by: minhthinh.h on 12/23/2024
 *
 * Description:
 */
class ScheduleRepositoryImpl(
    private val localDataSource: SchedulesLocalDataSource,
    private val mapperToDomain: ScheduleDataToDomainMapper
) : ScheduleRepository {
    override suspend fun fetchSchedulesByRange(timeRange: TimeRange?): Flow<List<Schedule>> {
        return localDataSource.fetchScheduleByRange(timeRange).map { schedules ->
            schedules.map { mapperToDomain.map(it) }
        }
    }

    override fun fetchScheduleByDate(date: Long): Flow<Schedule?> {
        return localDataSource.fetchScheduleByDate(date).map { it?.map(mapperToDomain) }
    }

    override suspend fun createSchedules(schedules: List<Schedule>) {
        val dailySchedules = schedules.map { it.mapToData() }
        val timeTasks = mutableListOf<TimeTaskEntity>().apply {
            schedules.forEach { schedule ->
                addAll(schedule.timeTasks.map { it.mapToData() })
            }
        }
        localDataSource.addSchedules(dailySchedules, timeTasks)
    }

    override suspend fun updateSchedule(schedule: Schedule) {
        localDataSource.updateTimeTasks(schedule.timeTasks.map { it.mapToData() })
    }

    override suspend fun deleteAllSchedules(): List<Schedule> {
        return localDataSource.removeAllSchedules().map { mapperToDomain.map(it) }
    }
}