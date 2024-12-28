package com.kurp.mdp.shared.data.data_sources.impl.templates

import com.kurp.mdp.shared.data.data_sources.api.templates.TemplatesLocalDataSource
import com.kurp.mdp.shared.data.data_sources.dao.templates.TemplatesDao
import com.kurp.mdp.shared.data.entities.template.TemplateCompound
import com.kurp.mdp.shared.data.entities.template.TemplateDetails
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

class TemplatesLocalDataSourceImpl(
    private val templatesDao: TemplatesDao,
) : TemplatesLocalDataSource {

    override suspend fun createTemplates(templates: List<TemplateCompound>): List<Long> {
        return templatesDao.addOrUpdateCompoundTemplates(templates)
    }

    override fun fetchAllTemplates(): Flow<List<TemplateDetails>> {
        return templatesDao.fetchAllTemplates()
    }

    override suspend fun fetchTemplatesById(templateId: Int): TemplateDetails? {
        return templatesDao.fetchTemplateById(templateId)
    }

    override suspend fun updateTemplate(template: TemplateCompound) {
        templatesDao.addOrUpdateCompoundTemplates(listOf(template))
    }

    override suspend fun deleteTemplateById(id: Int) {
        templatesDao.deleteTemplate(id)
        templatesDao.deleteRepeatTimesByTemplates(listOf(id))
    }

    override suspend fun deleteAllTemplates(): List<TemplateDetails> {
        val deletableTemplates = fetchAllTemplates().first()
        templatesDao.deleteAllTemplates()
        templatesDao.deleteAllRepeatTimes()

        return deletableTemplates
    }
}