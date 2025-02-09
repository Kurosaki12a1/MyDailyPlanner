package com.kuro.mdp.features.settings.domain.use_case.templates

import com.kuro.mdp.features.settings.domain.mapper.templates.mapToDomain
import com.kuro.mdp.features.settings.domain.model.template.TemplateUi
import com.kuro.mdp.features.settings.domain.repository.SettingsRepeatTaskRepository
import com.kuro.mdp.features.settings.domain.repository.SettingsTemplatesRepository
import com.kuro.mdp.shared.domain.model.template.RepeatTime
import com.kuro.mdp.shared.presentation.notifications.TemplatesAlarmManager
import com.kuro.mdp.shared.presentation.notifications.TimeTaskAlarmManager
import com.kuro.mdp.shared.utils.functional.ResultState
import com.kuro.mdp.shared.utils.functional.handle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by: minhthinh.h on 2/6/2025
 *
 * Description:
 */
class RestartTemplatesRepeatUseCase(
    private val templatesRepository: SettingsTemplatesRepository,
    private val repeatTaskRepository: SettingsRepeatTaskRepository,
    private val templatesAlarmManager: TemplatesAlarmManager,
    private val timeTaskAlarmManager: TimeTaskAlarmManager
) {
    operator fun invoke(template: TemplateUi): Flow<ResultState<Unit>> = flow {
        val newTemplate = template.copy(repeatEnabled = true)
        templatesRepository.updateTemplate(newTemplate.mapToDomain()).handle(
            onFailure = { emit(ResultState.Failure(it)) },
            onSuccess = {
                repeatTaskRepository.deleteRepeatsTemplates(template.mapToDomain(), template.repeatTimes).handle(
                    onFailure = { emit(ResultState.Failure(it)) },
                    onSuccess = { oldTasks ->
                        oldTasks.forEach { timeTaskAlarmManager.deleteNotifyAlarm(it) }
                    }
                )
                repeatTaskRepository.addRepeatsTemplate(template.mapToDomain(), template.repeatTimes).handle(
                    onFailure = { emit(ResultState.Failure(it)) },
                    onSuccess = { addNotifications(template, template.repeatTimes) }
                )
            }
        )
    }

    private fun addNotifications(template: TemplateUi, repeatTimes: List<RepeatTime>) {
        if (template.isEnableNotification) {
            repeatTimes.forEach { repeatTime ->
                templatesAlarmManager.addOrUpdateNotifyAlarm(template.mapToDomain(), repeatTime)
            }
        }
    }
}