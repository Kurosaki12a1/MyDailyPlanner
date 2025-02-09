package com.kuro.mdp.features.settings.data.repository

import com.kuro.mdp.features.settings.domain.mapper.templates.convertToTimeTask
import com.kuro.mdp.features.settings.domain.repository.SettingsRepeatTaskRepository
import com.kuro.mdp.shared.domain.model.schedules.Schedule
import com.kuro.mdp.shared.domain.model.schedules.TimeTask
import com.kuro.mdp.shared.domain.model.schedules.fetchAllTimeTasks
import com.kuro.mdp.shared.domain.model.template.RepeatTime
import com.kuro.mdp.shared.domain.model.template.Template
import com.kuro.mdp.shared.domain.repository.ScheduleRepository
import com.kuro.mdp.shared.domain.repository.TimeTaskRepository
import com.kuro.mdp.shared.utils.extensions.generateUniqueKey
import com.kuro.mdp.shared.utils.extensions.mapToDate
import com.kuro.mdp.shared.utils.extensions.startThisDay
import com.kuro.mdp.shared.utils.extensions.toEpochMillis
import com.kuro.mdp.shared.utils.functional.ResultState
import com.kuro.mdp.shared.utils.functional.wrap
import com.kuro.mdp.shared.utils.managers.DateManager
import com.kuro.mdp.shared.utils.managers.TimeOverlayManager
import kotlinx.coroutines.flow.first

/**
 * Created by: minhthinh.h on 2/6/2025
 *
 * Description:
 */
class SettingsRepeatTaskRepositoryImpl(
    private val timeTaskRepository: TimeTaskRepository,
    private val scheduleRepository: ScheduleRepository,
    private val overlayManager: TimeOverlayManager,
    private val dateManager: DateManager
) : SettingsRepeatTaskRepository {
    override suspend fun updateRepeatTemplate(oldTemplate: Template, template: Template): ResultState<List<TimeTask>> = wrap {
        val schedules = filteredSchedules()
        val updateTasks = mutableListOf<TimeTask>()
        val deletableTasksId = mutableListOf<Long>()
        template.repeatTimes.forEach { repeatTime ->
            deletableTasksId.addAll(findRepeatTasksByTemplate(schedules, oldTemplate, repeatTime).map { it.key })
            updateTasks.addAll(createRepeatTasksByTemplate(schedules, template, repeatTime, deletableTasksId))
        }
        return@wrap updateTasks.apply {
            timeTaskRepository.deleteTimeTasks(deletableTasksId)
            timeTaskRepository.addTimeTasks(this)
        }
    }

    override suspend fun addRepeatsTemplate(template: Template, repeatTimes: List<RepeatTime>): ResultState<List<TimeTask>> = wrap {
        val repeatTimeTasks = mutableListOf<TimeTask>()
        repeatTimes.forEach { repeatTime ->
            val timeTasks = createRepeatTasksByTemplate(filteredSchedules(), template, repeatTime).apply {
                timeTaskRepository.addTimeTasks(this)
            }
            repeatTimeTasks.addAll(timeTasks)
        }
        return@wrap repeatTimeTasks
    }

    override suspend fun deleteRepeatsTemplates(template: Template, repeatTimes: List<RepeatTime>): ResultState<List<TimeTask>> = wrap {
        val repeatTimeTasks = mutableListOf<TimeTask>()
        repeatTimes.forEach { repeatTime ->
            val timeTasks = findRepeatTasksByTemplate(filteredSchedules(), template, repeatTime).apply {
                timeTaskRepository.deleteTimeTasks(map { timeTask -> timeTask.key })
            }
            repeatTimeTasks.addAll(timeTasks)
        }
        return@wrap repeatTimeTasks
    }

    private suspend fun filteredSchedules(): List<Schedule> {
        val currentDate = dateManager.fetchBeginningCurrentDay()
        return scheduleRepository.fetchSchedulesByRange(null).first().filter { schedule -> schedule.date > currentDate.toEpochMillis() }
    }

    private fun findRepeatTasksByTemplate(
        schedules: List<Schedule>,
        template: Template,
        repeatTime: RepeatTime,
    ) = mutableListOf<TimeTask>().apply {
        schedules.fetchAllTimeTasks().filter { timeTask ->
            repeatTime.checkDateIsRepeat(timeTask.date)
        }.forEach { timeTask ->
            if (template.equalsIsTemplate(timeTask)) add(timeTask)
        }
    }

    private fun createRepeatTasksByTemplate(
        schedules: List<Schedule>,
        template: Template,
        repeatTime: RepeatTime,
        keyList: List<Long> = emptyList(),
    ) = mutableListOf<TimeTask>().apply {
        schedules.forEach { schedule ->
            val nextSchedule = schedules.getOrNull(schedules.indexOf(schedule) + 1)
            val scheduleTimeTasks = schedule.timeTasks + (nextSchedule?.timeTasks ?: emptyList())
            val scheduleDate = schedule.date.mapToDate().startThisDay()

            if (repeatTime.checkDateIsRepeat(scheduleDate)) {
                val existedTimeTask = schedule.timeTasks.find { keyList.contains(it.key) }
                val timeTaskKey = when (existedTimeTask != null) {
                    true -> existedTimeTask.key
                    false -> generateUniqueKey()
                }
                val repeatTimeTask = template.convertToTimeTask(scheduleDate, timeTaskKey, scheduleDate)
                val scheduleTimeRanges = scheduleTimeTasks.filter { !keyList.contains(it.key) }.map { it.timeRange }
                overlayManager.isOverlay(repeatTimeTask.timeRange, scheduleTimeRanges).let { overlayResult ->
                    if (!overlayResult.isOverlay) add(repeatTimeTask)
                }
            }
        }
    }
}