package com.kuro.mdp.features.home.presentation.ui.home.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.kuro.mdp.features.home.presentation.ui.home.theme.HomeTheme
import com.kuro.mdp.shared.domain.model.settings.ViewToggleStatus
import com.kuro.mdp.shared.presentation.theme.AppTheme
import com.kuro.mdp.shared.presentation.views.ViewToggle
import com.kuro.mdp.shared.utils.extensions.mapToDate
import com.kuro.mdp.shared.utils.extensions.scrollText
import com.kuro.mdp.shared.utils.extensions.shiftDays
import com.kuro.mdp.shared.utils.extensions.startThisDay
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.format
import kotlinx.datetime.format.DayOfWeekNames
import kotlinx.datetime.format.MonthNames
import kotlinx.datetime.format.char
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

/**
 * Created by: minhthinh.h on 12/19/2024
 *
 * Description:
 */
@Composable
internal fun DateChooserSection(
    modifier: Modifier = Modifier,
    visible: Boolean = true,
    currentDate: LocalDateTime?,
    toggleState: ViewToggleStatus,
    onChangeDate: (LocalDateTime) -> Unit,
    onChangeToggleStatus: (ViewToggleStatus) -> Unit,
) {
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn() + slideInVertically(),
        exit = fadeOut(),
    ) {
        Row(
            modifier = modifier.padding(start = 16.dp, end = 8.dp, top = 8.dp, bottom = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(24.dp),
        ) {
            HomeDateChooser(
                modifier = Modifier.width(202.dp),
                currentDate = currentDate,
                onChangeDate = onChangeDate,
            )
            Spacer(modifier = Modifier.weight(1f))
            ViewToggle(
                status = toggleState,
                onStatusChange = onChangeToggleStatus,
            )
        }
    }
}


@Composable
internal fun HomeDateChooser(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    currentDate: LocalDateTime?,
    onChangeDate: (LocalDateTime) -> Unit,
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

    val isDateDialogShow = rememberSaveable { mutableStateOf(false) }

    DateChooser(
        modifier = modifier,
        enabled = enabled,
        dateTitle = currentDate?.format(dateFormat) ?: "",
        onNext = { currentDate?.let { onChangeDate.invoke(it.shiftDays(amount = 1)) } },
        onPrevious = { currentDate?.let { onChangeDate.invoke(it.shiftDays(amount = -1)) } },
        onChooseDate = { isDateDialogShow.value = true },
    )

    HomeDatePicker(
        isOpenDialog = isDateDialogShow.value,
        onDismiss = { isDateDialogShow.value = false },
        onSelectedDate = {
            isDateDialogShow.value = false
            onChangeDate.invoke(it)
        },
    )
}

@Composable
internal fun DateChooser(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    dateTitle: String,
    onNext: () -> Unit,
    onPrevious: () -> Unit,
    onChooseDate: () -> Unit,
) {
    Surface(
        modifier = modifier.height(36.dp),
        shape = MaterialTheme.shapes.large,
        color = MaterialTheme.colorScheme.surfaceContainerLow,
    ) {
        Row(modifier = Modifier.fillMaxHeight(), verticalAlignment = Alignment.CenterVertically) {
            DateChooserIcon(
                enabled = enabled,
                icon = painterResource(HomeTheme.icons.previousDate),
                description = HomeTheme.strings.previousDateIconDesc,
                onClick = onPrevious,
            )
            DateChooserContent(
                modifier = Modifier.scrollText().weight(1f),
                enabled = enabled,
                dateTitle = dateTitle,
                onClick = onChooseDate,
            )
            DateChooserIcon(
                enabled = enabled,
                icon = painterResource(HomeTheme.icons.nextDate),
                description = HomeTheme.strings.nextDateIconDesc,
                onClick = onNext,
            )
        }
    }
}

@Composable
internal fun DateChooserIcon(
    modifier: Modifier = Modifier,
    enabled: Boolean,
    icon: Painter,
    description: StringResource?,
    onClick: () -> Unit,
) {
    IconButton(
        onClick = onClick,
        modifier = modifier.size(36.dp),
        enabled = enabled,
    ) {
        Icon(
            modifier = Modifier.size(12.dp).graphicsLayer(alpha = if (enabled) 1f else 0.5f),
            painter = icon,
            contentDescription = if (description != null) stringResource(description) else null,
            tint = MaterialTheme.colorScheme.onSurface,
        )
    }
}

@Composable
internal fun DateChooserContent(
    modifier: Modifier = Modifier,
    enabled: Boolean,
    dateTitle: String,
    onClick: () -> Unit,
) {
    Box(
        modifier = modifier.fillMaxHeight().clip(MaterialTheme.shapes.medium).clickable(
            enabled = enabled,
            onClick = onClick,
        ),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 12.dp).graphicsLayer(
                alpha = if (enabled) 1f else 0.5f,
            ),
            text = dateTitle,
            textAlign = TextAlign.Center,
            maxLines = 1,
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.titleMedium,
        )
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
internal fun HomeDatePicker(
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
                        onSelectedDate.invoke(date.startThisDay())
                    },
                    enabled = confirmEnabled,
                ) {
                    Text(text = stringResource(AppTheme.strings.confirmTitle))
                }
            },
            dismissButton = {
                TextButton(onClick = onDismiss) {
                    Text(text = stringResource(AppTheme.strings.cancelTitle))
                }
            },
        ) {
            DatePicker(
                state = datePickerState,
                title = {
                    Text(
                        modifier = Modifier.padding(start = 24.dp, top = 24.dp),
                        text = stringResource(HomeTheme.strings.dateDialogPickerTitle)
                    )
                },
                headline = {
                    Text(
                        modifier = Modifier.padding(start = 24.dp),
                        text = stringResource(HomeTheme.strings.dateDialogPickerHeadline)
                    )
                },
            )
        }
    }
}
