package com.kuro.mdp.shared.domain.repository.feature

import kotlinx.datetime.LocalDateTime


interface FeatureScheduleRepository {
    suspend fun fetchScheduleDate(): LocalDateTime?

    fun setScheduleDate(date: LocalDateTime?)
}
