package com.kuro.mdp.features.settings.data.repository

import com.kuro.mdp.features.settings.domain.repository.SettingsTemplatesRepository
import com.kuro.mdp.shared.domain.model.schedules.TimeTask
import com.kuro.mdp.shared.domain.model.template.Template
import com.kuro.mdp.shared.domain.repository.TemplatesRepository
import com.kuro.mdp.shared.utils.functional.ResultState
import com.kuro.mdp.shared.utils.functional.wrap
import com.kuro.mdp.shared.utils.functional.wrapFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

/**
 * Created by: minhthinh.h on 2/4/2025
 *
 * Description:
 */
class SettingsTemplatesRepositoryImpl(
    private val templateRepository: TemplatesRepository
) : SettingsTemplatesRepository {

    override suspend fun fetchTemplates(): Flow<ResultState<List<Template>>> = wrapFlow {
        templateRepository.fetchAllTemplates()
    }

    override suspend fun updateTemplate(template: Template): ResultState<Unit> = wrap {
        templateRepository.updateTemplate(template)
    }

    override suspend fun checkIsTemplate(timeTask: TimeTask): ResultState<Template?> = wrap {
        templateRepository.fetchAllTemplates().first().find { template ->
            template.equalsIsTemplate(timeTask)
        }
    }

    override suspend fun addTemplate(template: Template): ResultState<Int> = wrap {
        templateRepository.addTemplate(template)
    }

    override suspend fun deleteTemplate(id: Int): ResultState<Unit> = wrap {
        templateRepository.deleteTemplateById(id)
    }
}