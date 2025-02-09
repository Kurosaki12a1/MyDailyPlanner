package com.kuro.mdp.features.settings.domain.repository

import com.kuro.mdp.shared.domain.model.schedules.TimeTask
import com.kuro.mdp.shared.domain.model.template.RepeatTime
import com.kuro.mdp.shared.domain.model.template.Template
import com.kuro.mdp.shared.utils.functional.ResultState

/**
 * Created by: minhthinh.h on 2/6/2025
 *
 * Description:
 */
interface SettingsRepeatTaskRepository {

    suspend fun updateRepeatTemplate(oldTemplate: Template, template: Template): ResultState<List<TimeTask>>
    suspend fun addRepeatsTemplate(template: Template, repeatTimes: List<RepeatTime>): ResultState<List<TimeTask>>
    suspend fun deleteRepeatsTemplates(template: Template, repeatTimes: List<RepeatTime>): ResultState<List<TimeTask>>
}