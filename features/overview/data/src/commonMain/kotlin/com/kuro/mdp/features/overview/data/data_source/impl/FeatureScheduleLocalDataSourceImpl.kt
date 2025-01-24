package com.kuro.mdp.features.overview.data.data_source.impl

import com.kuro.mdp.features.overview.data.data_source.api.FeatureScheduleLocalDataSource
import kotlinx.datetime.LocalDateTime

/**
 * Created by: minhthinh.h on 1/22/2025
 *
 * Description:
 */
class FeatureScheduleLocalDataSourceImpl : FeatureScheduleLocalDataSource {
    private var scheduleDate: LocalDateTime? = null

    override suspend fun fetchScheduleDate(): LocalDateTime? = scheduleDate

    override fun setScheduleDate(date: LocalDateTime?) {
        this.scheduleDate = date
    }
}