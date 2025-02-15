package com.kuro.mdp.features.home.domain.repository.home

import com.kuro.mdp.shared.domain.model.schedules.TimeTask
import com.kuro.mdp.shared.utils.functional.ResultState

/**
 * Created by: minhthinh.h on 2/13/2025
 *
 * Description:
 */
interface HomeTimeTaskRepository {
    suspend fun addTimeTask(timeTask: TimeTask): ResultState<Long>
    suspend fun updateTimeTask(timeTask: TimeTask): ResultState<Long>
    suspend fun deleteTimeTask(key: Long): ResultState<Unit>
}