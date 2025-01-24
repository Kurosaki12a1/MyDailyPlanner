package com.kuro.mdp.features.overview.data.data_source.api

import kotlinx.datetime.LocalDateTime

/**
 * Created by: minhthinh.h on 1/22/2025
 *
 * Description:
 */
interface FeatureScheduleLocalDataSource {
    suspend fun fetchScheduleDate(): LocalDateTime?

    fun setScheduleDate(date: LocalDateTime?)
}