package com.kurp.mdp.shared.data.repository.feature

import com.kuro.mdp.shared.domain.repository.feature.FeatureScheduleLocalDataSource
import com.kuro.mdp.shared.domain.repository.feature.FeatureScheduleRepository
import kotlinx.datetime.LocalDateTime

class FeatureScheduleRepositoryImpl(
    private val localDataSource: FeatureScheduleLocalDataSource
) : FeatureScheduleRepository {
    override suspend fun fetchScheduleDate(): LocalDateTime? {
        return localDataSource.fetchScheduleDate()
    }

    override fun setScheduleDate(date: LocalDateTime?) {
        localDataSource.setScheduleDate(date)
    }
}