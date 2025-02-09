package com.kuro.mdp.features.settings.domain.use_case.templates

import com.kuro.mdp.features.settings.domain.mapper.templates.mapToDomain
import com.kuro.mdp.features.settings.domain.model.template.TemplateUi
import com.kuro.mdp.features.settings.domain.repository.SettingsTemplatesRepository
import com.kuro.mdp.shared.utils.functional.ResultState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by: minhthinh.h on 2/4/2025
 *
 * Description:
 */
class AddTemplatesUseCase(
    private val templatesRepository: SettingsTemplatesRepository
) {
    operator fun invoke(template: TemplateUi): Flow<ResultState<Unit>> = flow {
        templatesRepository.addTemplate(template.mapToDomain()).let {
            if (it is ResultState.Failure) emit(ResultState.Failure(it.exception))
            else emit(ResultState.Success(Unit))
        }
    }
}