package com.kuro.mdp.shared.domain.repository

import com.kuro.mdp.shared.domain.model.template.Template
import kotlinx.coroutines.flow.Flow

interface TemplatesRepository {
    suspend fun addTemplates(templates: List<Template>)
    suspend fun addTemplate(template: Template): Int
    suspend fun fetchTemplatesById(templateId: Int): Template?
    fun fetchAllTemplates(): Flow<List<Template>>
    suspend fun updateTemplate(template: Template)
    suspend fun deleteTemplateById(id: Int)
    suspend fun deleteAllTemplates(): List<Template>
}
