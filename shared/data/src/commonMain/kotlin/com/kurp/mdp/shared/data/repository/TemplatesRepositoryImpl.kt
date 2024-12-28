package com.kurp.mdp.shared.data.repository

import com.kuro.mdp.shared.domain.model.template.Template
import com.kuro.mdp.shared.domain.repository.TemplatesRepository
import com.kurp.mdp.shared.data.data_sources.api.templates.TemplatesLocalDataSource
import com.kurp.mdp.shared.data.mappers.template.mapToData
import com.kurp.mdp.shared.data.mappers.template.mapToDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Created by: minhthinh.h on 12/23/2024
 *
 * Description:
 */
class TemplatesRepositoryImpl(
    private val localDataSource: TemplatesLocalDataSource
) : TemplatesRepository {
    override suspend fun addTemplate(template: Template): Int {
        return localDataSource.createTemplates(listOf(template.mapToData()))[0].toInt()
    }

    override suspend fun addTemplates(templates: List<Template>) {
        localDataSource.createTemplates(templates.map { it.mapToData() })
    }

    override suspend fun fetchTemplatesById(templateId: Int): Template? {
        return localDataSource.fetchTemplatesById(templateId)?.mapToDomain()
    }

    override fun fetchAllTemplates(): Flow<List<Template>> {
        return localDataSource.fetchAllTemplates().map { templates ->
            templates.map { template -> template.mapToDomain() }
        }
    }

    override suspend fun updateTemplate(template: Template) {
        localDataSource.updateTemplate(template.mapToData())
    }

    override suspend fun deleteTemplateById(id: Int) {
        localDataSource.deleteTemplateById(id)
    }

    override suspend fun deleteAllTemplates(): List<Template> {
        return localDataSource.deleteAllTemplates().map { it.mapToDomain() }
    }
}