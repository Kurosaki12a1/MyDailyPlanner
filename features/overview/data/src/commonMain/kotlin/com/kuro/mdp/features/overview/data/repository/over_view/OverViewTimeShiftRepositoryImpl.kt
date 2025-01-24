package com.kuro.mdp.features.overview.data.repository.over_view

import com.kuro.mdp.features.overview.domain.repository.over_view.OverViewTimeShiftRepository
import com.kuro.mdp.shared.domain.model.schedules.TimeTask
import com.kuro.mdp.shared.domain.repository.ScheduleRepository
import com.kuro.mdp.shared.domain.repository.TemplatesRepository
import com.kuro.mdp.shared.domain.repository.TimeTaskRepository
import com.kuro.mdp.shared.utils.extensions.extractAllItem
import com.kuro.mdp.shared.utils.extensions.getLocalDateTimeNow
import com.kuro.mdp.shared.utils.extensions.isCurrentDay
import com.kuro.mdp.shared.utils.extensions.shiftDays
import com.kuro.mdp.shared.utils.extensions.shiftMinutes
import com.kuro.mdp.shared.utils.extensions.toEpochMillis
import com.kuro.mdp.shared.utils.functional.ResultState
import com.kuro.mdp.shared.utils.functional.TimeRange
import com.kuro.mdp.shared.utils.functional.TimeShiftException
import com.kuro.mdp.shared.utils.functional.TimeTaskImportanceException
import com.kuro.mdp.shared.utils.functional.wrap
import kotlinx.coroutines.flow.first

/**
 * Created by: minhthinh.h on 12/24/2024
 *
 * Description:
 */
class OverViewTimeShiftRepositoryImpl(
    private val scheduleRepository: ScheduleRepository,
    private val timeTaskRepository: TimeTaskRepository,
    private val templatesRepository: TemplatesRepository
) : OverViewTimeShiftRepository {
    override suspend fun shiftUpTimeTask(task: TimeTask, shiftValue: Int): ResultState<List<TimeTask>> = wrap {
        val timeRange = TimeRange(task.date.shiftDays(-1), task.date.shiftDays(1))
        val schedules = scheduleRepository.fetchSchedulesByRange(timeRange).first()
        val allTimeTasks = schedules.map { it.timeTasks }.extractAllItem().sortedBy { it.timeRange.from }

        val nextTimeTask = allTimeTasks.firstOrNull { it.timeRange.from >= task.timeRange.to }
        val nextTimeTaskTemplate = templatesRepository.fetchAllTemplates().first().find { template ->
            nextTimeTask?.let { template.equalsIsTemplate(it) } ?: false
        }

        val nextTime = nextTimeTask?.timeRange
        val shiftTime = task.timeRange.to.shiftMinutes(shiftValue)

        return@wrap if (nextTime == null || nextTime.from.toEpochMillis() - shiftTime.toEpochMillis() >= shiftValue) {
            when (shiftTime.isCurrentDay(task.timeRange.to)) {
                true -> listOf(task.copy(timeRange = task.timeRange.copy(to = shiftTime))).apply {
                    timeTaskRepository.updateTimeTaskList(this)
                }

                false -> throw TimeShiftException()
            }
        } else {
            when (nextTime.to.toEpochMillis() - shiftTime.toEpochMillis() > 0) {
                true -> {
                    if (nextTimeTask.priority.isImportant() || nextTimeTaskTemplate?.checkDateIsRepeat(getLocalDateTimeNow()) == true) {
                        throw TimeTaskImportanceException()
                    }
                    val shiftTask = task.copy(timeRange = task.timeRange.copy(to = shiftTime))
                    val nextShiftTask = nextTimeTask.copy(timeRange = nextTimeTask.timeRange.copy(from = shiftTime))
                    val updatedTasks = listOf(shiftTask, nextShiftTask)
                    timeTaskRepository.updateTimeTaskList(updatedTasks)
                    return@wrap updatedTasks
                }

                false -> throw TimeShiftException()
            }
        }
    }

    override suspend fun shiftDownTimeTask(task: TimeTask, shiftValue: Int): ResultState<TimeTask> = wrap {
        val shiftTime = task.timeRange.to.shiftMinutes(-shiftValue)
        if (shiftTime.toEpochMillis() - task.timeRange.from.toEpochMillis() > 0) {
            val timeRanges = task.timeRange.copy(to = shiftTime)
            val shiftTask = task.copy(timeRange = timeRanges)
            timeTaskRepository.updateTimeTask(shiftTask)
            return@wrap shiftTask
        } else {
            throw TimeShiftException()
        }
    }
}