package com.kuro.mdp.features.home.domain.use_case.editor

import com.kuro.mdp.features.home.domain.common.convertToTimeTask
import com.kuro.mdp.features.home.domain.mapper.editor.mapToDomain
import com.kuro.mdp.features.home.domain.model.editor.EditModelHome
import com.kuro.mdp.features.home.domain.model.editor.EditorAction
import com.kuro.mdp.features.home.domain.repository.common.CategoryValidator
import com.kuro.mdp.features.home.domain.repository.common.TimeRangeValidator
import com.kuro.mdp.features.home.domain.repository.home.HomeTimeTaskRepository
import com.kuro.mdp.features.home.domain.repository.home.HomeUndefinedTasksRepository
import com.kuro.mdp.shared.domain.model.schedules.TimeTask
import com.kuro.mdp.shared.presentation.navigation.navigator.Navigator
import com.kuro.mdp.shared.presentation.notifications.TimeTaskAlarmManager
import com.kuro.mdp.shared.utils.functional.ResultState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by: minhthinh.h on 2/13/2025
 *
 * Description:
 */
class SaveModelUseCase(
    private val timeRangeValidator: TimeRangeValidator,
    private val categoryValidator: CategoryValidator,
    private val timeTaskRepository: HomeTimeTaskRepository,
    private val undefinedTasksRepository: HomeUndefinedTasksRepository,
    private val timeTaskAlarmManager: TimeTaskAlarmManager,
    private val navigator: Navigator
) {
    operator fun invoke(editModelHome: EditModelHome?): Flow<ResultState<EditorAction>> = flow {
        if (editModelHome == null) return@flow
        with(editModelHome) {
            val timeValidate = timeRangeValidator.validate(timeRange)
            val categoryValidate = categoryValidator.validate(mainCategory)
            if (timeValidate.isValid && categoryValidate.isValid) {
                val domainModel = this.mapToDomain()
                val timeTask = domainModel.convertToTimeTask()
                val saveResult = when (timeTask.key != 0L) {
                    true -> timeTaskRepository.updateTimeTask(timeTask)
                    false -> timeTaskRepository.addTimeTask(timeTask)
                }
                if (!editModelHome.checkDateIsRepeat()) {
                    when (saveResult) {
                        is ResultState.Failure -> {
                            emit(ResultState.Failure(saveResult.exception))
                        }

                        is ResultState.Success -> {
                            notifyUpdateOrAdd(timeTask.copy(key = saveResult.data)).let {
                                if (editModelHome.undefinedTaskId != null) {
                                    undefinedTasksRepository.deleteUndefinedTask(editModelHome.undefinedTaskId)
                                }
                                navigator.navigateBack(route = null, inclusive = false)
                            }
                        }

                        else -> {}
                    }
                } else {
                    when (saveResult) {
                        is ResultState.Failure -> {
                            emit(ResultState.Failure(saveResult.exception))
                        }

                        is ResultState.Success -> {
                            navigator.navigateBack(route = null, inclusive = false)
                        }

                        else -> {}
                    }
                }
            } else {
                emit(ResultState.Success(EditorAction.SetValidError(timeValidate.validError, categoryValidate.validError)))
            }
        }
    }

    private fun notifyUpdateOrAdd(timeTask: TimeTask) = if (timeTask.isEnableNotification) {
        timeTaskAlarmManager.deleteNotifyAlarm(timeTask)
        timeTaskAlarmManager.addOrUpdateNotifyAlarm(timeTask)
    } else {
        timeTaskAlarmManager.deleteNotifyAlarm(timeTask)
    }
}