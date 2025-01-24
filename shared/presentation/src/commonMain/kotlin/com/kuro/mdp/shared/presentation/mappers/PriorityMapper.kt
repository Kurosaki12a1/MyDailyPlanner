package com.kuro.mdp.shared.presentation.mappers

import androidx.compose.runtime.Composable
import com.kuro.mdp.shared.domain.model.schedules.TaskPriority
import com.kuro.mdp.shared.presentation.theme.AppTheme
import com.kuro.mdp.shared.presentation.views.MonogramPriority
import com.kuro.mdp.shared.utils.extensions.string

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

@Composable
fun TaskPriority.mapToString(): String = when (this) {
    TaskPriority.STANDARD -> AppTheme.strings.priorityStandard.string()
    TaskPriority.MEDIUM -> AppTheme.strings.priorityMedium.string()
    TaskPriority.MAX -> AppTheme.strings.priorityMax.string()
}
