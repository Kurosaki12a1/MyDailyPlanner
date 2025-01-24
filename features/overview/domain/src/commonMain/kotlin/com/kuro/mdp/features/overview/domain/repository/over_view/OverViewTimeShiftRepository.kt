package com.kuro.mdp.features.overview.domain.repository.over_view

import com.kuro.mdp.shared.domain.model.schedules.TimeTask
import com.kuro.mdp.shared.utils.functional.ResultState

/**
 * Created by: minhthinh.h on 12/24/2024
 *
 * Description:
 */
interface OverViewTimeShiftRepository {
    suspend fun shiftUpTimeTask(task: TimeTask, shiftValue: Int): ResultState<List<TimeTask>>

    suspend fun shiftDownTimeTask(task: TimeTask, shiftValue: Int): ResultState<TimeTask>
}