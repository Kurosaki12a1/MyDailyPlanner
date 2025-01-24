package com.kuro.mdp.features.overview.presentation.ui.component

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
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
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AssistChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RichTooltip
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TooltipBox
import androidx.compose.material3.TooltipDefaults
import androidx.compose.material3.rememberTooltipState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.kuro.mdp.features.overview.domain.model.categories.CategoriesOverView
import com.kuro.mdp.features.overview.domain.model.schedules.UndefinedTaskOverView
import com.kuro.mdp.features.overview.presentation.theme.OverViewTheme
import com.kuro.mdp.features.overview.presentation.util.fetchName
import com.kuro.mdp.shared.domain.model.schedules.TaskPriority
import com.kuro.mdp.shared.presentation.mappers.mapToIconPainter
import com.kuro.mdp.shared.presentation.theme.materials.badgePriorityMax
import com.kuro.mdp.shared.presentation.theme.materials.badgePriorityMedium
import com.kuro.mdp.shared.presentation.views.MonogramBadge
import com.kuro.mdp.shared.presentation.views.PlaceHolderBox
import com.kuro.mdp.shared.presentation.views.toDaysTitle
import com.kuro.mdp.shared.utils.extensions.getLocalDateTimeNow
import com.kuro.mdp.shared.utils.extensions.string
import com.kuro.mdp.shared.utils.extensions.toEpochMillis
import com.kuro.mdp.shared.utils.functional.Constants
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDateTime
import org.jetbrains.compose.resources.painterResource

/**
 * Created by: minhthinh.h on 1/21/2025
 *
 * Description:
 */
@Composable
internal fun UndefinedTaskSection(
    modifier: Modifier = Modifier,
    isLoading: Boolean,
    categories: List<CategoriesOverView>,
    tasks: List<UndefinedTaskOverView>,
    onAddOrUpdateTask: (UndefinedTaskOverView) -> Unit,
    onDeleteTask: (UndefinedTaskOverView) -> Unit,
    onExecuteTask: (LocalDateTime, UndefinedTaskOverView) -> Unit,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = OverViewTheme.strings.undefinedTasksHeader.string(),
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.titleSmall,
        )
        AnimatedContent(
            targetState = isLoading,
            label = "Undefined tasks",
            transitionSpec = {
                fadeIn(animationSpec = tween(600, delayMillis = 90)).togetherWith(
                    fadeOut(animationSpec = tween(300)),
                )
            },
        ) { loading ->
            if (!loading) {
                UndefinedTaskSectionLazyRow(
                    categories = categories,
                    tasks = tasks,
                    onAddOrUpdateTask = onAddOrUpdateTask,
                    onDeleteIconClick = onDeleteTask,
                    onExecuteButtonClick = onExecuteTask,
                )
            } else {
                UndefinedTaskSectionLazyRowPlaceholder()
            }
        }
    }
}

@Composable
internal fun UndefinedTaskSectionLazyRow(
    modifier: Modifier = Modifier,
    state: LazyListState = rememberLazyListState(),
    categories: List<CategoriesOverView>,
    tasks: List<UndefinedTaskOverView>,
    onAddOrUpdateTask: (UndefinedTaskOverView) -> Unit,
    onDeleteIconClick: (UndefinedTaskOverView) -> Unit,
    onExecuteButtonClick: (LocalDateTime, UndefinedTaskOverView) -> Unit,
    flingBehavior: FlingBehavior = rememberSnapFlingBehavior(state),
    userScrollEnabled: Boolean = true,
) {
    var openTaskDateChooserDialog by remember { mutableStateOf(false) }
    var openTaskEditorDialog by remember { mutableStateOf(false) }
    var editableTask by remember { mutableStateOf<UndefinedTaskOverView?>(null) }

    LazyRow(
        modifier = modifier,
        state = state,
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        flingBehavior = flingBehavior,
        userScrollEnabled = userScrollEnabled,
    ) {
        item {
            AddUndefinedTaskItem(
                modifier = Modifier.animateItem(fadeInSpec = null, fadeOutSpec = null),
                isCompact = tasks.isNotEmpty(),
                onClick = { openTaskEditorDialog = true; editableTask = null },
            )
        }
        items(tasks) { undefinedTask ->
            UndefinedTaskItem(
                modifier = Modifier.animateItem(fadeInSpec = null, fadeOutSpec = null),
                model = undefinedTask,
                onClick = { openTaskEditorDialog = true; editableTask = undefinedTask },
                onDeleteIconClick = { onDeleteIconClick(undefinedTask) },
                onExecuteButtonClick = {
                    openTaskDateChooserDialog = true; editableTask = undefinedTask
                },
            )
        }
    }

    if (openTaskEditorDialog) {
        UndefinedTaskEditorDialog(
            categories = categories,
            model = editableTask,
            onDismiss = { openTaskEditorDialog = false },
            onConfirm = {
                onAddOrUpdateTask(it)
                openTaskEditorDialog = false
            },
        )
    }

    if (openTaskDateChooserDialog) {
        TaskDateChooserDialog(
            onDismiss = { openTaskDateChooserDialog = false },
            onConfirm = { date ->
                editableTask?.let { task -> onExecuteButtonClick(date, task) }
                openTaskDateChooserDialog = false
            },
        )
    }
}

