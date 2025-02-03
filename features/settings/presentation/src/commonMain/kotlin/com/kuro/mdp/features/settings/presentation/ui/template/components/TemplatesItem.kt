package com.kuro.mdp.features.settings.presentation.ui.template.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import com.kuro.mdp.features.settings.domain.mapper.categories.mapToDomain
import com.kuro.mdp.features.settings.domain.mapper.templates.mapToDomain
import com.kuro.mdp.features.settings.domain.mapper.templates.mapToUi
import com.kuro.mdp.features.settings.domain.model.categories.CategoriesUi
import com.kuro.mdp.features.settings.domain.model.template.TemplateUi
import com.kuro.mdp.features.settings.presentation.extension.fetchName
import com.kuro.mdp.features.settings.presentation.theme.SettingsTheme
import com.kuro.mdp.shared.domain.model.template.RepeatTime
import com.kuro.mdp.shared.presentation.mappers.mapToIconPainter
import com.kuro.mdp.shared.presentation.mappers.mapToString
import com.kuro.mdp.shared.presentation.theme.AppTheme
import com.kuro.mdp.shared.presentation.views.CategoryIconMonogram
import com.kuro.mdp.shared.presentation.views.CategoryTextMonogram
import com.kuro.mdp.shared.presentation.views.TemplateEditorDialog
import com.kuro.mdp.shared.presentation.views.toMinutesOrHoursTitle
import com.kuro.mdp.shared.utils.extensions.duration
import com.kuro.mdp.shared.utils.extensions.string
import com.kuro.mdp.shared.utils.format
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.format.char
import org.jetbrains.compose.resources.painterResource

@Composable
internal fun TemplatesItem(
    modifier: Modifier = Modifier,
    categories: List<CategoriesUi>,
    model: TemplateUi,
    onAddRepeat: (RepeatTime) -> Unit,
    onUpdate: (TemplateUi) -> Unit = {},
    onRestartRepeat: () -> Unit,
    onStopRepeat: () -> Unit,
    onDeleteRepeat: (RepeatTime) -> Unit,
    onDeleteTemplate: () -> Unit,
) {
    var isShowTemplateEditor by rememberSaveable { mutableStateOf(false) }
    val categoryIcon = model.category.defaultType?.mapToIconPainter()
    val categoryName = model.category.fetchName() ?: "*"

    Surface(
        onClick = { isShowTemplateEditor = true },
        modifier = modifier.fillMaxWidth().animateContentSize(),
        shape = MaterialTheme.shapes.large,
        color = MaterialTheme.colorScheme.surfaceContainerLow,
    ) {
        Row(
            modifier = Modifier.padding(start = 16.dp, end = 8.dp, top = 16.dp, bottom = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                if (categoryIcon != null) {
                    CategoryIconMonogram(
                        icon = categoryIcon,
                        iconDescription = categoryName,
                        iconColor = MaterialTheme.colorScheme.primary,
                        backgroundColor = MaterialTheme.colorScheme.primaryContainer,
                    )
                } else {
                    CategoryTextMonogram(
                        text = categoryName.first().toString(),
                        textColor = MaterialTheme.colorScheme.primary,
                        backgroundColor = MaterialTheme.colorScheme.primaryContainer,
                    )
                }
                Text(
                    text = when (model.subCategory != null) {
                        true -> AppTheme.strings.splitFormat.string()
                            .format(categoryName, model.subCategory?.name)

                        false -> categoryName
                    },
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.titleMedium,
                )
                TemplateItemInfo(
                    startTime = model.startTime,
                    endTime = model.endTime,
                    isEnableNotification = model.isEnableNotification,
                    isConsiderInStatistics = model.isConsiderInStatistics,
                    repeatTimes = model.repeatTimes,
                )
            }
            TemplateItemControlButtons(
                repeatEnabled = model.repeatEnabled,
                repeatTimes = model.repeatTimes,
                onDeleteTemplate = onDeleteTemplate,
                onAddRepeat = onAddRepeat,
                onDeleteRepeat = onDeleteRepeat,
                onRestartRepeat = onRestartRepeat,
                onStopRepeat = onStopRepeat,
            )
        }
    }

    if (isShowTemplateEditor) {
        TemplateEditorDialog(
            categories = categories.map { it.mapToDomain() },
            model = model.mapToDomain(),
            onDismiss = { isShowTemplateEditor = false },
            onConfirm = { template ->
                onUpdate(template.mapToUi())
                isShowTemplateEditor = false
            },
        )
    }
}

