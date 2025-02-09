package com.kuro.mdp.features.settings.domain.repository

import com.kuro.mdp.shared.domain.model.schedules.TimeTask
import com.kuro.mdp.shared.domain.model.template.Template
import com.kuro.mdp.shared.utils.functional.ResultState
import kotlinx.coroutines.flow.Flow

/**
 * Created by: minhthinh.h on 2/4/2025
 *
 * Description:
 */
interface SettingsTemplatesRepository {
    suspend fun fetchTemplates(): Flow<ResultState<List<Template>>>
    suspend fun updateTemplate(template: Template): ResultState<Unit>
    suspend fun checkIsTemplate(timeTask: TimeTask): ResultState<Template?>
    suspend fun addTemplate(template: Template): ResultState<Int>
    suspend fun deleteTemplate(id: Int): ResultState<Unit>
}