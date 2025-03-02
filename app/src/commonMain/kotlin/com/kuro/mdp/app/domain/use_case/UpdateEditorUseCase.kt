package com.kuro.mdp.app.domain.use_case

import com.kuro.mdp.app.domain.models.MainAction
import com.kuro.mdp.features.home.domain.common.convertToEditModel
import com.kuro.mdp.shared.domain.model.schedules.TimeTask
import com.kuro.mdp.shared.domain.model.template.Template
import com.kuro.mdp.shared.domain.repository.feature.FeatureEditorRepository
import com.kuro.mdp.shared.utils.functional.ResultState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by: minhthinh.h on 2/7/2025
 *
 * Description:
 */
class UpdateEditorUseCase(
    private val featureEditorRepository: FeatureEditorRepository,
) {
    operator fun invoke(
        timeTask: TimeTask,
        template: Template?,
        undefinedTaskId: Long? = null
    ): Flow<ResultState<MainAction>> = flow {
        val editor = timeTask.convertToEditModel(template, undefinedTaskId)
        featureEditorRepository.saveEditor(editor)
        emit(ResultState.Success(MainAction.UpdateEditor))
    }
}