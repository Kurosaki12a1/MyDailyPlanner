package com.kurp.mdp.shared.data.mappers.schedules

import com.kuro.mdp.shared.domain.common.ScheduleStatusChecker
import com.kuro.mdp.shared.domain.model.schedules.Schedule
import com.kuro.mdp.shared.utils.extensions.mapToDate
import com.kuro.mdp.shared.utils.functional.Mapper
import com.kuro.mdp.shared.utils.managers.DateManager
import com.kurp.mdp.shared.data.entities.schedules.ScheduleDetails

interface ScheduleDataToDomainMapper : Mapper<ScheduleDetails, Schedule>

class ScheduleDataToDomainMapperImpl(
    private val scheduleStatusChecker: ScheduleStatusChecker,
    private val dateManager: DateManager,
) : ScheduleDataToDomainMapper {
    override fun map(input: ScheduleDetails): Schedule {
        return Schedule(
            date = input.dailySchedule.date,
            status = scheduleStatusChecker.fetchState(
                requiredDate = input.dailySchedule.date.mapToDate(),
                currentDate = dateManager.fetchBeginningCurrentDay(),
            ),
            timeTasks = input.timeTasks.map { timeTaskDetails ->
                timeTaskDetails.mapToDomain()
            },
            overlayTimeTasks = input.overlayTimeTasks.map { timeTaskDetails ->
                timeTaskDetails.mapToDomain()
            }
        )
    }
}