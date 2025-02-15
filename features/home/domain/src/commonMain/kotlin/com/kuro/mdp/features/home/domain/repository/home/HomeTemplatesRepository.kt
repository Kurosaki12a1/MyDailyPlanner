package com.kuro.mdp.features.home.domain.repository.home

import com.kuro.mdp.shared.domain.model.template.Template
import com.kuro.mdp.shared.utils.functional.ResultState

/**
 * Created by: minhthinh.h on 12/24/2024
 *
 * Description:
 */
interface HomeTemplatesRepository {
    suspend fun fetchTemplates(): ResultState<List<Template>>
    suspend fun updateTemplate(template: Template): ResultState<Unit>
    suspend fun addTemplate(template: Template): ResultState<Int>
    suspend fun deleteTemplateById(id: Int): ResultState<Unit>
}