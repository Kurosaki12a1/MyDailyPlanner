package com.kuro.mdp.features.home.data.repository.home

import com.kuro.mdp.features.home.domain.repository.home.HomeTemplatesRepository
import com.kuro.mdp.shared.domain.model.schedules.TimeTask
import com.kuro.mdp.shared.domain.model.template.Template
import com.kuro.mdp.shared.domain.repository.TemplatesRepository
import com.kuro.mdp.shared.utils.functional.ResultState
import com.kuro.mdp.shared.utils.functional.wrap
import com.kuro.mdp.shared.utils.functional.wrapFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

/**
 * Created by: minhthinh.h on 12/24/2024
 *
 * Description:
 */
class HomeTemplatesRepositoryImpl(
    private val templatesRepository: TemplatesRepository
) : HomeTemplatesRepository {
    override suspend fun fetchTemplates(): Flow<ResultState<List<Template>>> = wrapFlow {
        templatesRepository.fetchAllTemplates()
    }

    override suspend fun updateTemplate(template: Template): ResultState<Unit> = wrap {
        templatesRepository.updateTemplate(template)
    }

    override suspend fun checkIsTemplate(timeTask: TimeTask): ResultState<Template?> = wrap {
        templatesRepository.fetchAllTemplates().first().find { template ->
            template.equalsIsTemplate(timeTask)
        }
    }

    override suspend fun addTemplate(template: Template): ResultState<Int> = wrap {
        templatesRepository.addTemplate(template)
    }

    override suspend fun deleteTemplate(id: Int): ResultState<Unit> = wrap {
        templatesRepository.deleteTemplateById(id)
    }
}