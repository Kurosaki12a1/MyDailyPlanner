package com.kuro.mdp.shared.presentation.notifications

import com.kuro.mdp.shared.domain.model.template.RepeatTime
import com.kuro.mdp.shared.domain.model.template.Template
import com.kuro.mdp.shared.presentation.model.NotificationTimeType
import kotlinx.datetime.LocalDateTime

/**
 * Created by: minhthinh.h on 12/9/2024
 *
 * Description:
 */
interface TemplatesAlarmManager {

    fun addOrUpdateNotifyAlarm(template: Template, repeatTime: RepeatTime)

    fun addRawNotifyAlarm(
        templateId: Int,
        timeType: NotificationTimeType,
        repeatTime: RepeatTime,
        time: LocalDateTime,
        category: String,
        subCategory: String?,
        icon: Int?,
    )

    fun deleteNotifyAlarm(template: Template, repeatTime: RepeatTime)
}
