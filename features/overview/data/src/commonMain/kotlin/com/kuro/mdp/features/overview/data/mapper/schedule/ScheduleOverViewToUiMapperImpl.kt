package com.kuro.mdp.features.overview.data.mapper.schedule

import com.kuro.mdp.features.overview.domain.api.ScheduleOverViewToUiMapper
import com.kuro.mdp.features.overview.domain.api.TimeTaskOverViewToUiMapper
import com.kuro.mdp.features.overview.domain.model.schedules.ScheduleOverView
import com.kuro.mdp.shared.domain.model.schedules.Schedule
import com.kuro.mdp.shared.utils.extensions.mapToDate
import com.kuro.mdp.shared.utils.managers.DateManager

/**
 * Created by: minhthinh.h on 1/22/2025
 *
 * Description:
 */
class ScheduleOverViewToUiMapperImpl(
    private val timeTaskMapperToUi: TimeTaskOverViewToUiMapper,
    private val dateManager: DateManager
) : ScheduleOverViewToUiMapper {
    override fun map(input: Schedule): ScheduleOverView {
        return ScheduleOverView(
            date = input.date.mapToDate(),
            dateStatus = input.status,
            timeTasks = input.timeTasks.map { timeTaskMapperToUi.map(it, false) },
            progress = when (input.timeTasks.isEmpty()) {
                true -> 0f
                false -> input.timeTasks.count { dateManager.fetchCurrentDate().time > it.timeRange.to.time && it.isCompleted } /
                        input.timeTasks.size.toFloat()
            },
        )
    }
}