package com.kuro.mdp.shared.domain.repository.feature

import kotlinx.datetime.LocalDateTime

interface FeatureScheduleLocalDataSource {
    suspend fun fetchScheduleDate(): LocalDateTime?

    fun setScheduleDate(date: LocalDateTime?)
}