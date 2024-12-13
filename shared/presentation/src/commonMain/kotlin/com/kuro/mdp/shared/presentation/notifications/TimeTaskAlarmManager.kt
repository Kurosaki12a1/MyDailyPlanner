package com.kuro.mdp.shared.presentation.notifications

import com.kuro.mdp.shared.domain.model.schedules.TimeTask

/**
 * Created by: minhthinh.h on 12/9/2024
 *
 * Description:
 */
interface TimeTaskAlarmManager {
    fun addOrUpdateNotifyAlarm(timeTask: TimeTask)
    fun deleteNotifyAlarm(timeTask: TimeTask)
}