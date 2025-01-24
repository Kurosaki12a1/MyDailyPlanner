package com.kuro.mdp.features.home.data.repository.home

import com.kuro.mdp.features.home.domain.mapper.templates.convertToTimeTask
import com.kuro.mdp.features.home.domain.repository.home.HomeFeatureScheduleRepository
import com.kuro.mdp.features.home.domain.repository.home.HomeScheduleRepository
import com.kuro.mdp.shared.domain.common.ScheduleStatusChecker
import com.kuro.mdp.shared.domain.model.schedules.Schedule
import com.kuro.mdp.shared.domain.model.schedules.TimeTask
import com.kuro.mdp.shared.domain.model.template.Template
import com.kuro.mdp.shared.domain.repository.ScheduleRepository
import com.kuro.mdp.shared.domain.repository.TemplatesRepository
import com.kuro.mdp.shared.utils.extensions.daysToMillis
import com.kuro.mdp.shared.utils.extensions.mapToDate
import com.kuro.mdp.shared.utils.extensions.shiftDays
import com.kuro.mdp.shared.utils.extensions.toEpochMillis
import com.kuro.mdp.shared.utils.functional.Constants.Date.NEXT_REPEAT_LIMIT
import com.kuro.mdp.shared.utils.functional.Constants.Date.OVERVIEW_NEXT_DAYS
import com.kuro.mdp.shared.utils.functional.Constants.Date.OVERVIEW_PREVIOUS_DAYS
import com.kuro.mdp.shared.utils.functional.ResultState
import com.kuro.mdp.shared.utils.functional.wrap
import com.kuro.mdp.shared.utils.functional.wrapFlow
import com.kuro.mdp.shared.utils.managers.DateManager
import com.kuro.mdp.shared.utils.managers.TimeOverlayManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.datetime.LocalDateTime

/**
 * Created by: minhthinh.h on 12/24/2024
 *
 * Description:
 */
class HomeScheduleRepositoryImpl(
    private val scheduleRepository: ScheduleRepository,
    private val homeFeatureScheduleRepository: HomeFeatureScheduleRepository,
    private val templatesRepository: TemplatesRepository,
    private val statusChecker: ScheduleStatusChecker,
    private val dateManager: DateManager,
    private val overlayManager: TimeOverlayManager
) : HomeScheduleRepository {
    override suspend fun fetchOverviewSchedules(): ResultState<List<Schedule>> = wrap {
        val currentDate = dateManager.fetchBeginningCurrentDay()
        val startDate = currentDate.shiftDays(-OVERVIEW_PREVIOUS_DAYS)
        mutableListOf<Schedule>().apply {
            for (shiftAmount in 0..OVERVIEW_NEXT_DAYS + OVERVIEW_PREVIOUS_DAYS) {
                val date = startDate.shiftDays(shiftAmount).toEpochMillis()
                val schedule = scheduleRepository.fetchScheduleByDate(date).firstOrNull() ?: if (date >= currentDate.toEpochMillis()) {
                    createRecurringSchedule(date.mapToDate(), currentDate)
                } else {
                    null
                }
                add(schedule ?: Schedule(date = date, status = statusChecker.fetchState(date.mapToDate(), currentDate)))
            }
        }
    }

    override suspend fun fetchScheduleByDate(date: Long): Flow<ResultState<Schedule?>> = wrapFlow {
        val currentDate = dateManager.fetchBeginningCurrentDay()
        val limit = NEXT_REPEAT_LIMIT.daysToMillis()
        scheduleRepository.fetchScheduleByDate(date).map { schedule ->
            if (schedule != null) {
                val timeTasks = schedule.overlayTimeTasks + schedule.timeTasks
                val sortedTasks = timeTasks.sortedBy { timeTask -> timeTask.timeRange.to }
                schedule.copy(timeTasks = sortedTasks)
            } else if (date >= currentDate.toEpochMillis() && date - currentDate.toEpochMillis() <= limit) {
                createRecurringSchedule(date.mapToDate(), currentDate)
            } else {
                null
            }
        }
    }

    override suspend fun createSchedule(requiredDay: LocalDateTime): ResultState<Unit> = wrap {
        val currentDate = dateManager.fetchBeginningCurrentDay()
        val status = statusChecker.fetchState(requiredDay, currentDate)
        val schedule = Schedule(date = requiredDay.toEpochMillis(), status = status)
        scheduleRepository.createSchedules(listOf(schedule))
    }

    override suspend fun updateSchedule(schedule: Schedule): ResultState<Unit> = wrap {
        scheduleRepository.updateSchedule(schedule)
    }

    override suspend fun fetchFeatureScheduleDate(): LocalDateTime? {
        return homeFeatureScheduleRepository.fetchScheduleDate().apply {
            homeFeatureScheduleRepository.setScheduleDate(null)
        }
    }

    override fun setFeatureScheduleDate(date: LocalDateTime?) {
        homeFeatureScheduleRepository.setScheduleDate(date)
    }

    private suspend fun createRecurringSchedule(target: LocalDateTime, current: LocalDateTime): Schedule? {
        val templates = foundPlannedTemplates(target).apply { if (this.isEmpty()) return null }
        val templatesTimeTasks = templates.map { it.convertToTimeTask(date = target, createdAt = target) }
        val correctTimeTasks = mutableListOf<TimeTask>().apply {
            templatesTimeTasks.sortedBy { it.timeRange.from }.forEach { timeTask ->
                val allTimeRanges = map { it.timeRange }
                val overlayResult = overlayManager.isOverlay(timeTask.timeRange, allTimeRanges)
                if (!overlayResult.isOverlay) add(timeTask)
            }
        }

        val status = statusChecker.fetchState(target, current)
        return Schedule(date = target.toEpochMillis(), status = status, timeTasks = correctTimeTasks).apply {
            scheduleRepository.createSchedules(listOf(this))
        }
    }

    private suspend fun foundPlannedTemplates(date: LocalDateTime) = mutableListOf<Template>().apply {
        templatesRepository.fetchAllTemplates().first().filter { template ->
            template.repeatEnabled
        }.forEach { template ->
            template.repeatTimes.forEach { repeatTime ->
                if (repeatTime.checkDateIsRepeat(date)) add(template)
            }
        }
    }
}