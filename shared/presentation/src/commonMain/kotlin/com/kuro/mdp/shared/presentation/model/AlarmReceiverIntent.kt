package com.kuro.mdp.shared.presentation.model

import com.kuro.mdp.shared.domain.model.template.RepeatTime
import kotlinx.datetime.LocalDateTime

/**
 * Created by: minhthinh.h on 12/9/2024
 *
 * Description:
 */
data class AlarmReceiverIntent(
    val category: String,
    val subCategory: String,
    val icon: Int?,
    val appIcon: Int,
    val time: LocalDateTime? = null,
    val templateId: Int? = null,
    val repeatTime: RepeatTime? = null,
    val timeType: NotificationTimeType
)