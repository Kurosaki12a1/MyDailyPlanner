package com.kuro.mdp.features.home.domain.use_case.editor

import com.kuro.mdp.features.home.domain.common.convertToTemplate
import com.kuro.mdp.features.home.domain.mapper.editor.mapToDomain
import com.kuro.mdp.features.home.domain.model.actions.EditorAction
import com.kuro.mdp.features.home.domain.model.editor.EditModelHome
import com.kuro.mdp.features.home.domain.repository.home.HomeTemplatesRepository
import com.kuro.mdp.shared.utils.functional.ResultState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by: minhthinh.h on 2/12/2025
 *
 * Description:
 */
class CreateTemplateUseCase(
    private val templatesRepository: HomeTemplatesRepository
) {

    operator fun invoke(editModelHome: EditModelHome?): Flow<ResultState<EditorAction>> = flow {
        val template = editModelHome?.mapToDomain()?.convertToTemplate()
        if (template != null) {
            when (val templateId = templatesRepository.addTemplate(template)) {
                is ResultState.Success -> emit(ResultState.Success(EditorAction.UpdateTemplateId(templateId = templateId.data)))
                else -> {}
            }
        }
    }
}