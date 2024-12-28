package com.kuro.mdp.features.home.domain.repository.feature

import kotlinx.datetime.LocalDateTime


interface FeatureScheduleRepository {
    suspend fun fetchScheduleDate(): LocalDateTime?
    fun setScheduleDate(date: LocalDateTime?)
}
