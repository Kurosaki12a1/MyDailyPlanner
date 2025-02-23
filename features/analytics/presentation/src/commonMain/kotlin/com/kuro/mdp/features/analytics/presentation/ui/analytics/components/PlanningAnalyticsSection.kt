package com.kuro.mdp.features.analytics.presentation.ui.analytics.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RichTooltip
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TooltipBox
import androidx.compose.material3.TooltipDefaults
import androidx.compose.material3.rememberTooltipState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.kuro.mdp.features.analytics.domain.mappers.mapToUI
import com.kuro.mdp.features.analytics.domain.models.analytics.PlanningAnalytics
import com.kuro.mdp.features.analytics.domain.models.time_task.TimeTaskAnalytics
import com.kuro.mdp.features.analytics.presentation.theme.AnalyticsTheme
import com.kuro.mdp.features.analytics.presentation.util.fetchName
import com.kuro.mdp.shared.presentation.extensions.scrollText
import com.kuro.mdp.shared.presentation.extensions.string
import com.kuro.mdp.shared.presentation.mappers.mapToIconPainter
import com.kuro.mdp.shared.presentation.theme.AppTheme
import com.kuro.mdp.shared.utils.extensions.isCurrentDay
import com.kuro.mdp.shared.utils.extensions.isCurrentMonth
import com.kuro.mdp.shared.utils.functional.getDateFormatMedium
import com.kuro.mdp.shared.utils.functional.getDateFormatShort
import com.kuro.mdp.shared.utils.functional.getTimeFormatShort
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.format.MonthNames

/**
 * Created by: minhthinh.h on 2/19/2025
 *
 * Description:
 */
