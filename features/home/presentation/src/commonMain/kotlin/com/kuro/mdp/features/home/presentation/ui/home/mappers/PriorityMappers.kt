package com.kuro.mdp.features.home.presentation.ui.home.mappers

import com.kuro.mdp.features.home.presentation.ui.home.ui.editor.PriorityParameters
import com.kuro.mdp.shared.domain.model.schedules.TaskPriority

/**
 * Created by: minhthinh.h on 2/10/2025
 *
 * Description:
 */
internal fun TaskPriority.convertToParameter() = when (this) {
    TaskPriority.STANDARD -> PriorityParameters.STANDARD
    TaskPriority.MEDIUM -> PriorityParameters.MEDIUM
    TaskPriority.MAX -> PriorityParameters.MAX
}

internal fun PriorityParameters.convertToModel() = when (this) {
    PriorityParameters.STANDARD -> TaskPriority.STANDARD
    PriorityParameters.MEDIUM -> TaskPriority.MEDIUM
    PriorityParameters.MAX -> TaskPriority.MAX
}