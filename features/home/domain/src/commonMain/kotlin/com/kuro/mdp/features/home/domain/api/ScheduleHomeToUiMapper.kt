package com.kuro.mdp.features.home.domain.api

import com.kuro.mdp.features.home.domain.model.schedules.ScheduleHome
import com.kuro.mdp.shared.domain.model.schedules.Schedule
import com.kuro.mdp.shared.utils.functional.Mapper

/**
 * Created by: minhthinh.h on 12/25/2024
 *
 * Description:
 */
interface ScheduleHomeToUiMapper : Mapper<Schedule, ScheduleHome>