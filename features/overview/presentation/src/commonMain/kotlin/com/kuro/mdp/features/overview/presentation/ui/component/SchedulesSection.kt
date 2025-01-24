package com.kuro.mdp.features.overview.presentation.ui.component

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kuro.mdp.features.overview.domain.model.schedules.ScheduleOverView
import com.kuro.mdp.features.overview.presentation.theme.OverViewTheme
import com.kuro.mdp.shared.presentation.views.PlaceHolderBox
import com.kuro.mdp.shared.utils.extensions.string
import com.kuro.mdp.shared.utils.extensions.toEpochMillis
import com.kuro.mdp.shared.utils.functional.Constants

/**
 * Created by: minhthinh.h on 1/21/2025
 *
 * Description:
 */
@Composable
internal fun SchedulesSection(
    modifier: Modifier = Modifier,
    isLoading: Boolean,
    currentSchedule: ScheduleOverView?,
    schedules: List<ScheduleOverView>,
    onOpenSchedule: (ScheduleOverView) -> Unit,
    onOpenAllSchedules: () -> Unit,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = OverViewTheme.strings.schedulesHeader.string(),
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.titleSmall,
        )
        AnimatedContent(
            targetState = isLoading,
            label = "Schedules",
            transitionSpec = {
                fadeIn(animationSpec = tween(600, delayMillis = 90)).togetherWith(
                    fadeOut(animationSpec = tween(300)),
                )
            },
        ) { loading ->
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                if (!loading && currentSchedule != null) {
                    val currentScheduleIndex = schedules.indexOf(currentSchedule)
                    val gridState = rememberLazyGridState(
                        initialFirstVisibleItemIndex = if (currentScheduleIndex == -1) 0 else currentScheduleIndex,
                    )
                    SchedulesSectionGridView(
                        state = gridState,
                        schedules = schedules,
                        onScheduleClick = onOpenSchedule,
                    )
                    Button(
                        modifier = Modifier.fillMaxWidth().height(40.dp).padding(horizontal = 16.dp),
                        onClick = onOpenAllSchedules,
                        shape = MaterialTheme.shapes.large,
                        contentPadding = PaddingValues(),
                        content = { Text(text = OverViewTheme.strings.showAllSchedulesTitle.string()) },
                    )
                } else {
                    SchedulesSectionGridViewPlaceholder()
                    PlaceHolderBox(
                        modifier = Modifier.fillMaxWidth().height(40.dp).padding(horizontal = 16.dp),
                        shape = MaterialTheme.shapes.large,
                        color = MaterialTheme.colorScheme.primary,
                    )
                }
            }
        }
    }
}

@Composable
internal fun SchedulesSectionGridView(
    modifier: Modifier = Modifier,
    state: LazyGridState = rememberLazyGridState(),
    schedules: List<ScheduleOverView>,
    onScheduleClick: (ScheduleOverView) -> Unit,
    flingBehavior: FlingBehavior = ScrollableDefaults.flingBehavior(),
    userScrollEnabled: Boolean = true,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier.fillMaxWidth().height(262.dp),
        state = state,
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        flingBehavior = flingBehavior,
        userScrollEnabled = userScrollEnabled,
    ) {
        items(schedules, key = { it.date.toEpochMillis() }) { schedule ->
            OverViewScheduleItem(
                model = schedule,
                onClick = { onScheduleClick(schedule) },
            )
        }
    }
}

@Composable
internal fun SchedulesSectionGridViewPlaceholder(
    modifier: Modifier = Modifier,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier.fillMaxWidth().height(262.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        userScrollEnabled = false,
    ) {
        items(Constants.Placeholder.ITEMS) {
            PlaceHolderBox(
                modifier = Modifier.height(125.dp),
                shape = MaterialTheme.shapes.large,
            )
        }
    }
}
