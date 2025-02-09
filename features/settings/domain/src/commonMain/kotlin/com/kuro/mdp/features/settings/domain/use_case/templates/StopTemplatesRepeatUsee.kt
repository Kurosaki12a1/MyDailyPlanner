package com.kuro.mdp.features.settings.domain.use_case.templates

import com.kuro.mdp.features.settings.domain.mapper.templates.mapToDomain
import com.kuro.mdp.features.settings.domain.model.template.TemplateUi
import com.kuro.mdp.features.settings.domain.repository.SettingsRepeatTaskRepository
import com.kuro.mdp.features.settings.domain.repository.SettingsTemplatesRepository
import com.kuro.mdp.shared.domain.model.template.RepeatTime
import com.kuro.mdp.shared.presentation.notifications.TemplatesAlarmManager
import com.kuro.mdp.shared.utils.functional.ResultState
import com.kuro.mdp.shared.utils.functional.handle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by: minhthinh.h on 2/6/2025
 *
 * Description:
 */
class StopTemplatesRepeatUseCase(
    private val templatesRepository: SettingsTemplatesRepository,
    private val repeatTaskRepository: SettingsRepeatTaskRepository,
    private val templatesAlarmManager: TemplatesAlarmManager
) {
    operator fun invoke(template: TemplateUi): Flow<ResultState<Unit>> = flow {
        val newTemplate = template.copy(repeatEnabled = false)
        templatesRepository.updateTemplate(newTemplate.mapToDomain()).handle(
            onFailure = { emit(ResultState.Failure(it)) },
            onSuccess = {
                repeatTaskRepository.deleteRepeatsTemplates(template.mapToDomain(), template.repeatTimes).handle(
                    onFailure = { emit(ResultState.Failure(it)) },
                    onSuccess = { deleteNotifications(template, template.repeatTimes) }
                )
            }
        )
    }

    private fun deleteNotifications(template: TemplateUi, repeatTimes: List<RepeatTime>) {
        repeatTimes.forEach { repeatTime ->
            templatesAlarmManager.deleteNotifyAlarm(template.mapToDomain(), repeatTime)
        }
    }
}