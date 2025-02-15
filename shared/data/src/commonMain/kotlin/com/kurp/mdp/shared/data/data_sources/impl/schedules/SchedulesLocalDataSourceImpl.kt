package com.kurp.mdp.shared.data.data_sources.impl.schedules

import com.kuro.mdp.shared.utils.extensions.endThisDay
import com.kuro.mdp.shared.utils.extensions.mapToDate
import com.kuro.mdp.shared.utils.extensions.startThisDay
import com.kuro.mdp.shared.utils.extensions.toEpochMillis
import com.kuro.mdp.shared.utils.functional.TimeRange
import com.kurp.mdp.shared.data.data_sources.api.schedules.SchedulesLocalDataSource
import com.kurp.mdp.shared.data.data_sources.dao.schedules.SchedulesDao
import com.kurp.mdp.shared.data.entities.schedules.DailyScheduleEntity
import com.kurp.mdp.shared.data.entities.schedules.ScheduleDetails
import com.kurp.mdp.shared.data.entities.tasks.TimeTaskEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

class SchedulesLocalDataSourceImpl(
    private val scheduleDao: SchedulesDao,
) : SchedulesLocalDataSource {

    override suspend fun addSchedules(
        schedules: List<DailyScheduleEntity>,
        timeTasks: List<TimeTaskEntity>,
    ) {
        scheduleDao.addDailySchedules(schedules)
        scheduleDao.addTimeTasks(timeTasks)
    }

    override suspend fun addTimeTasks(tasks: List<TimeTaskEntity>) {
        scheduleDao.addTimeTasks(tasks)
    }

    override fun fetchScheduleByDate(date: Long): Flow<ScheduleDetails?> {
        val startDate = date.mapToDate().startThisDay().toEpochMillis()
        val endDate = date.mapToDate().endThisDay().toEpochMillis()
        return scheduleDao.fetchDailyScheduleByDate(startDate, endDate)
    }

    override suspend fun fetchScheduleByRange(timeRange: TimeRange?): Flow<List<ScheduleDetails>> {
        return if (timeRange != null) {
            scheduleDao.fetchDailySchedulesByRange(
                timeRange.from.toEpochMillis(), timeRange.to.toEpochMillis()
            )
        } else {
            scheduleDao.fetchAllSchedules()
        }
    }

    override suspend fun updateTimeTasks(timeTasks: List<TimeTaskEntity>) {
        scheduleDao.updateTimeTasks(timeTasks)
    }

    override suspend fun removeTimeTasksByKey(keys: List<Long>) {
        scheduleDao.removeTimeTasksByKey(keys)
    }

    override suspend fun removeDailySchedule(schedule: DailyScheduleEntity) {
        scheduleDao.removeDailySchedule(schedule)
    }

    override suspend fun removeAllSchedules(): List<ScheduleDetails> {
        val deletableSchedules = scheduleDao.fetchAllSchedules().first()
        scheduleDao.removeAllSchedules()

        return deletableSchedules
    }
}