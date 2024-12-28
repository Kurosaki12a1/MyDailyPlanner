package com.kuro.mdp.features.home.domain.repository.common

import com.kuro.mdp.features.home.domain.model.schedules.TimeTaskHome

/**
 * Created by: minhthinh.h on 12/24/2024
 *
 * Description:
 */
interface TimeTaskStatusController {
    fun updateStatus(timeTask: TimeTaskHome): TimeTaskHome
}