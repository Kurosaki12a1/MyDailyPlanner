package com.kuro.mdp.features.home.domain.use_case

import com.kuro.mdp.features.home.domain.repository.home.HomeScheduleRepository
import com.kuro.mdp.shared.utils.extensions.toEpochMillis
import com.kuro.mdp.shared.utils.functional.ResultState
import com.kuro.mdp.shared.utils.functional.handle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.datetime.LocalDateTime

/**
 * Created by: minhthinh.h on 1/14/2025
 *
 * Description:
 */
class ChangeTaskDoneStateWorkUseCase(
    private val scheduleRepository: HomeScheduleRepository
) {

    operator fun invoke(date: LocalDateTime?, key: Long): Flow<ResultState<Unit>> = flow {
        if (date != null) {
            scheduleRepository.fetchScheduleByDate(date.toEpochMillis()).firstOrNull()?.handle(
                onFailure = { e -> emit(ResultState.Failure(e)) },
                onSuccess = { schedule ->
                    schedule?.let {
                        val timeTask = schedule.timeTasks.toMutableList().apply {
                            val oldTimeTaskIndex = indexOfFirst { it.key == key }
                            val oldTimeTask = get(oldTimeTaskIndex)
                            val newTimeTask = oldTimeTask.copy(isCompleted = !oldTimeTask.isCompleted)
                            set(oldTimeTaskIndex, newTimeTask)
                        }
                        val newSchedule = schedule.copy(timeTasks = timeTask)
                        scheduleRepository.updateSchedule(newSchedule).handle(
                            onFailure = {
                                emit(ResultState.Failure(it))
                            }
                        )
                    }
                }
            )
        } else {
            emit(ResultState.Failure(Exception("Date is null!")))
        }
    }
}