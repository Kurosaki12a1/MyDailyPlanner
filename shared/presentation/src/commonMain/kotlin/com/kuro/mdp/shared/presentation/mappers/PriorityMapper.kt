package com.kuro.mdp.shared.presentation.mappers

import com.kuro.mdp.shared.domain.model.schedules.TaskPriority
import com.kuro.mdp.shared.presentation.views.MonogramPriority

/**
 * Created by: minhthinh.h on 12/19/2024
 *
 * Description:
 */
fun TaskPriority.mapToUi() = when (this) {
    TaskPriority.STANDARD -> MonogramPriority.STANDARD
    TaskPriority.MEDIUM -> MonogramPriority.MEDIUM
    TaskPriority.MAX -> MonogramPriority.MAX
}