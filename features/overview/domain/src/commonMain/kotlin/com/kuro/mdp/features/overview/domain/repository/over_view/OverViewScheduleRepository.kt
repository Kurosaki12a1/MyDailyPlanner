package com.kuro.mdp.features.overview.domain.repository.over_view

import com.kuro.mdp.shared.domain.model.schedules.Schedule
import com.kuro.mdp.shared.utils.functional.ResultState
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.LocalDateTime

/**
 * Created by: minhthinh.h on 12/24/2024
 *
 * Description:
 */
interface OverViewScheduleRepository {
    suspend fun fetchOverviewSchedules(): ResultState<List<Schedule>>
    suspend fun fetchScheduleByDate(date: Long): Flow<ResultState<Schedule?>>
    suspend fun createSchedule(requiredDay: LocalDateTime): ResultState<Unit>
    suspend fun updateSchedule(schedule: Schedule): ResultState<Unit>
    suspend fun fetchFeatureScheduleDate(): LocalDateTime?
    fun setFeatureScheduleDate(date: LocalDateTime?)
}