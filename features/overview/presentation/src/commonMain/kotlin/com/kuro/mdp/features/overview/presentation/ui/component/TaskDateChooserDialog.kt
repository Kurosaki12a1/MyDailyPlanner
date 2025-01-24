package com.kuro.mdp.features.overview.presentation.ui.component

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.kuro.mdp.features.overview.presentation.theme.OverViewTheme
import com.kuro.mdp.shared.presentation.theme.AppTheme
import com.kuro.mdp.shared.presentation.views.DialogButtons
import com.kuro.mdp.shared.presentation.views.ExpandedIcon
import com.kuro.mdp.shared.utils.extensions.getLocalDateTimeNow
import com.kuro.mdp.shared.utils.extensions.shiftDays
import com.kuro.mdp.shared.utils.extensions.string
import com.kuro.mdp.shared.utils.functional.Constants
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.format.DayOfWeekNames
import kotlinx.datetime.format.MonthNames
import kotlinx.datetime.format.char

/**
 * Created by: minhthinh.h on 1/21/2025
 *
 * Description:
 */
@Composable
@OptIn(ExperimentalMaterial3Api::class)
internal fun TaskDateChooserDialog(
    modifier: Modifier = Modifier,
    daysCount: Int = Constants.Date.DAYS_IN_WEEK * 2,
    onDismiss: () -> Unit,
    onConfirm: (LocalDateTime) -> Unit,
) {
    var selectedDate by remember { mutableStateOf<LocalDateTime?>(null) }
    val dateList = remember {
        mutableStateListOf<LocalDateTime>().apply {
            for (shiftAmount in 0..daysCount) add(getLocalDateTimeNow().shiftDays(shiftAmount))
        }
    }

    BasicAlertDialog(onDismissRequest = onDismiss) {
        Surface(
            modifier = modifier.width(290.dp).wrapContentHeight(),
            shape = MaterialTheme.shapes.extraLarge,
            color = MaterialTheme.colorScheme.surfaceContainer,
        ) {
            Column {
                TaskDateChooserDialogHeader()
                HorizontalDivider(Modifier.fillMaxWidth())
                Column(
                    modifier = Modifier.height(160.dp).padding(start = 16.dp, end = 16.dp, top = 16.dp),
                ) {
                    DayChooser(
                        days = dateList,
                        selected = selectedDate,
                        onSelected = { selectedDate = it },
                    )
                    DialogButtons(
                        enabledConfirm = selectedDate != null,
                        confirmTitle = AppTheme.strings.okConfirmTitle.string(),
                        onConfirmClick = { selectedDate?.let { onConfirm(it) } },
                        onCancelClick = onDismiss,
                    )
                }
            }
        }
    }
}

@Composable
internal fun TaskDateChooserDialogHeader(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.padding(top = 24.dp, bottom = 12.dp, start = 24.dp, end = 24.dp),
        verticalArrangement = Arrangement.spacedBy(2.dp),
    ) {
        Text(
            text = OverViewTheme.strings.taskDateChooserHeader.string(),
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.headlineSmall,
        )
    }
}

@Composable
internal fun DayChooser(
    modifier: Modifier = Modifier,
    days: List<LocalDateTime>,
    selected: LocalDateTime?,
    onSelected: (LocalDateTime?) -> Unit,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
) {
    val listDayOfWeekName = AppTheme.dayOfWeeks
    val listOfMonthName = AppTheme.months
    val dateFormat = LocalDateTime.Format {
        dayOfWeek(DayOfWeekNames(listDayOfWeekName))
        chars(", ")
        monthName(MonthNames(listOfMonthName))
        char(' ')
        dayOfMonth()
        chars(", ''")
        yearTwoDigits(2000)
    }
    val isPressed by interactionSource.collectIsPressedAsState()
    var isDayChooseMenuOpen by remember { mutableStateOf(false) }

    OutlinedTextField(
        modifier = modifier.fillMaxWidth(),
        value = selected?.let { dateFormat.format(it) } ?: "",
        onValueChange = {},
        readOnly = true,
        label = { Text(text = OverViewTheme.strings.taskDateChooserFieldLabel.string()) },
        trailingIcon = { ExpandedIcon(isExpanded = isDayChooseMenuOpen) },
        shape = MaterialTheme.shapes.large,
        interactionSource = interactionSource,
    )
    Box(contentAlignment = Alignment.TopEnd) {
        DayChooseMenu(
            isExpanded = isDayChooseMenuOpen,
            days = days,
            onDismiss = { isDayChooseMenuOpen = false },
            onChoose = { mainCategory ->
                isDayChooseMenuOpen = false
                onSelected(mainCategory)
            },
        )
    }
    LaunchedEffect(key1 = isPressed) {
        if (isPressed) {
            isDayChooseMenuOpen = !isDayChooseMenuOpen
        }
    }
}

@Composable
internal fun DayChooseMenu(
    modifier: Modifier = Modifier,
    isExpanded: Boolean,
    days: List<LocalDateTime>,
    onDismiss: () -> Unit,
    onChoose: (LocalDateTime?) -> Unit,
) {
    val listDayOfWeekName = AppTheme.dayOfWeeks
    val listOfMonthName = AppTheme.months
    val dateFormat = LocalDateTime.Format {
        dayOfWeek(DayOfWeekNames(listDayOfWeekName))
        chars(", ")
        monthName(MonthNames(listOfMonthName))
        char(' ')
        dayOfMonth()
        chars(", ''")
        yearTwoDigits(2000)
    }
    DropdownMenu(
        expanded = isExpanded,
        onDismissRequest = onDismiss,
        modifier = modifier.sizeIn(maxHeight = 200.dp),
        shape = MaterialTheme.shapes.large,
        offset = DpOffset(0.dp, 6.dp),
    ) {
        days.forEach { day ->
            DropdownMenuItem(
                onClick = { onChoose(day) },
                text = {
                    Text(
                        text = dateFormat.format(day),
                        color = MaterialTheme.colorScheme.onSurface,
                        style = MaterialTheme.typography.titleMedium,
                    )
                },
            )
        }
    }
}
