package com.kuro.mdp.features.overview.domain.repository.common

import com.kuro.mdp.features.overview.domain.model.schedules.TimeTaskOverView

/**
 * Created by: minhthinh.h on 1/22/2025
 *
 * Description:
 */
interface TimeTaskStatusController {
    fun updateStatus(timeTask: TimeTaskOverView): TimeTaskOverView
}