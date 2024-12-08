package com.kuro.mdp.shared.domain.repository

import com.kuro.mdp.shared.domain.model.schedules.TimeTask
import kotlinx.datetime.LocalDateTime


interface TimeTaskRepository {
    suspend fun addTimeTasks(timeTasks: List<TimeTask>)
    suspend fun fetchAllTimeTaskByDate(date: LocalDateTime): List<TimeTask>
    suspend fun updateTimeTaskList(timeTaskList: List<TimeTask>)
    suspend fun updateTimeTask(timeTask: TimeTask)
    suspend fun deleteTimeTasks(keys: List<Long>)
}
