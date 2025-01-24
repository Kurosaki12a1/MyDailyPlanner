package com.kuro.mdp.features.overview.domain.model.schedules

import kotlinx.serialization.Serializable

/**
 * Created by: minhthinh.h on 12/19/2024
 *
 * Description:
 */
@Serializable
data class TaskNotificationsOverView(
    val fifteenMinutesBefore: Boolean = false,
    val oneHourBefore: Boolean = false,
    val threeHourBefore: Boolean = false,
    val oneDayBefore: Boolean = false,
    val oneWeekBefore: Boolean = false,
    val beforeEnd: Boolean = false,
)
