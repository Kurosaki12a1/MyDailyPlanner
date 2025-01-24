package com.kuro.mdp.features.overview.data.repository.common

import com.kuro.mdp.features.overview.domain.model.schedules.TimeTaskOverView
import com.kuro.mdp.features.overview.domain.repository.common.TimeTaskStatusController
import com.kuro.mdp.shared.domain.common.TimeTaskStatusChecker
import com.kuro.mdp.shared.domain.model.schedules.TimeTaskStatus
import com.kuro.mdp.shared.utils.managers.DateManager

/**
 * Created by: minhthinh.h on 12/24/2024
 *
 * Description:
 */
class TimeTaskStatusControllerImpl(
    private val statusManager: TimeTaskStatusChecker,
    private val dateManager: DateManager
) : TimeTaskStatusController {
    override fun updateStatus(timeTask: TimeTaskOverView): TimeTaskOverView = with(timeTask) {
        val currentTime = dateManager.fetchCurrentDate()
        when (val status = statusManager.fetchStatus(timeToTimeRange(), currentTime)) {
            TimeTaskStatus.COMPLETED -> copy(
                executionStatus = status,
                progress = 1f,
                leftTime = 0,
                isCompleted = !(executionStatus == TimeTaskStatus.COMPLETED && !isCompleted),
            )

            TimeTaskStatus.PLANNED -> copy(
                executionStatus = status,
                progress = 0f,
                leftTime = -1,
                isCompleted = true,
            )

            TimeTaskStatus.RUNNING -> copy(
                executionStatus = status,
                progress = dateManager.calculateProgress(startTime, endTime),
                leftTime = dateManager.calculateLeftTime(endTime),
                isCompleted = true,
            )
        }
    }
}