@Composable
internal fun TemplateItemInfo(
    modifier: Modifier = Modifier,
    startTime: LocalDateTime,
    endTime: LocalDateTime,
    isEnableNotification: Boolean,
    isConsiderInStatistics: Boolean,
    repeatTimes: List<RepeatTime>,
) {
    val timeFormat = LocalDateTime.Format {
        dayOfMonth()
        char('/')
        monthNumber()
        char('/')
        yearTwoDigits(2000)
    }
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Row(modifier = modifier, horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                TemplateInfoIcon(
                    icon = painterResource(SettingsTheme.icons.time),
                    title = "${timeFormat.format(startTime)} - ${timeFormat.format(endTime)}",
                )
                TemplateInfoIcon(
                    icon = painterResource(SettingsTheme.icons.duration),
                    title = duration(startTime, endTime).toMinutesOrHoursTitle(),
                )
            }
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                TemplateInfoIcon(
                    icon = painterResource(SettingsTheme.icons.statistics),
                    title = when (isConsiderInStatistics) {
                        true -> SettingsTheme.strings.statisticsActiveTitle.string()
                        false -> SettingsTheme.strings.statisticsDisabledTitle.string()
                    },
                )
                TemplateInfoIcon(
                    icon = painterResource(SettingsTheme.icons.notification),
                    title = when (isEnableNotification) {
                        true -> SettingsTheme.strings.notificationEnabledTitle.string()
                        false -> SettingsTheme.strings.notificationDisabledTitle.string()
                    },
                )
            }
        }
        if (repeatTimes.isNotEmpty()) {
            TemplateInfoIcon(
                icon = painterResource(SettingsTheme.icons.repeatVariant),
                title = "${repeatTimes.first().repeatType.mapToString()} (${repeatTimes.size})",
            )
        }
    }
}

@Composable
internal fun TemplateInfoIcon(
    modifier: Modifier = Modifier,
    icon: Painter,
    title: String,
) {
    Row(modifier = modifier, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        Icon(
            modifier = Modifier.size(18.dp),
            painter = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        Text(
            text = title,
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.labelMedium,
        )
    }
}

@Composable
internal fun TemplateItemControlButtons(
    modifier: Modifier = Modifier,
    repeatEnabled: Boolean,
    repeatTimes: List<RepeatTime>,
    onDeleteTemplate: () -> Unit,
    onAddRepeat: (RepeatTime) -> Unit,
    onDeleteRepeat: (RepeatTime) -> Unit,
    onRestartRepeat: () -> Unit,
    onStopRepeat: () -> Unit,
) {
    var isShowRepeatTimesMenu by rememberSaveable { mutableStateOf(false) }

    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(12.dp)) {
        if (repeatTimes.isEmpty()) {
            IconButton(onClick = { isShowRepeatTimesMenu = true }) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(SettingsTheme.icons.repeat),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
            IconButton(onClick = onDeleteTemplate) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    imageVector = Icons.Default.Delete,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        } else {
            IconButton(onClick = { isShowRepeatTimesMenu = true }) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(SettingsTheme.icons.updateRepeat),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
            if (repeatEnabled) {
                IconButton(onClick = onStopRepeat) {
                    Icon(
                        modifier = Modifier.size(24.dp),
                        painter = painterResource(SettingsTheme.icons.stop),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
            } else {
                IconButton(onClick = onRestartRepeat) {
                    Icon(
                        modifier = Modifier.size(24.dp),
                        painter = painterResource(SettingsTheme.icons.start),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.error,
                    )
                }
            }
        }
    }
    Box(contentAlignment = Alignment.TopEnd) {
        RepeatTimeMenu(
            isExpanded = isShowRepeatTimesMenu,
            selectedTimes = repeatTimes,
            onDismiss = { isShowRepeatTimesMenu = false },
            onAddRepeat = onAddRepeat,
            onDeleteRepeat = onDeleteRepeat,
        )
    }
}
