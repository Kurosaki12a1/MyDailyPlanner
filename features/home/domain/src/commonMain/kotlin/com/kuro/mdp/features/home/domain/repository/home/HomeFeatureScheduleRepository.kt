package com.kuro.mdp.features.home.domain.repository.home

import kotlinx.datetime.LocalDateTime


interface HomeFeatureScheduleRepository {
    suspend fun fetchScheduleDate(): LocalDateTime?
    fun setScheduleDate(date: LocalDateTime?)
}
