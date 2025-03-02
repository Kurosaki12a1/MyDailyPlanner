package com.kuro.mdp.features.settings.domain.use_case.templates

import com.kuro.mdp.features.settings.domain.mapper.templates.mapToDomain
import com.kuro.mdp.features.settings.domain.model.actions.TemplatesAction
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
class AddRepeatTemplatesUseCase(
    private val templatesRepository: SettingsTemplatesRepository,
    private val repeatTimeTaskRepository: SettingsRepeatTaskRepository,
    private val templatesAlarmManager: TemplatesAlarmManager
) {

    operator fun invoke(repeatTime: RepeatTime, template: TemplateUi): Flow<ResultState<TemplatesAction>> = flow {
        val newRepeatTimes = template.repeatTimes.toMutableList().apply { add(repeatTime) }
        val newTemplate = template.copy(repeatTimes = newRepeatTimes)
        templatesRepository.updateTemplate(newTemplate.mapToDomain()).handle(
            onFailure = { emit(ResultState.Failure(it)) },
            onSuccess = {
                val repeat = listOf(repeatTime)
                if (template.repeatEnabled) {
                    repeatTimeTaskRepository.addRepeatsTemplate(template.mapToDomain(), repeat).handle(
                        onFailure = { emit(ResultState.Failure(it)) },
                        onSuccess = {
                            addNotifications(template, repeat)
                            emit(ResultState.Success(TemplatesAction.AddRepeatTemplates))
                        }
                    )
                }
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