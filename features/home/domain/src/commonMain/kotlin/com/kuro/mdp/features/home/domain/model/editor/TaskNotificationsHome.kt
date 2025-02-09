package com.kuro.mdp.features.home.domain.model.editor

import kotlinx.serialization.Serializable

@Serializable
data class TaskNotificationsHome(
    val fifteenMinutesBefore: Boolean = false,
    val oneHourBefore: Boolean = false,
    val threeHourBefore: Boolean = false,
    val oneDayBefore: Boolean = false,
    val oneWeekBefore: Boolean = false,
    val beforeEnd: Boolean = false,
)
