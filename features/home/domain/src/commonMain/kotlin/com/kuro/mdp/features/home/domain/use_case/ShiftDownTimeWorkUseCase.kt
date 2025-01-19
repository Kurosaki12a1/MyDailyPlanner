package com.kuro.mdp.features.home.domain.use_case

import com.kuro.mdp.features.home.domain.mapper.schedules.mapToDomain
import com.kuro.mdp.features.home.domain.model.schedules.TimeTaskHome
import com.kuro.mdp.features.home.domain.repository.home.HomeTimeShiftRepository
import com.kuro.mdp.shared.domain.model.schedules.TimeTask
import com.kuro.mdp.shared.presentation.notifications.TimeTaskAlarmManager
import com.kuro.mdp.shared.utils.functional.Constants
import com.kuro.mdp.shared.utils.functional.ResultState
import com.kuro.mdp.shared.utils.functional.handle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by: minhthinh.h on 1/14/2025
 *
 * Description:
 */
class ShiftDownTimeWorkUseCase(
    private val timeShiftRepository: HomeTimeShiftRepository,
    private val timeTaskAlarmManager: TimeTaskAlarmManager
) {

    operator fun invoke(timeTask: TimeTaskHome): Flow<ResultState<List<TimeTask>>> = flow {
        timeShiftRepository.shiftDownTimeTask(timeTask.mapToDomain(), Constants.Date.SHIFT_MINUTE_VALUE).handle(
            onFailure = { emit(ResultState.Failure(it)) },
            onSuccess = { notifyUpdate(it) }
        )
    }

    private fun notifyUpdate(timeTask: TimeTask) {
        if (timeTask.isEnableNotification) {
            timeTaskAlarmManager.deleteNotifyAlarm(timeTask)
            timeTaskAlarmManager.addOrUpdateNotifyAlarm(timeTask)
        }
    }
}