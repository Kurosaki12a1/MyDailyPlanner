package com.kurp.mdp.shared.data.repository

import com.kuro.mdp.shared.domain.model.schedules.TimeTask
import com.kuro.mdp.shared.domain.repository.TimeTaskRepository
import com.kuro.mdp.shared.utils.extensions.toEpochMillis
import com.kurp.mdp.shared.data.data_sources.api.schedules.SchedulesLocalDataSource
import com.kurp.mdp.shared.data.mappers.schedules.mapToData
import com.kurp.mdp.shared.data.mappers.schedules.mapToDomain
import kotlinx.coroutines.flow.first
import kotlinx.datetime.LocalDateTime

/**
 * Created by: minhthinh.h on 12/23/2024
 *
 * Description:
 */
class TimeTaskRepositoryImpl(
    private val localDataSource: SchedulesLocalDataSource
) : TimeTaskRepository {
    override suspend fun addTimeTasks(timeTasks: List<TimeTask>) {
        localDataSource.addTimeTasks(timeTasks.map { it.mapToData() })
    }

    override suspend fun fetchAllTimeTaskByDate(date: LocalDateTime): List<TimeTask> {
        val schedules = localDataSource.fetchScheduleByDate(date.toEpochMillis()).first() ?: return emptyList()
        val timeTasks = schedules.overlayTimeTasks + schedules.timeTasks
        return timeTasks.map { timeTaskDetails -> timeTaskDetails.mapToDomain() }
    }

    override suspend fun updateTimeTaskList(timeTaskList: List<TimeTask>) {
        localDataSource.updateTimeTasks(timeTaskList.map { it.mapToData() })
    }

    override suspend fun updateTimeTask(timeTask: TimeTask) {
        localDataSource.updateTimeTasks(listOf(timeTask.mapToData()))
    }

    override suspend fun deleteTimeTasks(keys: List<Long>) {
        localDataSource.removeTimeTasksByKey(keys)
    }
}