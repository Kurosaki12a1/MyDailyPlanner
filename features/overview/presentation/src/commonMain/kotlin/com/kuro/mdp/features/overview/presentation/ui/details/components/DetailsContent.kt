package com.kuro.mdp.features.overview.presentation.ui.details.components

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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kuro.mdp.features.overview.domain.model.schedules.ScheduleOverView
import com.kuro.mdp.features.overview.presentation.ui.details.DetailsViewState
import com.kuro.mdp.shared.presentation.views.PlaceHolderBox
import com.kuro.mdp.shared.utils.functional.Constants

/**
 * Created by: minhthinh.h on 2/18/2025
 *
 * Description:
 */
@Composable
internal fun DetailsContent(
    modifier: Modifier = Modifier,
    state: DetailsViewState,
    onOpenSchedule: (ScheduleOverView) -> Unit,
) {
    DetailsSchedulesLazyGrid(
        modifier = modifier,
        isLoading = state.isLoading,
        currentSchedule = state.currentSchedule,
        schedules = state.schedules,
        onOpenSchedule = onOpenSchedule,
    )
}

@Composable
internal fun DetailsSchedulesLazyGrid(
    modifier: Modifier = Modifier,
    isLoading: Boolean,
    currentSchedule: ScheduleOverView?,
    schedules: List<ScheduleOverView>,
    onOpenSchedule: (ScheduleOverView) -> Unit,
) {
    AnimatedContent(
        modifier = modifier,
        targetState = isLoading,
        label = "Details schedules",
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
            } else {
                SchedulesSectionGridViewPlaceholder()
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
        modifier = modifier.fillMaxSize(),
        state = state,
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        flingBehavior = flingBehavior,
        userScrollEnabled = userScrollEnabled,
    ) {
        items(schedules, key = { it.date.date.toEpochDays() }) { schedule ->
            ScheduleItem(
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
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        userScrollEnabled = false,
    ) {
        items(Constants.Placeholder.MANY_ITEMS) {
            PlaceHolderBox(
                modifier = Modifier.height(125.dp),
                shape = MaterialTheme.shapes.large,
            )
        }
    }
}