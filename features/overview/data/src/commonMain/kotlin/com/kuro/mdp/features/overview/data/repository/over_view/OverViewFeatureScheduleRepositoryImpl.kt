package com.kuro.mdp.features.overview.data.repository.over_view

import com.kuro.mdp.features.overview.data.data_source.api.FeatureScheduleLocalDataSource
import com.kuro.mdp.features.overview.domain.repository.over_view.OverViewFeatureScheduleRepository
import kotlinx.datetime.LocalDateTime

/**
 * Created by: minhthinh.h on 1/22/2025
 *
 * Description:
 */
class OverViewFeatureScheduleRepositoryImpl(
    private val localDataSource: FeatureScheduleLocalDataSource
) : OverViewFeatureScheduleRepository {
    override suspend fun fetchScheduleDate(): LocalDateTime? {
        return localDataSource.fetchScheduleDate()
    }

    override fun setScheduleDate(date: LocalDateTime?) {
        localDataSource.setScheduleDate(date)
    }
}