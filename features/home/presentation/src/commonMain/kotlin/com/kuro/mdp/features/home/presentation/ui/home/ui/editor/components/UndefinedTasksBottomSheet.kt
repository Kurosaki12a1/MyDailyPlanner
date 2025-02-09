package com.kuro.mdp.features.home.presentation.ui.home.ui.editor.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Badge
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.kuro.mdp.features.home.domain.model.schedules.UndefinedTaskHome
import com.kuro.mdp.features.home.presentation.ui.home.theme.HomeTheme
import com.kuro.mdp.features.home.presentation.ui.home.util.fetchName
import com.kuro.mdp.shared.presentation.extensions.alphaByEnabled
import com.kuro.mdp.shared.presentation.extensions.string
import com.kuro.mdp.shared.presentation.mappers.mapToIconPainter
import com.kuro.mdp.shared.presentation.mappers.mapToUi
import com.kuro.mdp.shared.presentation.views.CategoryIconMonogram
import com.kuro.mdp.shared.presentation.views.CategoryTextMonogram
import com.kuro.mdp.shared.presentation.views.NoneItemsView
import com.kuro.mdp.shared.presentation.views.toDaysTitle
import com.kuro.mdp.shared.utils.extensions.getLocalDateTimeNow
import com.kuro.mdp.shared.utils.extensions.toEpochMillis
import kotlinx.datetime.LocalDateTime
import org.jetbrains.compose.resources.painterResource

/**
 * Created by: minhthinh.h on 2/7/2025
 *
 * Description:
 */
@Composable
@ExperimentalMaterial3Api
internal fun UndefinedTasksBottomSheet(
    modifier: Modifier = Modifier,
    isShow: Boolean,
    undefinedTasks: List<UndefinedTaskHome>?,
    currentUndefinedTaskId: Long?,
    onDismiss: () -> Unit,
    onChooseUndefinedTask: (UndefinedTaskHome) -> Unit,
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    if (isShow) {
        ModalBottomSheet(
            modifier = modifier,
            sheetState = sheetState,
            contentWindowInsets = { WindowInsets(0.dp) },
            onDismissRequest = onDismiss,
        ) {
            Column(modifier = Modifier.heightIn(min = 350.dp)) {
                UndefinedTasksBottomSheetHeader(tasksCount = undefinedTasks?.size)
                HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp))
                LazyColumn(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                ) {
                    if (undefinedTasks != null) {
                        if (undefinedTasks.isNotEmpty()) {
                            items(items = undefinedTasks, key = { it.id }) { task ->
                                UndefinedTaskBottomSheetItem(
                                    enable = task.id != currentUndefinedTaskId,
                                    model = task,
                                    onChoose = { onChooseUndefinedTask(task) },
                                )
                            }
                        } else {
                            item {
                                NoneItemsView(text = HomeTheme.strings.emptyTemplatesTitle.string())
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
@ExperimentalMaterial3Api
internal fun UndefinedTasksBottomSheetHeader(
    modifier: Modifier = Modifier,
    tasksCount: Int?,
) {
    Row(
        modifier = modifier.padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Text(
            text = HomeTheme.strings.undefinedTasksSheetTitle.string(),
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.titleLarge,
        )
        Badge(modifier = Modifier.align(Alignment.CenterVertically).width(22.dp)) {
            Text(tasksCount?.toString() ?: "-")
        }
    }
}

@Composable
internal fun UndefinedTaskBottomSheetItem(
    modifier: Modifier = Modifier,
    enable: Boolean = true,
    model: UndefinedTaskHome,
    onChoose: () -> Unit,
) {
    var expandedNote by rememberSaveable { mutableStateOf(false) }

    Surface(
        onClick = onChoose,
        modifier = modifier.alphaByEnabled(enable),
        enabled = enable,
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.surfaceContainer,
    ) {
        Column(modifier = Modifier.animateContentSize()) {
            Row(
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp).fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                Box(modifier = Modifier.align(Alignment.Top)) {
                    val categoryIcon = model.mainCategory.defaultType?.mapToIconPainter()
                    if (categoryIcon != null) {
                        CategoryIconMonogram(
                            icon = categoryIcon,
                            iconDescription = null,
                            iconColor = MaterialTheme.colorScheme.primary,
                            priority = model.priority.mapToUi(),
                            backgroundColor = MaterialTheme.colorScheme.primaryContainer,
                        )
                    } else {
                        CategoryTextMonogram(
                            text = model.mainCategory.customName?.first().toString(),
                            textColor = MaterialTheme.colorScheme.primary,
                            priority = model.priority.mapToUi(),
                            backgroundColor = MaterialTheme.colorScheme.primaryContainer,
                        )
                    }
                }
                Column {
                    Text(
                        text = model.mainCategory.fetchName() ?: "",
                        color = MaterialTheme.colorScheme.onSurface,
                        style = MaterialTheme.typography.titleMedium,
                    )
                    if (model.subCategory != null) {
                        Text(
                            text = model.subCategory!!.name ?: "",
                            modifier = Modifier.padding(top = 2.dp),
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            style = MaterialTheme.typography.bodyMedium,
                        )
                    }
                }
                Spacer(modifier = Modifier.weight(1f))
                if (model.deadline != null) {
                    DeadlineView(deadline = model.deadline!!)
                }
            }
            if (!model.note.isNullOrEmpty()) {
                UndefinedTaskNoteView(
                    modifier = Modifier.padding(vertical = 8.dp, horizontal = 12.dp),
                    onClick = { expandedNote = !expandedNote },
                    text = model.note!!,
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
                painter = painterResource(HomeTheme.icons.deadline),
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
private fun UndefinedTaskNoteView(
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
                    contentDescription = null,
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
