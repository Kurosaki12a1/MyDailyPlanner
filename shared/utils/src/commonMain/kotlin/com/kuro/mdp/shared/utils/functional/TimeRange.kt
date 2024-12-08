package com.kuro.mdp.shared.utils.functional

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class TimeRange(
    @Serializable(LocalDateTimeSerializer::class) val from: LocalDateTime,
    @Serializable(LocalDateTimeSerializer::class) val to: LocalDateTime,
)
