package com.kuro.mdp.features.home.data.repository.home

import com.kuro.mdp.features.home.domain.repository.home.HomeTimeTaskRepository
import com.kuro.mdp.shared.domain.common.ScheduleStatusChecker
import com.kuro.mdp.shared.domain.model.schedules.Schedule
import com.kuro.mdp.shared.domain.model.schedules.TimeTask
import com.kuro.mdp.shared.domain.repository.ScheduleRepository
import com.kuro.mdp.shared.domain.repository.TimeTaskRepository
import com.kuro.mdp.shared.utils.extensions.extractAllItem
import com.kuro.mdp.shared.utils.extensions.generateUniqueKey
import com.kuro.mdp.shared.utils.extensions.shiftDays
import com.kuro.mdp.shared.utils.extensions.toEpochMillis
import com.kuro.mdp.shared.utils.functional.TimeRange
import com.kuro.mdp.shared.utils.functional.wrap
import com.kuro.mdp.shared.utils.managers.DateManager
import com.kuro.mdp.shared.utils.managers.TimeOverlayException
import com.kuro.mdp.shared.utils.managers.TimeOverlayManager
import kotlinx.coroutines.flow.first

/**
 * Created by: minhthinh.h on 2/13/2025
 *
 * Description:
 */
class HomeTimeTaskRepositoryImpl(
    private val scheduleRepository: ScheduleRepository,
    private val timeTaskRepository: TimeTaskRepository,
    private val statusChecker: ScheduleStatusChecker,
    private val overlayManager: TimeOverlayManager,
    private val dateManager: DateManager
) : HomeTimeTaskRepository {

    override suspend fun addTimeTask(timeTask: TimeTask) = wrap {
        val timeRange = TimeRange(timeTask.date.shiftDays(-1), timeTask.date.shiftDays(1))
        val schedules = scheduleRepository.fetchSchedulesByRange(timeRange).first().let { schedules ->
            if (schedules.find { it.date == timeTask.date.toEpochMillis() } == null) {
                val createdSchedule = Schedule(
                    date = timeTask.date.toEpochMillis(),
                    status = statusChecker.fetchState(timeTask.date, dateManager.fetchBeginningCurrentDay()),
                )
                scheduleRepository.createSchedules(listOf(createdSchedule))
                listOf(createdSchedule)
            } else {
                schedules
            }
        }
        val allTimeTask = schedules.map { it.timeTasks }.extractAllItem()
        val key = generateUniqueKey()

        checkIsOverlay(allTimeTask.map { it.timeRange }, timeTask.timeRange) {
            timeTaskRepository.addTimeTasks(listOf(timeTask.copy(key = key)))
        }
        return@wrap key
    }

    override suspend fun updateTimeTask(timeTask: TimeTask) = wrap {
        val timeRange = TimeRange(timeTask.date.shiftDays(-1), timeTask.date.shiftDays(1))
        val schedules = scheduleRepository.fetchSchedulesByRange(timeRange).first()
        val allTimeTask = schedules.map { it.timeTasks }.extractAllItem().toMutableList().apply {
            removeAll { it.key == timeTask.key }
        }

        checkIsOverlay(allTimeTask.map { it.timeRange }, timeTask.timeRange) {
            timeTaskRepository.updateTimeTask(timeTask)
        }
        return@wrap timeTask.key
    }

    override suspend fun deleteTimeTask(key: Long) = wrap {
        timeTaskRepository.deleteTimeTasks(listOf(key))
    }

    private suspend fun checkIsOverlay(
        allRanges: List<TimeRange>,
        range: TimeRange,
        block: suspend () -> Unit,
    ) = overlayManager.isOverlay(range, allRanges).let { result ->
        if (result.isOverlay) {
            throw TimeOverlayException(result.leftTimeBorder, result.rightTimeBorder)
        } else {
            block()
        }
    }
}