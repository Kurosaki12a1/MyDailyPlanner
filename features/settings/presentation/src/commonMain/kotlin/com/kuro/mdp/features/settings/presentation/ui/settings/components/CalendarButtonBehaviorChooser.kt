package com.kuro.mdp.features.settings.presentation.ui.settings.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kuro.mdp.features.settings.presentation.theme.SettingsTheme
import com.kuro.mdp.shared.domain.model.settings.CalendarButtonBehavior
import com.kuro.mdp.shared.presentation.extensions.string
import com.kuro.mdp.shared.presentation.views.SegmentedButtonItem
import com.kuro.mdp.shared.presentation.views.SegmentedButtons

/**
 * Created by: minhthinh.h on 12/26/2024
 *
 * Description:
 */
@Composable
internal fun CalendarButtonBehaviorChooser(
    modifier: Modifier = Modifier,
    calendarButtonBehavior: CalendarButtonBehavior,
    onUpdateCalendarBehavior: (CalendarButtonBehavior) -> Unit,
) {
    Surface(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.surfaceContainer,
    ) {
        Column(
            modifier = Modifier.padding(vertical = 12.dp, horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Text(
                text = SettingsTheme.strings.calendarButtonBehaviorTitle.string(),
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.titleMedium,
            )
            SegmentedButtons(
                modifier = Modifier.fillMaxWidth(),
                items = CalendarButtonBehaviorSegmentedItems.entries.toTypedArray(),
                selectedItem = calendarButtonBehavior.toSegmentedItem(),
                onItemClick = { onUpdateCalendarBehavior.invoke(it.toCalendarButtonBehavior()) },
            )
        }
    }
}

internal enum class CalendarButtonBehaviorSegmentedItems : SegmentedButtonItem {
    OPEN_CALENDAR {
        override val title: String @Composable get() = SettingsTheme.strings.selectDayCalendarBehavior.string()
    },
    SET_CURRENT_DATE {
        override val title: String @Composable get() = SettingsTheme.strings.currentDayCalendarBehavior.string()
    },
}

internal fun CalendarButtonBehavior.toSegmentedItem() = when (this) {
    CalendarButtonBehavior.OPEN_CALENDAR -> CalendarButtonBehaviorSegmentedItems.OPEN_CALENDAR
    CalendarButtonBehavior.SET_CURRENT_DATE -> CalendarButtonBehaviorSegmentedItems.SET_CURRENT_DATE
}

internal fun CalendarButtonBehaviorSegmentedItems.toCalendarButtonBehavior() = when (this) {
    CalendarButtonBehaviorSegmentedItems.OPEN_CALENDAR -> CalendarButtonBehavior.OPEN_CALENDAR
    CalendarButtonBehaviorSegmentedItems.SET_CURRENT_DATE -> CalendarButtonBehavior.SET_CURRENT_DATE
}
