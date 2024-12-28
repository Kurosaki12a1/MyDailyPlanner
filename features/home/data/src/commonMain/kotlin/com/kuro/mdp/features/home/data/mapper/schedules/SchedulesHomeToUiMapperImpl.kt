package com.kuro.mdp.features.home.data.mapper.schedules

import com.kuro.mdp.features.home.domain.api.ScheduleHomeToUiMapper
import com.kuro.mdp.features.home.domain.api.TimeTaskHomeToUiMapper
import com.kuro.mdp.features.home.domain.model.schedules.ScheduleHome
import com.kuro.mdp.shared.domain.model.schedules.Schedule
import com.kuro.mdp.shared.utils.extensions.mapToDate
import com.kuro.mdp.shared.utils.managers.DateManager

/**
 * Created by: minhthinh.h on 12/25/2024
 *
 * Description:
 */
class SchedulesHomeToUiMapperImpl(
    private val timeTaskMapperToUi: TimeTaskHomeToUiMapper,
    private val dateManager: DateManager
) : ScheduleHomeToUiMapper {
    override fun map(input: Schedule): ScheduleHome {
        return ScheduleHome(
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