@Composable
internal fun PlanningAnalyticsSection(
    modifier: Modifier = Modifier,
    isLoading: Boolean,
    planningAnalytics: Map<Int, List<PlanningAnalytics>>?,
) {
    val listState = rememberLazyListState()
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = AnalyticsTheme.strings.planningAnalyticsTitle.string(),
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.labelLarge,
        )
        Surface(
            modifier = Modifier.padding(horizontal = 16.dp),
            shape = MaterialTheme.shapes.medium,
            color = MaterialTheme.colorScheme.surfaceContainerLow,
        ) {
            AnimatedContent(
                targetState = isLoading,
                label = "Planning analytics",
                transitionSpec = {
                    fadeIn(animationSpec = tween(220, delayMillis = 90)).togetherWith(
                        fadeOut(animationSpec = tween(90)),
                    )
                },
            ) { loading ->
                Row(
                    modifier = Modifier
                        .padding(start = 8.dp, end = 16.dp)
                        .height(220.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    if (!loading && planningAnalytics != null) {
                        WeekDaysColumn(modifier = Modifier.padding(top = 62.dp))
                        LazyRow(
                            contentPadding = PaddingValues(vertical = 8.dp),
                            state = listState,
                            reverseLayout = true,
                            flingBehavior = rememberSnapFlingBehavior(listState),
                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                        ) {
                            items(planningAnalytics.values.toList()) { analytic ->
                                PlanningAnalyticsMonthItem(monthAnalytics = analytic)
                            }
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun PlanningAnalyticsMonthItem(
    modifier: Modifier = Modifier,
    monthAnalytics: List<PlanningAnalytics>,
) {
    val dateFormat = getDateFormatMedium()
    val listMonthsName = AppTheme.months
    val monthFormat = LocalDateTime.Format { monthName(MonthNames(listMonthsName)) }

    val coroutineScope = rememberCoroutineScope()
    var selectedAnalyticItem by remember { mutableStateOf<PlanningAnalytics?>(null) }
    val isLongTitle = remember { derivedStateOf { monthAnalytics.size > 21 } }
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text(
            modifier = Modifier.width(if (isLongTitle.value) 130.dp else 80.dp).scrollText(),
            textAlign = TextAlign.Center,
            text = monthFormat.format(monthAnalytics[0].date),
            color = when (monthAnalytics[0].date.isCurrentMonth()) {
                true -> MaterialTheme.colorScheme.primary
                else -> MaterialTheme.colorScheme.onSurface
            },
            style = MaterialTheme.typography.labelMedium,
        )
        LazyHorizontalGrid(
            modifier = Modifier.width(if (isLongTitle.value) 130.dp else 80.dp),
            rows = GridCells.Fixed(7),
            verticalArrangement = Arrangement.spacedBy(6.dp),
            horizontalArrangement = Arrangement.spacedBy(6.dp),
        ) {
            items(monthAnalytics) { analytic ->
                val tooltipState = rememberTooltipState(isPersistent = true)
                TooltipBox(
                    positionProvider = TooltipDefaults.rememberPlainTooltipPositionProvider(),
                    state = tooltipState,
                    tooltip = {
                        RichTooltip(
                            text = {
                                val createdTimeTasks = selectedAnalyticItem?.timeTasks
                                if (createdTimeTasks != null) {
                                    LazyColumn(
                                        modifier = Modifier.size(200.dp, 160.dp),
                                        verticalArrangement = Arrangement.spacedBy(12.dp),
                                    ) {
                                        item {
                                            Text(
                                                modifier = Modifier.padding(top = 8.dp),
                                                text = dateFormat.format(analytic.date),
                                                style = MaterialTheme.typography.labelMedium,
                                                color = MaterialTheme.colorScheme.primary,
                                            )
                                        }
                                        items(createdTimeTasks) { timeTask ->
                                            TooltipTimeTaskItem(model = timeTask.mapToUI())
                                        }
                                    }
                                }
                            },
                        )
                    },
                ) {
                    PlanningAnalyticsDayItem(
                        enabled = analytic.timeTasks.isNotEmpty(),
                        analytic = analytic,
                        onClick = {
                            selectedAnalyticItem = analytic
                            coroutineScope.launch {
                                if (!tooltipState.isVisible) tooltipState.show() else tooltipState.dismiss()
                            }
                        },
                    )
                }
            }
        }
    }
}

@Composable
internal fun PlanningAnalyticsDayItem(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    analytic: PlanningAnalytics,
    onClick: () -> Unit,
) {
    val color = when (analytic.timeTasks.size) {
        0 -> MaterialTheme.colorScheme.surfaceVariant
        1, 2 -> MaterialTheme.colorScheme.primary.copy(alpha = 0.3F)
        3, 4 -> MaterialTheme.colorScheme.primary.copy(alpha = 0.6F)
        5, 6 -> MaterialTheme.colorScheme.primary.copy(alpha = 0.85F)
        else -> MaterialTheme.colorScheme.primary
    }
    Box(
        modifier = modifier
            .size(20.dp)
            .clip(MaterialTheme.shapes.extraSmall)
            .background(color)
            .clickable(enabled = enabled, onClick = onClick)
            .then(
                other = if (analytic.date.isCurrentDay()) {
                    Modifier.border(
                        1.dp,
                        MaterialTheme.colorScheme.outline,
                        MaterialTheme.shapes.extraSmall
                    )
                } else {
                    Modifier
                },
            ),
        contentAlignment = Alignment.Center,
    ) {
        if (analytic.timeTasks.isNotEmpty()) {
            Text(
                text = analytic.timeTasks.size.toString(),
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                style = MaterialTheme.typography.labelSmall,
            )
        }
    }
}

@Composable
internal fun WeekDaysColumn(
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.width(40.dp), verticalArrangement = Arrangement.spacedBy(32.dp)) {
        ShortWeekDaysTitle(weekDay = AppTheme.strings.mondayTitle.string())
        ShortWeekDaysTitle(weekDay = AppTheme.strings.wednesdayTitle.string())
        ShortWeekDaysTitle(weekDay = AppTheme.strings.fridayTitle.string())
    }
}

@Composable
internal fun ShortWeekDaysTitle(
    modifier: Modifier = Modifier,
    weekDay: String,
) {
    Text(
        modifier = modifier.scrollText().height(20.dp),
        text = weekDay,
        color = MaterialTheme.colorScheme.onSurface,
        style = MaterialTheme.typography.labelMedium,
    )
}

@Composable
internal fun TooltipTimeTaskItem(
    modifier: Modifier = Modifier,
    model: TimeTaskAnalytics,
) {
    val dateFormat = getDateFormatShort()
    val timeFormat = getTimeFormatShort()
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (model.category.defaultType != null) {
            Icon(
                modifier = Modifier.size(18.dp),
                painter = model.category.defaultType!!.mapToIconPainter(),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
            )
        } else {
            Text(
                modifier = Modifier.size(18.dp),
                text = "",
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleMedium,
            )
        }
        Column {
            Text(
                text = when (model.subCategory?.name != null) {
                    true -> "${model.category.fetchName()} - ${model.subCategory?.name}"
                    false -> model.category.fetchName() ?: "-"
                },
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 1,
                style = MaterialTheme.typography.labelMedium,
            )
            Text(
                text = "${timeFormat.format(model.timeRanges.from)} " +
                        "- ${timeFormat.format(model.timeRanges.to)} " +
                        "| ${dateFormat.format(model.timeRanges.from)}",
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 1,
                style = MaterialTheme.typography.labelSmall,
            )
        }
    }
}
