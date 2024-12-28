package com.kuro.mdp.features.home.presentation.ui.home.ui.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.VisibilityThreshold
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.kuro.mdp.features.home.domain.model.schedules.TimeTaskHome
import com.kuro.mdp.features.home.presentation.ui.home.theme.HomeTheme
import com.kuro.mdp.shared.domain.model.schedules.DailyScheduleStatus
import com.kuro.mdp.shared.domain.model.schedules.TaskPriority
import com.kuro.mdp.shared.domain.model.schedules.TimeTaskStatus
import com.kuro.mdp.shared.domain.model.settings.ViewToggleStatus
import com.kuro.mdp.shared.presentation.mappers.mapToUi
import com.kuro.mdp.shared.presentation.theme.AppTheme
import com.kuro.mdp.shared.presentation.views.CategoryIconMonogram
import com.kuro.mdp.shared.presentation.views.CategoryTextMonogram
import com.kuro.mdp.shared.presentation.views.ExpandedIcon
import com.kuro.mdp.shared.utils.extensions.endThisDay
import com.kuro.mdp.shared.utils.extensions.isCurrentDay
import com.kuro.mdp.shared.utils.extensions.isNotZeroDifference
import com.kuro.mdp.shared.utils.extensions.string
import kotlinx.datetime.LocalDateTime
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

/**
 * Created by: minhthinh.h on 12/19/2024
 *
 * Description:
 */
