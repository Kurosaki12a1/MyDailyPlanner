package com.kuro.mdp.features.overview.presentation.ui.overview.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kuro.mdp.features.overview.domain.model.schedules.UndefinedTaskOverView
import com.kuro.mdp.features.overview.presentation.ui.overview.OverViewViewState
import com.kuro.mdp.shared.utils.extensions.getLocalDateTimeNow
import com.kuro.mdp.shared.utils.extensions.isIncludeTime
import kotlinx.datetime.LocalDateTime

/**
 * Created by: minhthinh.h on 1/21/2025
 *
 * Description:
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun OverviewContent(
    modifier: Modifier = Modifier,
    state: OverViewViewState,
    onRefresh: () -> Unit,
    onOpenSchedule: (LocalDateTime?) -> Unit,
    onOpenAllSchedules: () -> Unit,
    onAddOrUpdateTask: (UndefinedTaskOverView) -> Unit,
    onDeleteTask: (UndefinedTaskOverView) -> Unit,
    onExecuteTask: (LocalDateTime, UndefinedTaskOverView) -> Unit,
) {
    val scrollState = rememberScrollState()
    val refreshState = rememberPullToRefreshState()

    PullToRefreshBox(
        modifier = modifier,
        state = refreshState,
        isRefreshing = state.isLoading,
        onRefresh = onRefresh,
        content = {
            Column(
                modifier = Modifier.verticalScroll(state = scrollState, enabled = !state.isLoading),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                SchedulesSection(
                    isLoading = state.isLoading,
                    currentSchedule = state.currentSchedule,
                    schedules = state.schedules,
                    onOpenSchedule = { onOpenSchedule(it.date) },
                    onOpenAllSchedules = onOpenAllSchedules,
                )
                CurrentTimeTaskSection(
                    isLoading = state.isLoading,
                    task = state.currentSchedule?.timeTasks?.find { it.timeToTimeRange().isIncludeTime(getLocalDateTimeNow()) },
                    onOpenTask = { onOpenSchedule(null) },
                )
                UndefinedTaskSection(
                    isLoading = state.isLoading,
                    categories = state.categories,
                    tasks = state.undefinedTasks,
                    onAddOrUpdateTask = onAddOrUpdateTask,
                    onDeleteTask = onDeleteTask,
                    onExecuteTask = onExecuteTask,
                )
            }

        }
    )
}