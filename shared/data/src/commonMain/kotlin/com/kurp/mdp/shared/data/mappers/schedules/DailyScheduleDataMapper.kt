package com.kurp.mdp.shared.data.mappers.schedules

import com.kuro.mdp.shared.domain.model.schedules.Schedule
import com.kurp.mdp.shared.data.entities.schedules.DailyScheduleEntity

fun Schedule.mapToData() = DailyScheduleEntity(date = date)