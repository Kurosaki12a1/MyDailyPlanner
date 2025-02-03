package com.kuro.mdp.features.settings.presentation.ui.template.components

import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.kuro.mdp.features.settings.presentation.theme.SettingsTheme
import com.kuro.mdp.shared.domain.model.template.RepeatTime
import com.kuro.mdp.shared.domain.model.template.RepeatTimeType
import com.kuro.mdp.shared.presentation.mappers.mapToString
import com.kuro.mdp.shared.presentation.theme.AppTheme
import com.kuro.mdp.shared.presentation.views.BackMenuItem
import com.kuro.mdp.shared.presentation.views.CheckedMenuItem
import com.kuro.mdp.shared.presentation.views.NavMenuItem
import com.kuro.mdp.shared.utils.extensions.string
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.Month

@Composable
internal fun RepeatTimeMenu(
    modifier: Modifier = Modifier,
    isExpanded: Boolean,
    selectedTimes: List<RepeatTime>,
    onDismiss: () -> Unit,
    onAddRepeat: (RepeatTime) -> Unit,
    onDeleteRepeat: (RepeatTime) -> Unit,
) {
    var repeatCategory by remember {
        mutableStateOf(if (selectedTimes.isEmpty()) null else selectedTimes.first().repeatType)
    }
    DropdownMenu(
        expanded = isExpanded,
        onDismissRequest = onDismiss,
        modifier = modifier.sizeIn(maxHeight = 200.dp),
        shape = MaterialTheme.shapes.large,
        offset = DpOffset(0.dp, 6.dp),
    ) {
        when (repeatCategory) {
            RepeatTimeType.WEEK_DAY -> WeekDayMenuItems(
                selectedTimes = selectedTimes,
                onBackClick = { repeatCategory = null },
                onAddRepeat = onAddRepeat,
                onDeleteRepeat = onDeleteRepeat,
            )

            RepeatTimeType.WEEK_DAY_IN_MONTH -> WeekDayInMonthMenuItems(
                selectedTimes = selectedTimes,
                onBackClick = { repeatCategory = null },
                onAddRepeat = onAddRepeat,
                onDeleteRepeat = onDeleteRepeat,
            )

            RepeatTimeType.MONTH_DAY -> MonthDayMenuItems(
                selectedTimes = selectedTimes,
                onBackClick = { repeatCategory = null },
                onAddRepeat = onAddRepeat,
                onDeleteRepeat = onDeleteRepeat,
            )

            RepeatTimeType.YEAR_DAY -> YearDayMenuItems(
                selectedTimes = selectedTimes,
                onBackClick = { repeatCategory = null },
                onAddRepeat = onAddRepeat,
                onDeleteRepeat = onDeleteRepeat,
            )

            null -> RepeatTimeType.entries.forEach { type ->
                NavMenuItem(text = type.mapToString().string(), onClick = { repeatCategory = type })
            }
        }
    }
}

@Composable
internal fun WeekDayMenuItems(
    selectedTimes: List<RepeatTime>,
    onBackClick: () -> Unit,
    onAddRepeat: (RepeatTime) -> Unit,
    onDeleteRepeat: (RepeatTime) -> Unit,
) {
    BackMenuItem(
        enabled = selectedTimes.isEmpty(),
        onClick = onBackClick,
        title = SettingsTheme.strings.navToBackTitle.string(),
    )
    DayOfWeek.entries.forEach { day ->
        CheckedMenuItem(
            text = day.mapToString().string(),
            check = selectedTimes.contains(RepeatTime.WeekDays(day)),
            onCheckedChange = {
                val repeat = RepeatTime.WeekDays(day)
                if (it) onAddRepeat(repeat) else onDeleteRepeat(repeat)
            },
        )
    }
}

@Composable
internal fun MonthDayMenuItems(
    selectedTimes: List<RepeatTime>,
    onBackClick: () -> Unit,
    onAddRepeat: (RepeatTime) -> Unit,
    onDeleteRepeat: (RepeatTime) -> Unit,
) {
    BackMenuItem(
        enabled = selectedTimes.isEmpty(),
        onClick = onBackClick,
        title = SettingsTheme.strings.navToBackTitle.string(),
    )
    for (dayNumber in 1..31) {
        CheckedMenuItem(
            text = dayNumber.toString(),
            check = selectedTimes.contains(RepeatTime.MonthDay(dayNumber)),
            onCheckedChange = {
                val repeat = RepeatTime.MonthDay(dayNumber)
                if (it) onAddRepeat(repeat) else onDeleteRepeat(repeat)
            },
        )
    }
}

@Composable
internal fun WeekDayInMonthMenuItems(
    selectedTimes: List<RepeatTime>,
    onBackClick: () -> Unit,
    onAddRepeat: (RepeatTime) -> Unit,
    onDeleteRepeat: (RepeatTime) -> Unit,
) {
    var selectedWeekNumber by remember { mutableStateOf<Int?>(null) }
    var isOpenSubMenu by remember { mutableStateOf(false) }

    if (isOpenSubMenu) {
        BackMenuItem(
            onClick = { isOpenSubMenu = false; selectedWeekNumber = null },
            title = SettingsTheme.strings.navToBackTitle.string(),
        )
        DayOfWeek.entries.forEach { day ->
            CheckedMenuItem(
                text = day.mapToString().string(),
                check = selectedTimes.contains(
                    RepeatTime.WeekDayInMonth(
                        day,
                        selectedWeekNumber!!
                    )
                ),
                onCheckedChange = {
                    val repeat = RepeatTime.WeekDayInMonth(day, selectedWeekNumber!!)
                    if (it) onAddRepeat(repeat) else onDeleteRepeat(repeat)
                },
            )
        }
    } else {
        BackMenuItem(
            enabled = selectedTimes.isEmpty(),
            onClick = onBackClick,
            title = SettingsTheme.strings.navToBackTitle.string(),
        )
        for (weekNumber in 1..5) {
            NavMenuItem(
                text = weekNumber.toString(),
                onClick = {
                    selectedWeekNumber = weekNumber
                    isOpenSubMenu = true
                },
            )
        }
    }
}

@Composable
internal fun YearDayMenuItems(
    selectedTimes: List<RepeatTime>,
    onBackClick: () -> Unit,
    onAddRepeat: (RepeatTime) -> Unit,
    onDeleteRepeat: (RepeatTime) -> Unit,
) {
    var selectedMonth by remember { mutableStateOf<Month?>(null) }
    var isOpenSubMenu by remember { mutableStateOf(false) }

    if (isOpenSubMenu) {
        BackMenuItem(
            onClick = { isOpenSubMenu = false; selectedMonth = null },
            title = SettingsTheme.strings.navToBackTitle.string(),
        )
        for (day in 1..31) {
            CheckedMenuItem(
                text = day.toString(),
                check = selectedTimes.contains(
                    RepeatTime.YearDay(
                        month = selectedMonth!!,
                        dayNumber = day
                    )
                ),
                onCheckedChange = {
                    val repeat = RepeatTime.YearDay(month = selectedMonth!!, dayNumber = day)
                    if (it) onAddRepeat(repeat) else onDeleteRepeat(repeat)
                },
            )
        }
    } else {
        BackMenuItem(
            enabled = selectedTimes.isEmpty(),
            onClick = onBackClick,
            title = SettingsTheme.strings.navToBackTitle.string(),
        )
        Month.entries.forEach { month ->
            NavMenuItem(
                text = month.mapToString(AppTheme.strings).string(),
                onClick = {
                    selectedMonth = month
                    isOpenSubMenu = true
                },
            )
        }
    }
}
