package com.kuro.mdp.features.home.data.repository.feature

import com.kuro.mdp.features.home.data.data_source.api.FeatureScheduleLocalDataSource
import com.kuro.mdp.features.home.domain.repository.home.HomeFeatureScheduleRepository
import kotlinx.datetime.LocalDateTime

/**
 * Created by: minhthinh.h on 12/23/2024
 *
 * Description:
 */
class HomeFeatureScheduleRepositoryImpl(
    private val localDataSource: FeatureScheduleLocalDataSource
) : HomeFeatureScheduleRepository {
    override suspend fun fetchScheduleDate(): LocalDateTime? {
        return localDataSource.fetchScheduleDate()
    }

    override fun setScheduleDate(date: LocalDateTime?) {
        localDataSource.setScheduleDate(date)
    }
}