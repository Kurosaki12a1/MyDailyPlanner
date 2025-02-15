package com.kuro.mdp.shared.presentation.views

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AssistChip
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.kuro.mdp.shared.presentation.extensions.string
import com.kuro.mdp.shared.presentation.theme.AppTheme
import com.kuro.mdp.shared.utils.extensions.hoursToMillis
import com.kuro.mdp.shared.utils.extensions.minutesToMillis
import com.kuro.mdp.shared.utils.extensions.toHours
import com.kuro.mdp.shared.utils.extensions.toMinutesInHours
import com.kuro.mdp.shared.utils.extensions.toStringOrEmpty
import com.kuro.mdp.shared.utils.functional.Constants
import kotlinx.datetime.LocalDateTime

/**
 * Created by: minhthinh.h on 2/10/2025
 *
 * Description:
 */
@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun DurationPickerDialog(
    modifier: Modifier = Modifier,
    headerTitle: String,
    startTime: LocalDateTime,
    duration: Long,
    onDismissRequest: () -> Unit,
    onSelectedTime: (Long) -> Unit,
) {
    val maxHours = Constants.Date.HOURS_IN_DAY.toInt() - startTime.hour - 1
    val maxMinutes = Constants.Date.MINUTES_IN_HOUR.toInt() - startTime.minute - 1

    var hours by rememberSaveable { mutableStateOf<Int?>(duration.toHours().toInt()) }
    var minutes by rememberSaveable { mutableStateOf<Int?>(duration.toMinutesInHours().toInt()) }

    BasicAlertDialog(onDismissRequest = onDismissRequest) {
        Surface(
            modifier = modifier.width(243.dp),
            color = MaterialTheme.colorScheme.surfaceContainer,
            shape = MaterialTheme.shapes.extraLarge,
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(20.dp),
                horizontalAlignment = Alignment.End,
            ) {
                TimePickerHeader(title = headerTitle)
                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    DurationPickerHourMinuteSelector(
                        hours = hours.toStringOrEmpty(),
                        minutes = minutes.toStringOrEmpty(),
                        isEnableSupportText = true,
                        onMinutesChanges = { value ->
                            if (value.isEmpty()) {
                                hours = null
                            } else if (value.toIntOrNull() != null && value.length <= 2) {
                                hours = value.toIntOrNull()
                            }
                        },
                        onHoursChanges = { value ->
                            if (value.isEmpty()) {
                                minutes = null
                            } else if (value.toIntOrNull() != null && value.length <= 2) {
                                minutes = value.toIntOrNull()
                            }
                        },
                    )
                    LazyRow(
                        modifier = Modifier.height(32.dp),
                        contentPadding = PaddingValues(horizontal = 24.dp),
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                    ) {
                        items(DurationTemplate.entries.toTypedArray()) {
                            AssistChip(
                                onClick = {
                                    hours = it.hours
                                    minutes = it.minutes
                                },
                                label = {
                                    val millis =
                                        it.hours.hoursToMillis() + it.minutes.minutesToMillis()
                                    Text(text = millis.toMinutesOrHoursTitle())
                                },
                                border = BorderStroke(
                                    1.dp,
                                    color = MaterialTheme.colorScheme.outlineVariant,
                                ),
                            )
                        }
                    }
                }
                TimePickerActions(
                    enabledConfirm = hours != null && minutes != null && hours!! < 24,
                    onDismissClick = onDismissRequest,
                    onCurrentTimeChoose = {
                        hours = maxHours
                        minutes = maxMinutes
                    },
                    onConfirmClick = {
                        val hoursInMillis = checkNotNull(hours).hoursToMillis()
                        val time = hoursInMillis + checkNotNull(minutes).minutesToMillis()
                        onSelectedTime.invoke(time)
                    },
                )
            }
        }
    }
}

@Composable
internal fun DurationPickerHourMinuteSelector(
    modifier: Modifier = Modifier,
    hours: String,
    minutes: String,
    isEnableSupportText: Boolean = false,
    onMinutesChanges: (String) -> Unit,
    onHoursChanges: (String) -> Unit,
) = Row(
    modifier = modifier.padding(horizontal = 24.dp),
    verticalAlignment = Alignment.CenterVertically,
) {
    var isRequestedFirstFocus by rememberSaveable { mutableStateOf(false) }
    val hourRequester = remember { FocusRequester() }
    val minuteRequester = remember { FocusRequester() }

    OutlinedTextField(
        modifier = Modifier.weight(1f).focusRequester(hourRequester),
        value = hours,
        textStyle = MaterialTheme.typography.displayMedium.copy(textAlign = TextAlign.Center),
        onValueChange = { value ->
            onMinutesChanges(value)
            if (value.length == 2 && value.toIntOrNull() in 0..23) minuteRequester.requestFocus()
        },
        supportingText = if (isEnableSupportText) {
            {
                Text(AppTheme.strings.hoursTitle.string())
            }
        } else {
            null
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        shape = MaterialTheme.shapes.large,
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.primaryContainer,
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedContainerColor = MaterialTheme.colorScheme.surfaceContainerHighest,
            unfocusedBorderColor = MaterialTheme.colorScheme.surfaceContainerHighest,
        ),
    )
    Text(
        modifier = Modifier.width(24.dp),
        text = AppTheme.strings.separator.string(),
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.displayLarge,
        color = MaterialTheme.colorScheme.onSurface,
    )
    OutlinedTextField(
        modifier = Modifier.weight(1f).focusRequester(minuteRequester),
        value = minutes,
        textStyle = MaterialTheme.typography.displayMedium.copy(textAlign = TextAlign.Center),
        onValueChange = onHoursChanges,
        supportingText = if (isEnableSupportText) {
            {
                Text(AppTheme.strings.minutesTitle.string())
            }
        } else {
            null
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        singleLine = true,
        shape = MaterialTheme.shapes.large,
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.primaryContainer,
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedContainerColor = MaterialTheme.colorScheme.surfaceContainerHighest,
            unfocusedBorderColor = MaterialTheme.colorScheme.surfaceContainerHighest,
        ),
    )
    LaunchedEffect(Unit) {
        if (!isRequestedFirstFocus) {
            hourRequester.requestFocus()
            isRequestedFirstFocus = true
        }
    }
}
