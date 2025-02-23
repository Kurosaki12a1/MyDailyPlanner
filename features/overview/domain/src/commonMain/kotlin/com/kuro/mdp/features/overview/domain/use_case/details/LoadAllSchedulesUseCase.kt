package com.kuro.mdp.features.overview.domain.use_case.details

import com.kuro.mdp.features.overview.domain.api.ScheduleOverViewToUiMapper
import com.kuro.mdp.features.overview.domain.model.details.DetailsAction
import com.kuro.mdp.features.overview.domain.repository.over_view.OverViewScheduleRepository
import com.kuro.mdp.shared.utils.functional.ResultState
import com.kuro.mdp.shared.utils.functional.handle
import com.kuro.mdp.shared.utils.managers.DateManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by: minhthinh.h on 2/18/2025
 *
 * Description:
 */
class LoadAllSchedulesUseCase(
    private val scheduleRepository: OverViewScheduleRepository,
    private val schedulesUiMapper: ScheduleOverViewToUiMapper,
    private val dateManager: DateManager
) {

    operator fun invoke(): Flow<ResultState<DetailsAction>> = flow {
        val currentDate = dateManager.fetchBeginningCurrentDay()
        scheduleRepository.fetchOverviewSchedules().handle(
            onFailure = { emit(ResultState.Failure(it)) },
            onSuccess = { schedules ->
                emit(ResultState.Success(DetailsAction.UpdateSchedules(currentDate, schedules.map { schedulesUiMapper.map(it) })))
            }
        )
    }
}