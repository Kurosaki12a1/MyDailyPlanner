package com.kuro.mdp.features.home.presentation.ui.home.ui.editor

import androidx.compose.runtime.Composable
import com.kuro.mdp.shared.presentation.extensions.string
import com.kuro.mdp.shared.presentation.theme.AppTheme
import com.kuro.mdp.shared.presentation.views.SegmentedButtonItem

/**
 * Created by: minhthinh.h on 2/10/2025
 *
 * Description:
 */
internal enum class PriorityParameters : SegmentedButtonItem {
    STANDARD {
        override val title: String @Composable get() = AppTheme.strings.priorityStandard.string()
    },
    MEDIUM {
        override val title: String @Composable get() = AppTheme.strings.priorityMedium.string()
    },
    MAX {
        override val title: String @Composable get() = AppTheme.strings.priorityMax.string()
    },
}