package com.kuro.mdp.features.overview.domain.repository.over_view

import kotlinx.datetime.LocalDateTime


interface OverViewFeatureScheduleRepository {
    suspend fun fetchScheduleDate(): LocalDateTime?
    fun setScheduleDate(date: LocalDateTime?)
}
