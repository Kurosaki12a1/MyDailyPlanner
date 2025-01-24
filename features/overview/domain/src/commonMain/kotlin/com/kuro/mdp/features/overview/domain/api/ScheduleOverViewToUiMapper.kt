package com.kuro.mdp.features.overview.domain.api

import com.kuro.mdp.features.overview.domain.model.schedules.ScheduleOverView
import com.kuro.mdp.shared.domain.model.schedules.Schedule
import com.kuro.mdp.shared.utils.functional.Mapper

/**
 * Created by: minhthinh.h on 12/25/2024
 *
 * Description:
 */
interface ScheduleOverViewToUiMapper : Mapper<Schedule, ScheduleOverView>