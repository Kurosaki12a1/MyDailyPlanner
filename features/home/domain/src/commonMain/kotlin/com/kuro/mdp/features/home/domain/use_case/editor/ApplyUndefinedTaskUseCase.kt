package com.kuro.mdp.features.home.domain.use_case.editor

import com.kuro.mdp.features.home.domain.mapper.schedules.convertToEditModel
import com.kuro.mdp.features.home.domain.model.actions.EditorAction
import com.kuro.mdp.features.home.domain.model.editor.EditModelHome
import com.kuro.mdp.features.home.domain.model.schedules.UndefinedTaskHome
import com.kuro.mdp.shared.utils.extensions.getLocalDateTimeNow
import com.kuro.mdp.shared.utils.functional.ResultState

/**
 * Created by: minhthinh.h on 2/19/2025
 *
 * Description:
 */
class ApplyUndefinedTaskUseCase {
    operator fun invoke(editModel: EditModelHome?, task: UndefinedTaskHome): ResultState<EditorAction> {
        if (editModel != null) {
            val domainModel = task.convertToEditModel(
                scheduleDate = editModel.date,
                timeRange = editModel.timeRange
            ).copy(
                key = editModel.key,
                createdAt = getLocalDateTimeNow()
            )
            return ResultState.Success(EditorAction.UpdateEditModel(domainModel))
        } else {
            return ResultState.Failure(Exception("Edit model is null!"))
        }
    }
}