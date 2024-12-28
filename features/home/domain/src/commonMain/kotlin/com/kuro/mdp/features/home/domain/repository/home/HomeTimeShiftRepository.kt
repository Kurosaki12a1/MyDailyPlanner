package com.kuro.mdp.features.home.domain.repository.home

import com.kuro.mdp.shared.domain.model.schedules.TimeTask
import com.kuro.mdp.shared.utils.functional.ResultState

/**
 * Created by: minhthinh.h on 12/24/2024
 *
 * Description:
 */
interface HomeTimeShiftRepository {
    suspend fun shiftUpTimeTask(task: TimeTask, shiftValue: Int): ResultState<List<TimeTask>>

    suspend fun shiftDownTimeTask(task: TimeTask, shiftValue: Int): ResultState<TimeTask>
}