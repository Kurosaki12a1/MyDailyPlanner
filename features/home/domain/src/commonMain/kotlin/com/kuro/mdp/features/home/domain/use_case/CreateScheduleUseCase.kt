package com.kuro.mdp.features.home.domain.use_case

import com.kuro.mdp.features.home.domain.repository.home.HomeScheduleRepository
import com.kuro.mdp.shared.utils.functional.ResultState
import com.kuro.mdp.shared.utils.functional.handle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.datetime.LocalDateTime

/**
 * Created by: minhthinh.h on 1/13/2025
 *
 * Description:
 */
class CreateScheduleUseCase(
    private val scheduleRepository: HomeScheduleRepository
) {

    operator fun invoke(date: LocalDateTime?): Flow<ResultState<Unit>> = flow {
        if (date != null) {
            scheduleRepository.createSchedule(date).handle(
                onFailure = { e ->
                    emit(ResultState.Failure(e))
                },
                onSuccess = {
                    emit(ResultState.Success(Unit))
                }
            )
        }
    }
}