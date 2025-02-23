package com.kuro.mdp.features.home.presentation.ui.home.ui.editor.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.kuro.mdp.features.home.presentation.ui.home.theme.HomeTheme
import com.kuro.mdp.shared.presentation.extensions.string
import com.kuro.mdp.shared.presentation.views.DurationPickerDialog
import com.kuro.mdp.shared.presentation.views.MultiTimePickerDialog
import com.kuro.mdp.shared.presentation.views.toMinutesAndHoursTitle
import com.kuro.mdp.shared.utils.functional.getTimeFormatShort
import kotlinx.datetime.LocalDateTime
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

/**
 * Created by: minhthinh.h on 2/10/2025
 *
 * Description:
 */
@Composable
internal fun BaseTimeField(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    currentTime: LocalDateTime,
    isError: Boolean,
    label: String,
    leadingIcon: DrawableResource,
    color: Color = MaterialTheme.colorScheme.surfaceContainerLow,
    onChangeTime: (LocalDateTime) -> Unit,
) {
    val timeFormat = getTimeFormatShort()
    var openDialog by rememberSaveable { mutableStateOf(false) }

    Surface(
        enabled = enabled,
        onClick = { openDialog = true },
        modifier = modifier.height(56.dp).widthIn(min = 120.dp),
        shape = MaterialTheme.shapes.medium,
        color = color,
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Icon(
                modifier = Modifier.size(24.dp),
                painter = painterResource(leadingIcon),
                contentDescription = label,
                tint = when (isError) {
                    true -> MaterialTheme.colorScheme.error
                    false -> MaterialTheme.colorScheme.onSurfaceVariant
                },
            )
            Column {
                val textColor = when (isError) {
                    true -> MaterialTheme.colorScheme.error
                    false -> MaterialTheme.colorScheme.onSurface
                }
                Text(
                    text = label,
                    color = textColor,
                    style = MaterialTheme.typography.bodySmall,
                )
                Text(
                    text = timeFormat.format(currentTime),
                    color = textColor,
                    style = MaterialTheme.typography.bodySmall,
                )
            }
        }
    }
    if (openDialog) {
        MultiTimePickerDialog(
            headerTitle = HomeTheme.strings.timePickerHeader.string(),
            initTime = currentTime,
            onDismissRequest = { openDialog = false },
            onSelectedTime = {
                onChangeTime(it)
                openDialog = false
            },
        )
    }
}

@Composable
internal fun StartTimeField(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    currentTime: LocalDateTime,
    isError: Boolean,
    onChangeTime: (LocalDateTime) -> Unit,
) = BaseTimeField(
    enabled = enabled,
    modifier = modifier,
    currentTime = currentTime,
    isError = isError,
    label = HomeTheme.strings.timeFieldStartLabel.string(),
    leadingIcon = HomeTheme.icons.startTime,
    onChangeTime = onChangeTime,
)

@Composable
internal fun EndTimeField(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    currentTime: LocalDateTime,
    isError: Boolean,
    onChangeTime: (LocalDateTime) -> Unit,
) = BaseTimeField(
    enabled = enabled,
    modifier = modifier,
    currentTime = currentTime,
    isError = isError,
    label = HomeTheme.strings.timeFieldEndLabel.string(),
    leadingIcon = HomeTheme.icons.endTime,
    onChangeTime = onChangeTime,
)

@Composable
internal fun DurationTitle(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    duration: Long,
    startTime: LocalDateTime,
    isError: Boolean = false,
    onChangeDuration: (Long) -> Unit,
) {
    var isOpenDurationDialog by remember { mutableStateOf(false) }
    val correctDuration = if (duration < 0L) 0L else duration
    val titleColor = when (isError) {
        true -> MaterialTheme.colorScheme.error
        false -> MaterialTheme.colorScheme.onSurface
    }
    Box(
        modifier = modifier.clip(MaterialTheme.shapes.small).clickable(enabled) {
            isOpenDurationDialog = true
        },
    ) {
        Text(
            modifier = Modifier.padding(4.dp),
            text = correctDuration.toMinutesAndHoursTitle(),
            style = MaterialTheme.typography.bodyLarge,
            maxLines = 1,
            color = titleColor,
        )
    }
    if (isOpenDurationDialog) {
        DurationPickerDialog(
            headerTitle = HomeTheme.strings.durationPickerTitle.string(),
            duration = duration,
            startTime = startTime,
            onDismissRequest = { isOpenDurationDialog = false },
            onSelectedTime = {
                onChangeDuration(it)
                isOpenDurationDialog = false
            },
        )
    }
}