@Composable
@OptIn(ExperimentalFoundationApi::class)
internal fun TimeTasksSection(
    modifier: Modifier = Modifier,
    listState: LazyListState = rememberLazyListState(),
    dateStatus: DailyScheduleStatus?,
    currentDate: LocalDateTime?,
    timeTasks: List<TimeTaskHome>,
    timeTaskViewStatus: ViewToggleStatus,
    onCreateSchedule: () -> Unit,
    onTimeTaskEdit: (TimeTaskHome) -> Unit,
    onTaskDoneChange: (TimeTaskHome) -> Unit,
    onTimeTaskAdd: (startTime: LocalDateTime, endTime: LocalDateTime) -> Unit,
    onTimeTaskIncrease: (TimeTaskHome) -> Unit,
    onTimeTaskReduce: (TimeTaskHome) -> Unit,
) = AnimatedVisibility(
    visible = currentDate != null,
    enter = fadeIn() + scaleIn(initialScale = 0.9f),
    exit = fadeOut(),
) {
    val isCompactView = timeTaskViewStatus == ViewToggleStatus.COMPACT
    var isScrolled by rememberSaveable { mutableStateOf(false) }
    val visibleFirstAdd = timeTasks.isNotEmpty() && currentDate != null && timeTasks.first().startTime > currentDate && !isCompactView

    Box(modifier = modifier.fillMaxSize()) {
        if (dateStatus != null) {
            LazyColumn(
                state = listState,
                contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                if (visibleFirstAdd) {
                    item {
                        val startTime = checkNotNull(currentDate)
                        val endTime = timeTasks[0].startTime
                        AddTimeTaskViewItem(
                            modifier = Modifier.animateItemPlacement(
                                animationSpec = spring(
                                    stiffness = Spring.StiffnessMedium,
                                    visibilityThreshold = IntOffset.VisibilityThreshold,
                                ),
                            ),
                            onAddClick = { onTimeTaskAdd(startTime, endTime) },
                            startTime = startTime,
                            endTime = endTime,
                        )
                    }
                }
                items(timeTasks, key = { it.key }) { timeTask ->
                    val timeTaskIndex = timeTasks.indexOf(timeTask)
                    val nextItem = timeTasks.getOrNull(timeTaskIndex + 1)

                    TimeTaskViewItem(
                        modifier = Modifier.animateItemPlacement(
                            animationSpec = spring(
                                stiffness = Spring.StiffnessMedium,
                                visibilityThreshold = IntOffset.VisibilityThreshold,
                            ),
                        ),
                        timeTask = timeTask,
                        onEdit = onTimeTaskEdit,
                        onIncrease = onTimeTaskIncrease,
                        onReduce = onTimeTaskReduce,
                        onDoneChange = onTaskDoneChange,
                        isCompactView = isCompactView && nextItem != null && timeTask.endTime.isNotZeroDifference(
                            nextItem.startTime,
                        ),
                    )
                    AnimatedVisibility(
                        enter = fadeIn() + slideInVertically(),
                        exit = shrinkVertically() + fadeOut(),
                        visible = nextItem != null &&
                                timeTask.endTime.isNotZeroDifference(nextItem.startTime) &&
                                !isCompactView,
                    ) {
                        val trackColor = when (timeTask.executionStatus) {
                            TimeTaskStatus.PLANNED -> MaterialTheme.colorScheme.surfaceContainerLow
                            TimeTaskStatus.RUNNING -> MaterialTheme.colorScheme.primaryContainer
                            TimeTaskStatus.COMPLETED -> MaterialTheme.colorScheme.tertiaryContainer
                        }
                        if (nextItem != null) {
                            AddTimeTaskViewItem(
                                modifier = Modifier.animateItemPlacement(
                                    animationSpec = spring(
                                        stiffness = Spring.StiffnessMedium,
                                        visibilityThreshold = IntOffset.VisibilityThreshold,
                                    ),
                                ),
                                onAddClick = { onTimeTaskAdd.invoke(timeTask.endTime, nextItem.startTime) },
                                startTime = timeTask.endTime,
                                endTime = nextItem.startTime,
                                indicatorColor = trackColor,
                            )
                        }
                    }
                }
                item {
                    val startTime = when (timeTasks.isEmpty()) {
                        true -> checkNotNull(currentDate)
                        false -> timeTasks.last().endTime
                    }
                    val endTime = startTime.endThisDay()
                    AddTimeTaskViewItem(
                        modifier = Modifier.animateItemPlacement(
                            animationSpec = spring(
                                stiffness = Spring.StiffnessMedium,
                                visibilityThreshold = IntOffset.VisibilityThreshold,
                            ),
                        ),
                        enabled = timeTasks.isEmpty() || timeTasks.last().endTime.isCurrentDay(currentDate!!),
                        onAddClick = { onTimeTaskAdd(startTime, endTime) },
                        startTime = startTime,
                        endTime = endTime,
                    )
                }
                item { EmptyItem() }
            }
            LaunchedEffect(Unit) {
                val runningTask = timeTasks.find { it.executionStatus == TimeTaskStatus.RUNNING }
                if (runningTask != null && !isScrolled) {
                    val index = timeTasks.indexOf(runningTask) + if (visibleFirstAdd) 1 else 0
                    listState.animateScrollToItem(index)
                    isScrolled = true
                }
            }
        } else if (currentDate != null) {
            EmptyDateView(
                modifier = Modifier.align(Alignment.Center),
                emptyTitle = stringResource(AppTheme.strings.emptyScheduleTitle),
                subTitle = null,
            ) {
                OutlinedButton(
                    onClick = onCreateSchedule,
                    modifier = Modifier.width(185.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = MaterialTheme.colorScheme.secondary,
                    ),
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
                    contentPadding = PaddingValues(horizontal = 4.dp),
                ) {
                    Icon(
                        modifier = Modifier
                            .size(18.dp)
                            .align(Alignment.CenterVertically),
                        imageVector = Icons.Default.Add,
                        contentDescription = stringResource(HomeTheme.strings.createScheduleDesc),
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                    Text(
                        modifier = Modifier
                            .padding(start = 4.dp)
                            .align(Alignment.CenterVertically),
                        text = stringResource(HomeTheme.strings.createScheduleTitle),
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        style = MaterialTheme.typography.bodyMedium,
                    )
                }
            }
        }
    }
}

@Composable
internal fun AddTimeTaskView(
    modifier: Modifier = Modifier,
    showAddIconForFreeTime: Boolean = true,
    isFreeTime: Boolean,
    remainingTimeTitle: String,
    onViewClicked: () -> Unit,
) {
    Surface(
        onClick = onViewClicked,
        modifier = modifier.height(46.dp),
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.background,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.surfaceVariant),
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if (showAddIconForFreeTime) {
                Icon(
                    modifier = Modifier.size(18.dp),
                    imageVector = Icons.Filled.Add,
                    contentDescription = stringResource(HomeTheme.strings.timeTaskIncreaseTimeTitle),
                    tint = MaterialTheme.colorScheme.onSurface,
                )
            }
            Text(
                text = stringResource(if (isFreeTime) HomeTheme.strings.addFreeTimeTaskTitle else HomeTheme.strings.addTaskTitle),
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface,
            )
            Spacer(modifier = Modifier.weight(1f))
            if (isFreeTime) {
                Text(
                    text = remainingTimeTitle,
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        }
    }
}

@Composable
internal fun PlannedTimeTask(
    modifier: Modifier = Modifier,
    onViewClicked: () -> Unit,
    taskTitle: String,
    taskSubTitle: String?,
    taskDurationTitle: String,
    categoryIcon: Painter?,
    priority: TaskPriority,
    enabledNotifications: Boolean,
    note: String?,
) {
    var expandedNote by rememberSaveable { mutableStateOf(false) }

    Surface(
        onClick = onViewClicked,
        modifier = modifier,
        enabled = true,
        shape = MaterialTheme.shapes.large,
        color = MaterialTheme.colorScheme.surfaceContainerLow,
    ) {
        Column(modifier = Modifier.animateContentSize()) {
            Row(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, top = 16.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                Box(modifier = Modifier.align(Alignment.Top)) {
                    if (categoryIcon != null) {
                        CategoryIconMonogram(
                            icon = categoryIcon,
                            iconDescription = taskTitle,
                            iconColor = MaterialTheme.colorScheme.primary,
                            priority = priority.mapToUi(),
                            backgroundColor = MaterialTheme.colorScheme.primaryContainer,
                        )
                    } else {
                        CategoryTextMonogram(
                            text = taskTitle.first().toString(),
                            textColor = MaterialTheme.colorScheme.primary,
                            priority = priority.mapToUi(),
                            backgroundColor = MaterialTheme.colorScheme.primaryContainer,
                        )
                    }
                }
                TimeTaskTitles(
                    modifier = Modifier.weight(1f),
                    title = taskTitle,
                    titleColor = MaterialTheme.colorScheme.onSurface,
                    subTitle = taskSubTitle,
                )
                Box(
                    modifier = Modifier.align(
                        alignment = when (taskSubTitle == null) {
                            true -> Alignment.CenterVertically
                            false -> Alignment.Top
                        },
                    ),
                ) {
                    TimeTaskDurationTitle(
                        title = taskDurationTitle,
                        enabledNotifications = enabledNotifications,
                    )
                }
            }
            if (!note.isNullOrEmpty()) {
                TimeTaskNoteView(
                    modifier = Modifier.padding(vertical = 8.dp, horizontal = 12.dp),
                    onClick = { expandedNote = !expandedNote },
                    text = note,
                    expanded = expandedNote,
                    container = MaterialTheme.colorScheme.surfaceVariant,
                    content = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            } else {
                Spacer(modifier = Modifier.padding(bottom = 16.dp))
            }
        }
    }
}

@Composable
internal fun RunningTimeTask(
    modifier: Modifier = Modifier,
    onMoreButtonClick: () -> Unit,
    onIncreaseTime: () -> Unit,
    onReduceTime: () -> Unit,
    taskTitle: String,
    taskSubTitle: String?,
    categoryIcon: Painter?,
    priority: TaskPriority,
    note: String?,
) {
    var expandedTask by rememberSaveable { mutableStateOf(false) }
    var expandedNote by rememberSaveable { mutableStateOf(false) }

    Surface(
        onClick = { expandedTask = !expandedTask },
        modifier = modifier,
        shape = MaterialTheme.shapes.large,
        color = MaterialTheme.colorScheme.primaryContainer,

        ) {
        Column(modifier = Modifier.animateContentSize()) {
            Row(
                modifier = Modifier
                    .padding(start = 16.dp, end = 8.dp, top = 16.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                Box(modifier = Modifier.align(Alignment.Top)) {
                    if (categoryIcon != null) {
                        CategoryIconMonogram(
                            icon = categoryIcon,
                            iconDescription = taskTitle,
                            iconColor = MaterialTheme.colorScheme.onPrimary,
                            priority = priority.mapToUi(),
                            backgroundColor = MaterialTheme.colorScheme.primary,
                        )
                    } else {
                        CategoryTextMonogram(
                            text = taskTitle.first().toString(),
                            textColor = MaterialTheme.colorScheme.onPrimary,
                            backgroundColor = MaterialTheme.colorScheme.primary,
                            priority = priority.mapToUi(),
                        )
                    }
                }
                TimeTaskTitles(
                    modifier = Modifier.weight(1f),
                    title = taskTitle,
                    titleColor = MaterialTheme.colorScheme.onSurface,
                    subTitle = taskSubTitle,
                )
                Box(
                    modifier = Modifier.size(36.dp).animateContentSize(),
                    contentAlignment = Alignment.Center,
                ) {
                    ExpandedIcon(
                        isExpanded = expandedTask,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        description = null,
                    )
                }
            }
            if (!note.isNullOrEmpty()) {
                TimeTaskNoteView(
                    modifier = Modifier.padding(vertical = 8.dp, horizontal = 12.dp),
                    onClick = { expandedNote = !expandedNote },
                    text = note,
                    expanded = expandedNote,
                )
            } else {
                Spacer(modifier = Modifier.padding(bottom = 16.dp))
            }
            if (expandedTask) {
                HorizontalDivider(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    color = MaterialTheme.colorScheme.primary
                )
                Row(
                    modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    TextButton(onClick = onIncreaseTime) {
                        Icon(
                            painter = painterResource(HomeTheme.icons.add),
                            contentDescription = HomeTheme.strings.timeTaskAddIconDesc.string(),
                            tint = MaterialTheme.colorScheme.primary,
                        )
                        Text(
                            text = HomeTheme.strings.timeTaskIncreaseTimeTitle.string(),
                            color = MaterialTheme.colorScheme.primary,
                            style = MaterialTheme.typography.titleSmall,
                        )
                    }
                    TextButton(onClick = onReduceTime) {
                        Icon(
                            painter = painterResource(HomeTheme.icons.remove),
                            contentDescription = stringResource(HomeTheme.strings.timeTaskRemoveIconDesc),
                            tint = MaterialTheme.colorScheme.primary,
                        )
                        Text(
                            text = stringResource(HomeTheme.strings.timeTaskReduceTimeTitle),
                            color = MaterialTheme.colorScheme.primary,
                            style = MaterialTheme.typography.titleSmall,
                        )
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    IconButton(modifier = Modifier.size(36.dp), onClick = onMoreButtonClick) {
                        Icon(
                            painter = painterResource(HomeTheme.icons.more),
                            contentDescription = stringResource(HomeTheme.strings.timeTaskMoreIconDesc),
                        )
                    }
                }
            }
        }
    }
}

@Composable
internal fun CompletedTimeTask(
    modifier: Modifier = Modifier,
    onViewClicked: () -> Unit,
    onDoneChange: () -> Unit,
    taskTitle: String,
    taskSubTitle: String?,
    categoryIcon: Painter?,
    isCompleted: Boolean,
    note: String?,
) {
    Surface(
        modifier = modifier,
        onClick = onViewClicked,
        enabled = true,
        color = MaterialTheme.colorScheme.tertiaryContainer,
        shape = MaterialTheme.shapes.large,
    ) {
        Row(
            modifier = Modifier
                .padding(top = 16.dp, bottom = 16.dp, start = 16.dp, end = 8.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Box(modifier = Modifier.align(Alignment.Top)) {
                if (categoryIcon != null) {
                    CategoryIconMonogram(
                        icon = categoryIcon,
                        iconDescription = taskTitle,
                        iconColor = MaterialTheme.colorScheme.onTertiary,
                        backgroundColor = MaterialTheme.colorScheme.tertiary,
                    )
                } else {
                    CategoryTextMonogram(
                        text = taskTitle.first().toString(),
                        textColor = MaterialTheme.colorScheme.onTertiary,
                        backgroundColor = MaterialTheme.colorScheme.tertiary,
                    )
                }
            }
            TimeTaskTitles(
                modifier = Modifier.weight(1f),
                title = taskTitle,
                titleColor = MaterialTheme.colorScheme.onSurface,
                subTitle = taskSubTitle,
            )
            IconButton(
                modifier = Modifier.size(36.dp),
                onClick = onDoneChange,
            ) {
                if (isCompleted) {
                    Icon(
                        modifier = Modifier.size(24.dp),
                        painter = painterResource(HomeTheme.icons.check),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                } else {
                    Icon(
                        modifier = Modifier.size(24.dp),
                        painter = painterResource(HomeTheme.icons.cancel),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurface,
                    )
                }
            }
        }
    }
}

@Composable
internal fun TimeTaskTitles(
    modifier: Modifier = Modifier,
    title: String,
    titleColor: Color,
    subTitle: String?,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = title,
            color = titleColor,
            style = MaterialTheme.typography.titleMedium,
        )
        if (subTitle != null) {
            Text(
                text = subTitle,
                modifier = Modifier.padding(top = 2.dp),
                color = titleColor,
                style = MaterialTheme.typography.bodyMedium,
            )
        }
    }
}

@Composable
private fun TimeTaskDurationTitle(
    modifier: Modifier = Modifier,
    title: String,
    enabledNotifications: Boolean,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = title,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.End,
            maxLines = 1,
            style = MaterialTheme.typography.bodyLarge,
        )
        if (!enabledNotifications) {
            Icon(
                modifier = Modifier.size(18.dp),
                painter = painterResource(HomeTheme.icons.offNotifications),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.error,
            )
        }
    }
}

@Composable
private fun TimeTaskNoteView(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    text: String,
    expanded: Boolean,
    container: Color = MaterialTheme.colorScheme.primary,
    content: Color = MaterialTheme.colorScheme.onPrimary,
) {
    var isOverflow by remember { mutableStateOf(false) }

    Surface(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled && isOverflow || enabled && expanded,
        shape = MaterialTheme.shapes.medium,
        color = container,
    ) {
        AnimatedContent(
            targetState = expanded,
            label = "Note",
        ) { isExpanded ->
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp, vertical = 6.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    modifier = Modifier.size(18.dp),
                    painter = painterResource(HomeTheme.icons.notes),
                    contentDescription = stringResource(HomeTheme.strings.noteTitle),
                    tint = content,
                )
                Text(
                    text = text,
                    color = content,
                    maxLines = when (isExpanded) {
                        true -> 4
                        false -> 1
                    },
                    overflow = TextOverflow.Ellipsis,
                    onTextLayout = { result -> isOverflow = result.didOverflowHeight },
                    style = MaterialTheme.typography.labelMedium,
                )
            }
        }
    }
}