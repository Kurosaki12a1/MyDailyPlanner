package com.kurp.mdp.shared.data.repository.feature

import com.kuro.mdp.shared.domain.repository.feature.FeatureScheduleLocalDataSource
import kotlinx.datetime.LocalDateTime

class FeatureScheduleLocalDataSourceImpl : FeatureScheduleLocalDataSource {

    private var scheduleDate: LocalDateTime? = null

    override suspend fun fetchScheduleDate(): LocalDateTime? = scheduleDate

    override fun setScheduleDate(date: LocalDateTime?) {
        this.scheduleDate = date
    }
}