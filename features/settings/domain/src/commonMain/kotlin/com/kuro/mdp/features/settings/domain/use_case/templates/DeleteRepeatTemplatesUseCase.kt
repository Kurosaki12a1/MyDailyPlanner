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
class DeleteRepeatTemplatesUseCase(
    private val templatesRepository: SettingsTemplatesRepository,
    private val repeatTaskRepository: SettingsRepeatTaskRepository,
    private val templatesAlarmManager: TemplatesAlarmManager
) {

    operator fun invoke(repeatTime: RepeatTime, template: TemplateUi): Flow<ResultState<Unit>> = flow {
        val newRepeatTimes = template.repeatTimes.toMutableList().apply { remove(repeatTime) }
        val newTemplate = template.copy(repeatTimes = newRepeatTimes)
        templatesRepository.updateTemplate(newTemplate.mapToDomain()).handle(
            onFailure = { emit(ResultState.Failure(it)) },
            onSuccess = {
                val repeat = listOf(repeatTime)
                if (template.repeatEnabled) {
                    repeatTaskRepository.deleteRepeatsTemplates(template.mapToDomain(), repeat).handle(
                        onFailure = { emit(ResultState.Failure(it)) },
                        onSuccess = { deleteNotifications(template, repeat) }
                    )
                }
            }
        )
    }

    private fun deleteNotifications(template: TemplateUi, repeatTimes: List<RepeatTime>) {
        repeatTimes.forEach { repeatTime ->
            templatesAlarmManager.deleteNotifyAlarm(template.mapToDomain(), repeatTime)
        }
    }
}