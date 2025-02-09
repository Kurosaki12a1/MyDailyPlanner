package com.kuro.mdp.features.settings.domain.use_case.templates

import com.kuro.mdp.features.settings.domain.mapper.templates.mapToDomain
import com.kuro.mdp.features.settings.domain.model.template.TemplateUi
import com.kuro.mdp.features.settings.domain.repository.SettingsRepeatTaskRepository
import com.kuro.mdp.features.settings.domain.repository.SettingsTemplatesRepository
import com.kuro.mdp.shared.domain.model.template.Template
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
class UpdateTemplatesUseCase(
    private val templatesRepository: SettingsTemplatesRepository,
    private val repeatTaskRepository: SettingsRepeatTaskRepository,
    private val templatesAlarmManager: TemplatesAlarmManager
) {
    operator fun invoke(oldTemplate: TemplateUi, newTemplate: TemplateUi): Flow<ResultState<Unit>> = flow {
        val oldDomainModel = oldTemplate.mapToDomain()
        val newDomainModel = newTemplate.mapToDomain()
        templatesRepository.updateTemplate(newDomainModel).handle(
            onFailure = { emit(ResultState.Failure(it)) },
            onSuccess = {
                if (newTemplate.repeatEnabled) {
                    repeatTaskRepository.updateRepeatTemplate(oldDomainModel, newDomainModel).handle(
                        onFailure = { emit(ResultState.Failure(it)) },
                        onSuccess = { updateNotifications(oldDomainModel, newDomainModel) }
                    )
                }
            }
        )
    }

    private fun updateNotifications(oldTemplate: Template, newTemplate: Template) {
        newTemplate.repeatTimes.forEach { repeatTime ->
            templatesAlarmManager.deleteNotifyAlarm(oldTemplate, repeatTime)
            if (newTemplate.isEnableNotification) {
                templatesAlarmManager.addOrUpdateNotifyAlarm(newTemplate, repeatTime)
            }
        }
    }
}