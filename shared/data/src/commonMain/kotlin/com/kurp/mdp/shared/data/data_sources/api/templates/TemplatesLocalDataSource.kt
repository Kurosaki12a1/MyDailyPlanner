package com.kurp.mdp.shared.data.data_sources.api.templates

import com.kurp.mdp.shared.data.entities.template.TemplateCompound
import com.kurp.mdp.shared.data.entities.template.TemplateDetails
import kotlinx.coroutines.flow.Flow

interface TemplatesLocalDataSource {
    suspend fun createTemplates(templates: List<TemplateCompound>): List<Long>
    suspend fun fetchTemplatesById(templateId: Int): TemplateDetails?
    fun fetchAllTemplates(): Flow<List<TemplateDetails>>
    suspend fun updateTemplate(template: TemplateCompound)
    suspend fun deleteTemplateById(id: Int)
    suspend fun deleteAllTemplates(): List<TemplateDetails>
}