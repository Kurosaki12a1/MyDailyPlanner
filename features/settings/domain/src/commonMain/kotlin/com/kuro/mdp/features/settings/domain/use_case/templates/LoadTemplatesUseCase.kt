package com.kuro.mdp.features.settings.domain.use_case.templates

import com.kuro.mdp.features.settings.domain.mapper.templates.mapToUi
import com.kuro.mdp.features.settings.domain.model.actions.TemplatesAction
import com.kuro.mdp.features.settings.domain.model.template.TemplatesSortedType
import com.kuro.mdp.features.settings.domain.repository.SettingsTemplatesRepository
import com.kuro.mdp.shared.utils.extensions.duration
import com.kuro.mdp.shared.utils.functional.ResultState
import com.kuro.mdp.shared.utils.functional.collectAndHandle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by: minhthinh.h on 2/4/2025
 *
 * Description:
 */
class LoadTemplatesUseCase(
    private val templatesRepository: SettingsTemplatesRepository
) {

    operator fun invoke(sortedType: TemplatesSortedType): Flow<ResultState<TemplatesAction>> = flow {
        templatesRepository.fetchTemplates().collectAndHandle(
            onFailure = { emit(ResultState.Failure(it)) },
            onSuccess = { templates ->
                val sortedTemplates = when (sortedType) {
                    TemplatesSortedType.CATEGORIES -> {
                        templates.sortedBy { it.category.id }
                    }

                    TemplatesSortedType.DATE -> {
                        templates.sortedBy { it.startTime }
                    }

                    TemplatesSortedType.DURATION -> {
                        templates.sortedBy { duration(it.startTime, it.endTime) }
                    }
                }
                emit(ResultState.Success(TemplatesAction.UpdateTemplates(sortedTemplates.map { it.mapToUi() })))
            }
        )
    }
}