@Composable
internal fun UndefinedTaskSectionLazyRowPlaceholder(
    modifier: Modifier = Modifier,
) {
    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        userScrollEnabled = false,
    ) {
        items(Constants.Placeholder.ITEMS) {
            PlaceHolderBox(
                modifier = Modifier.size(165.dp, 170.dp),
                shape = MaterialTheme.shapes.large,
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun UndefinedTaskItem(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    model: UndefinedTaskOverView,
    onClick: () -> Unit,
    onDeleteIconClick: () -> Unit,
    onExecuteButtonClick: () -> Unit,
) {
    Surface(
        onClick = onClick,
        modifier = modifier.size(175.dp, 175.dp),
        enabled = enabled,
        shape = MaterialTheme.shapes.large,
        color = MaterialTheme.colorScheme.surfaceContainerLow,
    ) {
        val scope = rememberCoroutineScope()
        val tooltipState = rememberTooltipState(isPersistent = true)

        Column(
            modifier = Modifier.padding(start = 12.dp, end = 8.dp, top = 12.dp, bottom = 12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Row(
                modifier = Modifier.height(32.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                if (model.mainCategory.defaultType != null) {
                    Icon(
                        painter = model.mainCategory.defaultType!!.mapToIconPainter(),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                    )
                } else {
                    Text(
                        text = model.mainCategory.customName?.first()?.uppercaseChar()?.toString() ?: "*",
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.titleLarge,
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                if (model.deadline != null) {
                    DeadlineView(deadline = model.deadline!!)
                }
                if (model.note != null) {
                    TooltipBox(
                        positionProvider = TooltipDefaults.rememberPlainTooltipPositionProvider(),
                        state = tooltipState,
                        tooltip = {
                            RichTooltip(
                                title = { Text(text = OverViewTheme.strings.noteTitle.string()) },
                                text = { Text(text = model.note ?: "") },
                            )
                        },
                    ) {
                        Box(
                            modifier = Modifier
                                .size(32.dp)
                                .clip(MaterialTheme.shapes.small)
                                .background(MaterialTheme.colorScheme.surfaceContainerHigh)
                                .clickable {
                                    scope.launch {
                                        if (tooltipState.isVisible) tooltipState.dismiss() else tooltipState.show()
                                    }
                                },
                        ) {
                            Icon(
                                modifier = Modifier.size(18.dp).align(Alignment.Center),
                                painter = painterResource(OverViewTheme.icons.notes),
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onPrimaryContainer,
                            )
                        }
                    }
                }
            }
            Column(modifier = Modifier.weight(1f)) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = model.mainCategory.fetchName() ?: OverViewTheme.strings.noneTitle.string(),
                        color = MaterialTheme.colorScheme.onSurface,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.titleMedium,
                    )
                    if (model.priority.isImportant()) {
                        MonogramBadge(
                            color = when (model.priority == TaskPriority.MEDIUM) {
                                true -> badgePriorityMedium
                                false -> badgePriorityMax
                            },
                        )
                    }
                }
                Text(
                    text = model.subCategory?.name ?: OverViewTheme.strings.noneTitle.string(),
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                AssistChip(
                    modifier = Modifier.weight(1f).height(32.dp),
                    label = {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = OverViewTheme.strings.executeUndefinedTasksTitle.string(),
                            textAlign = TextAlign.Center,
                        )
                    },
                    onClick = onExecuteButtonClick,
                )
                IconButton(
                    modifier = Modifier.size(24.dp),
                    onClick = onDeleteIconClick,
                ) {
                    Icon(
                        modifier = Modifier.size(18.dp),
                        imageVector = Icons.Default.Delete,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
            }
        }
    }
}

@Composable
internal fun DeadlineView(
    modifier: Modifier = Modifier,
    deadline: LocalDateTime,
) {
    Surface(
        modifier = modifier,
        shape = MaterialTheme.shapes.small,
        color = MaterialTheme.colorScheme.surfaceContainerHigh,
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 4.dp, vertical = 6.dp),
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                modifier = Modifier.size(18.dp),
                painter = painterResource(OverViewTheme.icons.duration),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
            )
            Text(
                text = (deadline.toEpochMillis() - getLocalDateTimeNow().toEpochMillis()).toDaysTitle(),
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.labelMedium,
            )
        }
    }
}

@Composable
internal fun AddUndefinedTaskItem(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    isCompact: Boolean = true,
    onClick: () -> Unit,
) {
    Surface(
        onClick = onClick,
        modifier = modifier.height(170.dp).width(if (isCompact) 48.dp else 165.dp).animateContentSize(),
        enabled = enabled,
        shape = MaterialTheme.shapes.large,
        color = MaterialTheme.colorScheme.surfaceContainerLow,
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
            )
        }
    }
}
