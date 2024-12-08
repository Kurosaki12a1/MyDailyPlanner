package com.kuro.mdp.shared.domain.model.schedules

import com.kuro.mdp.shared.utils.extensions.extractAllItem
import com.kuro.mdp.shared.utils.functional.Mapper
import kotlinx.serialization.Serializable

@Serializable
data class Schedule(
    val date: Long,
    val status: DailyScheduleStatus,
    val timeTasks: List<TimeTask> = emptyList(),
    val overlayTimeTasks: List<TimeTask> = emptyList(),
) {
    fun <T> map(mapper: Mapper<Schedule, T>) = mapper.map(this)
}

fun List<Schedule>.fetchAllTimeTasks() = map { it.overlayTimeTasks + it.timeTasks }.extractAllItem()
