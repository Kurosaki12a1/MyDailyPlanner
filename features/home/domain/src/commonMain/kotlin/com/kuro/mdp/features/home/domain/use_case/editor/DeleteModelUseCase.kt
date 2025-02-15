package com.kuro.mdp.features.home.domain.use_case.editor

import com.kuro.mdp.features.home.domain.common.convertToTimeTask
import com.kuro.mdp.features.home.domain.mapper.editor.mapToDomain
import com.kuro.mdp.features.home.domain.model.editor.EditModelHome
import com.kuro.mdp.features.home.domain.repository.home.HomeTimeTaskRepository
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
class DeleteModelUseCase(
    private val timeTaskRepository: HomeTimeTaskRepository,
    private val timeTaskAlarmManager: TimeTaskAlarmManager,
    private val navigator: Navigator
) {
    operator fun invoke(editModel: EditModelHome?): Flow<ResultState<Unit>> = flow {
        if (editModel == null) return@flow
        val domainModel = editModel.mapToDomain()
        if (domainModel.key != 0L) {
            when (val deleteResult = timeTaskRepository.deleteTimeTask(domainModel.key)) {
                is ResultState.Failure -> emit(ResultState.Failure(deleteResult.exception))
                is ResultState.Success -> {
                    timeTaskAlarmManager.deleteNotifyAlarm(domainModel.convertToTimeTask())
                }

                else -> {}
            }
        }
        navigator.navigateBack(route = null, inclusive = false)
    }
}