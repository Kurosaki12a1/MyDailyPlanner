package com.kuro.mdp.app.domain.use_case

import com.kuro.mdp.app.domain.models.MainAction
import com.kuro.mdp.shared.domain.repository.feature.FeatureScheduleRepository
import com.kuro.mdp.shared.utils.extensions.getLocalDateTimeNow
import com.kuro.mdp.shared.utils.extensions.mapToDate
import com.kuro.mdp.shared.utils.functional.ResultState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by: minhthinh.h on 2/7/2025
 *
 * Description:
 */
class UpdateScheduleDateUseCase(
    private val featureScheduleRepository: FeatureScheduleRepository,
) {
    operator fun invoke(date: Long?): Flow<ResultState<MainAction>> = flow {
        if (date != null || featureScheduleRepository.fetchScheduleDate() == null) {
            val result = date?.mapToDate() ?: getLocalDateTimeNow()
            featureScheduleRepository.setScheduleDate(result)
            emit(ResultState.Success(MainAction.UpdateScheduleDate(result)))
        }
    }
}