package com.kuro.mdp.features.home.data.data_source.api

import kotlinx.datetime.LocalDateTime

/**
 * Created by: minhthinh.h on 12/23/2024
 *
 * Description:
 */
interface FeatureScheduleLocalDataSource {
    suspend fun fetchScheduleDate(): LocalDateTime?

    fun setScheduleDate(date: LocalDateTime?)
}