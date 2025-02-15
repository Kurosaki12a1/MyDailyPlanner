package com.kuro.mdp.features.home.presentation.ui.home.ui.editor.components

import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.kuro.mdp.features.home.domain.model.editor.TaskNotificationsHome
import com.kuro.mdp.features.home.presentation.ui.home.theme.HomeTheme
import com.kuro.mdp.shared.presentation.extensions.string
import com.kuro.mdp.shared.presentation.views.CheckedMenuItem

/**
 * Created by: minhthinh.h on 2/10/2025
 *
 * Description:
 */
@Composable
internal fun TaskNotificationsMenu(
    modifier: Modifier = Modifier,
    isExpanded: Boolean,
    taskNotification: TaskNotificationsHome,
    onDismiss: () -> Unit,
    onUpdate: (TaskNotificationsHome) -> Unit,
) {
    DropdownMenu(
        expanded = isExpanded,
        onDismissRequest = onDismiss,
        modifier = modifier.sizeIn(maxHeight = 200.dp),
        shape = MaterialTheme.shapes.large,
        offset = DpOffset(0.dp, 6.dp),
    ) {
        CheckedMenuItem(
            text = HomeTheme.strings.fifteenMinutesBeforeTitle.string(),
            check = taskNotification.fifteenMinutesBefore,
            onCheckedChange = { onUpdate(taskNotification.copy(fifteenMinutesBefore = it)) },
        )
        CheckedMenuItem(
            text = HomeTheme.strings.oneHourBeforeTitle.string(),
            check = taskNotification.oneHourBefore,
            onCheckedChange = { onUpdate(taskNotification.copy(oneHourBefore = it)) },
        )
        CheckedMenuItem(
            text = HomeTheme.strings.threeHourBeforeTitle.string(),
            check = taskNotification.threeHourBefore,
            onCheckedChange = { onUpdate(taskNotification.copy(threeHourBefore = it)) },
        )
        CheckedMenuItem(
            text = HomeTheme.strings.oneDayBeforeTitle.string(),
            check = taskNotification.oneDayBefore,
            onCheckedChange = { onUpdate(taskNotification.copy(oneDayBefore = it)) },
        )
        CheckedMenuItem(
            text = HomeTheme.strings.oneWeekBeforeTitle.string(),
            check = taskNotification.oneWeekBefore,
            onCheckedChange = { onUpdate(taskNotification.copy(oneWeekBefore = it)) },
        )
        CheckedMenuItem(
            text = HomeTheme.strings.beforeEndTitle.string(),
            check = taskNotification.beforeEnd,
            onCheckedChange = { onUpdate(taskNotification.copy(beforeEnd = it)) },
        )
    }
}