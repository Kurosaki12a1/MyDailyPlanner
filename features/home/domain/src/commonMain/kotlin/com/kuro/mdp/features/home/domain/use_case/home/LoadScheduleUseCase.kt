package com.kuro.mdp.features.home.domain.use_case.home

import com.kuro.mdp.features.home.domain.api.ScheduleHomeToUiMapper
import com.kuro.mdp.features.home.domain.mapper.schedules.mapToDomain
import com.kuro.mdp.features.home.domain.model.schedules.ScheduleHome
import com.kuro.mdp.features.home.domain.repository.common.TimeTaskStatusController
import com.kuro.mdp.features.home.domain.repository.home.HomeScheduleRepository
import com.kuro.mdp.shared.domain.model.schedules.DailyScheduleStatus
import com.kuro.mdp.shared.domain.model.schedules.TimeTaskStatus
import com.kuro.mdp.shared.utils.extensions.toEpochMillis
import com.kuro.mdp.shared.utils.functional.Constants
import com.kuro.mdp.shared.utils.functional.ResultState
import com.kuro.mdp.shared.utils.functional.handle
import com.kuro.mdp.shared.utils.managers.DateManager
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.datetime.LocalDateTime

/**
 * Created by: minhthinh.h on 1/13/2025
 *
 * Description:
 */
class LoadScheduleUseCase(
    private val scheduleRepository: HomeScheduleRepository,
    private val mapperToUi: ScheduleHomeToUiMapper,
    private val dateManager: DateManager,
    private val statusController: TimeTaskStatusController
) {

    operator fun invoke(date: LocalDateTime?): Flow<ResultState<ScheduleHome?>> = channelFlow {
        var cycleUpdateJob: Job? = null
        val sendDate = scheduleRepository.fetchFeatureScheduleDate()
        val scheduleDate = sendDate ?: date ?: dateManager.fetchBeginningCurrentDay()
        scheduleRepository.fetchScheduleByDate(scheduleDate.toEpochMillis()).collect { data ->
            cycleUpdateJob?.cancelAndJoin()
            data.handle(
                onFailure = { e ->
                    send(ResultState.Failure(e))
                },
                onSuccess = { scheduleModel ->
                    if (scheduleModel != null) {
                        val schedule = scheduleModel.map(mapperToUi)

                        send(ResultState.Success(schedule))

                        cycleUpdateJob = refreshScheduleState(schedule)
                            .onEach { send(it) }
                            .launchIn(this)
                            .apply { start() }
                    } else {
                        send(
                            ResultState.Success(
                                ScheduleHome(
                                    date = scheduleDate, timeTasks = emptyList(), progress = -1f, dateStatus = DailyScheduleStatus.PLANNED
                                )
                            )
                        )
                    }
                }
            )
        }
    }

    private fun refreshScheduleState(schedule: ScheduleHome) = flow {
        var oldTimeTasks = schedule.timeTasks
        var isWorking = true
        while (isWorking) {
            val newTimeTasks = oldTimeTasks.map { statusController.updateStatus(it) }
            if (newTimeTasks != oldTimeTasks || schedule.timeTasks == oldTimeTasks) {
                val completedChange = oldTimeTasks.map { it.isCompleted } != newTimeTasks.map { it.isCompleted }
                oldTimeTasks = newTimeTasks
                val newSchedule = schedule.copy(timeTasks = oldTimeTasks)
                emit(ResultState.Success(newSchedule))
                if (completedChange) scheduleRepository.updateSchedule(newSchedule.mapToDomain())
            }
            isWorking = oldTimeTasks.find { it.executionStatus != TimeTaskStatus.COMPLETED } != null
            delay(Constants.Delay.CHECK_STATUS)
        }
    }
}