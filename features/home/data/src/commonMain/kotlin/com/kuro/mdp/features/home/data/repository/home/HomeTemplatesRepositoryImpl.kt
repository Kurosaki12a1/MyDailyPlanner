package com.kuro.mdp.features.home.data.repository.home

import com.kuro.mdp.features.home.domain.repository.home.HomeTemplatesRepository
import com.kuro.mdp.shared.domain.model.template.Template
import com.kuro.mdp.shared.domain.repository.TemplatesRepository
import com.kuro.mdp.shared.utils.functional.wrap
import kotlinx.coroutines.flow.first

/**
 * Created by: minhthinh.h on 12/24/2024
 *
 * Description:
 */
class HomeTemplatesRepositoryImpl(
    private val templatesRepository: TemplatesRepository
) : HomeTemplatesRepository {
    override suspend fun fetchTemplates() = wrap {
        templatesRepository.fetchAllTemplates().first()
    }

    override suspend fun updateTemplate(template: Template) = wrap {
        templatesRepository.updateTemplate(template)
    }

    override suspend fun addTemplate(template: Template) = wrap {
        templatesRepository.addTemplate(template)
    }

    override suspend fun deleteTemplateById(id: Int) = wrap {
        templatesRepository.deleteTemplateById(id)
    }
}