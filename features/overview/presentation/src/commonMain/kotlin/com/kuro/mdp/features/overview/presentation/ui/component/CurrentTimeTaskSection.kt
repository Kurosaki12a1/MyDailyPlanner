package com.kuro.mdp.features.overview.presentation.ui.component

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RichTooltip
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TooltipBox
import androidx.compose.material3.TooltipDefaults
import androidx.compose.material3.rememberTooltipState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp
import com.kuro.mdp.features.overview.domain.model.schedules.TimeTaskOverView
import com.kuro.mdp.features.overview.presentation.theme.OverViewTheme
import com.kuro.mdp.features.overview.presentation.util.fetchName
import com.kuro.mdp.shared.presentation.mappers.mapToIconPainter
import com.kuro.mdp.shared.presentation.mappers.mapToUi
import com.kuro.mdp.shared.presentation.views.CategoryIconMonogram
import com.kuro.mdp.shared.presentation.views.CategoryTextMonogram
import com.kuro.mdp.shared.presentation.views.PlaceHolderBox
import com.kuro.mdp.shared.utils.extensions.string
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import kotlin.math.roundToInt

/**
 * Created by: minhthinh.h on 1/21/2025
 *
 * Description:
 */
@Composable
internal fun CurrentTimeTaskSection(
    modifier: Modifier = Modifier,
    isLoading: Boolean,
    task: TimeTaskOverView?,
    onOpenTask: () -> Unit,
) {
    Column(
        modifier = modifier.padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Text(
            text = OverViewTheme.strings.currentTaskHeader.string(),
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.titleSmall,
        )
        AnimatedContent(
            targetState = isLoading,
            label = "Current task",
            transitionSpec = {
                fadeIn(animationSpec = tween(600, delayMillis = 90)).togetherWith(
                    fadeOut(animationSpec = tween(300)),
                )
            },
        ) { loading ->
            if (loading) {
                PlaceHolderBox(
                    modifier = Modifier.fillMaxWidth().height(125.dp),
                    shape = MaterialTheme.shapes.large,
                )
            } else if (task != null) {
                CurrentTimeTaskView(
                    model = task,
                    onClick = onOpenTask,
                )
            } else {
                NoneCurrentTimeTaskView()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun CurrentTimeTaskView(
    modifier: Modifier = Modifier,
    model: TimeTaskOverView,
    onClick: () -> Unit,
) {
    val scope = rememberCoroutineScope()
    val tooltipState = rememberTooltipState(isPersistent = true)

    Surface(
        onClick = onClick,
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.primaryContainer,
    ) {
        Column(modifier = Modifier.animateContentSize()) {
            Row(
                modifier = Modifier.padding(start = 16.dp, end = 8.dp, top = 16.dp, bottom = 4.dp).fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                Box(modifier = Modifier.align(Alignment.Top)) {
                    val categoryIcon = model.mainCategory.defaultType?.mapToIconPainter()
                    if (categoryIcon != null) {
                        CategoryIconMonogram(
                            icon = categoryIcon,
                            iconDescription = null,
                            iconColor = MaterialTheme.colorScheme.onPrimary,
                            priority = model.priority.mapToUi(),
                            backgroundColor = MaterialTheme.colorScheme.primary,
                        )
                    } else {
                        CategoryTextMonogram(
                            text = model.mainCategory.fetchName()?.first().toString(),
                            textColor = MaterialTheme.colorScheme.onPrimary,
                            backgroundColor = MaterialTheme.colorScheme.primary,
                            priority = model.priority.mapToUi(),
                        )
                    }
                }
                TimeTaskTitles(
                    modifier = Modifier.weight(1f),
                    title = model.mainCategory.fetchName() ?: OverViewTheme.strings.noneTitle.string(),
                    titleColor = MaterialTheme.colorScheme.onSurface,
                    subTitle = model.subCategory?.name,
                )
                if (model.note != null) {
                    TooltipBox(
                        positionProvider = TooltipDefaults.rememberPlainTooltipPositionProvider(),
                        state = tooltipState,
                        tooltip = {
                            RichTooltip(
                                title = { Text(text = OverViewTheme.strings.noteTitle.string()) },
                                text = { Text(text = model.note ?: "") },
                            )
                        }
                    ) {
                        IconButton(
                            modifier = Modifier.size(32.dp),
                            onClick = {
                                scope.launch {
                                    if (!tooltipState.isVisible) tooltipState.show() else tooltipState.dismiss()
                                }
                            },
                        ) {
                            Icon(
                                modifier = Modifier.size(18.dp),
                                painter = painterResource(OverViewTheme.icons.notes),
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onPrimaryContainer,
                            )
                        }
                    }
                }
            }
            Column(
                modifier = Modifier.padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp),
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        modifier = Modifier.weight(1f).height(IntrinsicSize.Min),
                        text = OverViewTheme.strings.progressTitle.string(),
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        style = MaterialTheme.typography.titleSmall,
                    )
                    Text(
                        text = (model.progress * 100).roundToInt().toString() + "%",
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        style = MaterialTheme.typography.titleSmall,
                    )
                }
                LinearProgressIndicator(
                    progress = { model.progress },
                    modifier = Modifier.fillMaxWidth().height(10.dp).clip(RoundedCornerShape(100.dp)),
                    color = MaterialTheme.colorScheme.primary,
                    trackColor = MaterialTheme.colorScheme.surfaceContainerHigh,
                    strokeCap = StrokeCap.Square,
                    gapSize = 0.dp,
                    drawStopIndicator = {},
                )
            }
        }
    }
}

@Composable
internal fun NoneCurrentTimeTaskView(
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.surfaceContainerLow,
        shape = MaterialTheme.shapes.large,
    ) {
        Text(
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            text = OverViewTheme.strings.noneTitle.string(),
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.titleSmall,
        )
    }
}
