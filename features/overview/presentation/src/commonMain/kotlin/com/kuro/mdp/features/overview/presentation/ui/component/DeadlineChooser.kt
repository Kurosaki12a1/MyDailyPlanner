package com.kuro.mdp.features.overview.presentation.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kuro.mdp.features.overview.presentation.theme.OverViewTheme
import com.kuro.mdp.shared.presentation.extensions.scrollText
import com.kuro.mdp.shared.presentation.extensions.string
import com.kuro.mdp.shared.presentation.theme.AppTheme
import com.kuro.mdp.shared.utils.extensions.endThisDay
import com.kuro.mdp.shared.utils.extensions.mapToDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.format.DayOfWeekNames
import kotlinx.datetime.format.MonthNames
import kotlinx.datetime.format.char
import org.jetbrains.compose.resources.painterResource

/**
 * Created by: minhthinh.h on 1/21/2025
 *
 * Description:
 */
@Composable
internal fun DeadlineChooser(
    modifier: Modifier = Modifier,
    deadline: LocalDateTime?,
    onChooseDeadline: (LocalDateTime?) -> Unit,
) {
    var openDateChooserDialog by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Icon(
                painter = painterResource(OverViewTheme.icons.duration),
                contentDescription = AppTheme.strings.subCategoryLabel.string(),
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            Text(
                modifier = Modifier.weight(1f),
                text = OverViewTheme.strings.deadlineLabel.string(),
                maxLines = 1,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            if (deadline == null) {
                SuggestionChip(
                    label = { Text(text = OverViewTheme.strings.specifyDeadlineTitle.string()) },
                    onClick = { openDateChooserDialog = true },
                )
            }
        }
        if (deadline != null) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                DeadlineDateView(
                    deadline = deadline,
                    onClick = { openDateChooserDialog = true },
                )
                IconButton(modifier = Modifier.size(32.dp), onClick = { onChooseDeadline(null) }) {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                    )
                }
            }
        }
    }


    DeadlineDatePicker(
        isOpenDialog = openDateChooserDialog,
        onDismiss = { openDateChooserDialog = false },
        onSelectedDate = {
            onChooseDeadline(it)
            openDateChooserDialog = false
        },
    )
}

@Composable
internal fun DeadlineDateView(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    deadline: LocalDateTime,
    onClick: () -> Unit,
) {
    val listDayOfWeekName = AppTheme.dayOfWeeks
    val listOfMonthName = AppTheme.months
    val dateFormat = LocalDateTime.Format {
        dayOfWeek(DayOfWeekNames(listDayOfWeekName))
        chars(", ")
        dayOfMonth()
        char(' ')
        monthName(MonthNames(listOfMonthName))
    }

    Surface(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        shape = MaterialTheme.shapes.small,
        color = MaterialTheme.colorScheme.primaryContainer,
    ) {
        Text(
            modifier = Modifier.scrollText().padding(horizontal = 8.dp, vertical = 4.dp),
            text = dateFormat.format(deadline),
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.titleMedium,
        )
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
internal fun DeadlineDatePicker(
    modifier: Modifier = Modifier,
    isOpenDialog: Boolean,
    onDismiss: () -> Unit,
    onSelectedDate: (LocalDateTime) -> Unit,
) {
    if (isOpenDialog) {
        val datePickerState = rememberDatePickerState()
        val confirmEnabled by remember {
            derivedStateOf { datePickerState.selectedDateMillis != null }
        }
        DatePickerDialog(
            modifier = modifier,
            onDismissRequest = onDismiss,
            confirmButton = {
                TextButton(
                    onClick = {
                        val dateMillis = datePickerState.selectedDateMillis
                        val date = dateMillis?.mapToDate() ?: return@TextButton
                        onSelectedDate.invoke(date.endThisDay())
                    },
                    enabled = confirmEnabled,
                ) {
                    Text(text = AppTheme.strings.confirmTitle.string())
                }
            },
            dismissButton = {
                TextButton(onClick = onDismiss) {
                    Text(text = AppTheme.strings.cancelTitle.string())
                }
            },
        ) {
            DatePicker(
                state = datePickerState,
                title = {
                    Text(
                        modifier = Modifier.padding(start = 24.dp, top = 24.dp),
                        text = OverViewTheme.strings.dateDialogPickerTitle.string(),
                    )
                },
                headline = {
                    Text(
                        modifier = Modifier.padding(start = 24.dp),
                        text = OverViewTheme.strings.dateDialogPickerHeadline.string(),
                    )
                },
            )
        }
    }
}
