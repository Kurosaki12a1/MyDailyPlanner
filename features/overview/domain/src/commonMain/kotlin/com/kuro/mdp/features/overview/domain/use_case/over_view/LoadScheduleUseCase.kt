package com.kuro.mdp.features.overview.domain.use_case.over_view

import com.kuro.mdp.features.overview.domain.api.ScheduleOverViewToUiMapper
import com.kuro.mdp.features.overview.domain.model.actions.OverViewAction
import com.kuro.mdp.features.overview.domain.repository.over_view.OverViewScheduleRepository
import com.kuro.mdp.shared.utils.extensions.isIncludeTime
import com.kuro.mdp.shared.utils.extensions.mapToDate
import com.kuro.mdp.shared.utils.extensions.shiftDays
import com.kuro.mdp.shared.utils.functional.ResultState
import com.kuro.mdp.shared.utils.functional.TimeRange
import com.kuro.mdp.shared.utils.functional.handle
import com.kuro.mdp.shared.utils.managers.DateManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by: minhthinh.h on 1/22/2025
 *
 * Description:
 */
class LoadScheduleUseCase(
    private val dateManager: DateManager,
    private val scheduleRepository: OverViewScheduleRepository,
    private val schedulesUiMapper: ScheduleOverViewToUiMapper
) {
    operator fun invoke(): Flow<ResultState<OverViewAction>> = flow {
        val currentDate = dateManager.fetchBeginningCurrentDay()
        val previewTimeRange = TimeRange(currentDate.shiftDays(-1), currentDate.shiftDays(2))
        scheduleRepository.fetchOverviewSchedules().handle(
            onFailure = {
                emit(ResultState.Failure(it))
            }, onSuccess = { schedules ->
                val previewSchedules = schedules.filter { previewTimeRange.isIncludeTime(it.date.mapToDate()) }
                    .map { schedulesUiMapper.map(it) }
                emit(ResultState.Success(OverViewAction.UpdateSchedules(currentDate, previewSchedules)))
            }
        )
    }
}