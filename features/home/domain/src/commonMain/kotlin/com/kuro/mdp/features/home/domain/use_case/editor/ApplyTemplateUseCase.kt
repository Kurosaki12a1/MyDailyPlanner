package com.kuro.mdp.features.home.domain.use_case.editor

import com.kuro.mdp.features.home.domain.common.convertToEditModel
import com.kuro.mdp.features.home.domain.mapper.editor.mapToUi
import com.kuro.mdp.features.home.domain.mapper.templates.mapToDomain
import com.kuro.mdp.features.home.domain.model.actions.EditorAction
import com.kuro.mdp.features.home.domain.model.editor.EditModelHome
import com.kuro.mdp.features.home.domain.model.templates.TemplateHome
import com.kuro.mdp.shared.utils.functional.ResultState

/**
 * Created by: minhthinh.h on 2/19/2025
 *
 * Description:
 */
class ApplyTemplateUseCase {
    operator fun invoke(
        editModel: EditModelHome?,
        template: TemplateHome
    ): ResultState<EditorAction> {
        if (editModel != null) {
            val domainModel = template.mapToDomain().convertToEditModel(editModel.date).copy(key = editModel.key)
            return ResultState.Success(EditorAction.UpdateEditModel(domainModel.mapToUi()))
        } else {
            return ResultState.Failure(Exception("Edit model is null!"))
        }
    }
}