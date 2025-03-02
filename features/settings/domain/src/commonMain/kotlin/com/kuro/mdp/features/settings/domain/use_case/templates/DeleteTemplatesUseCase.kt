package com.kuro.mdp.features.settings.domain.use_case.templates

import com.kuro.mdp.features.settings.domain.model.actions.TemplatesAction
import com.kuro.mdp.features.settings.domain.repository.SettingsTemplatesRepository
import com.kuro.mdp.shared.utils.functional.ResultState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by: minhthinh.h on 2/6/2025
 *
 * Description:
 */
class DeleteTemplatesUseCase(
    private val templatesRepository: SettingsTemplatesRepository
) {

    operator fun invoke(templateId: Int): Flow<ResultState<TemplatesAction>> = flow {
        templatesRepository.deleteTemplate(templateId).let {
            if (it is ResultState.Failure) emit(ResultState.Failure(it.exception))
            else if (it is ResultState.Success) emit(ResultState.Success(TemplatesAction.DeleteTemplates))
        }
    }
}