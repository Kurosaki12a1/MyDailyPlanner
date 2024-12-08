package com.kurp.mdp.shared.data.entities.schedules

import androidx.room.Embedded
import androidx.room.Relation
import com.kuro.mdp.shared.utils.functional.Mapper
import com.kurp.mdp.shared.data.entities.tasks.TimeTaskDetails
import com.kurp.mdp.shared.data.entities.tasks.TimeTaskEntity

data class ScheduleDetails(
    @Embedded
    val dailySchedule: DailyScheduleEntity,
    @Relation(
        parentColumn = "date",
        entityColumn = "daily_schedule_date",
        entity = TimeTaskEntity::class,
    )
    val timeTasks: List<TimeTaskDetails>,
    @Relation(
        parentColumn = "date",
        entityColumn = "next_schedule_date",
        entity = TimeTaskEntity::class,
    )
    val overlayTimeTasks: List<TimeTaskDetails>,
) {
    fun <T> map(mapper: Mapper<ScheduleDetails, T>) = mapper.map(this)
}
