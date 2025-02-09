package com.kuro.mdp.features.home.presentation.ui.home.ui.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.kuro.mdp.features.home.domain.model.schedules.TimeTaskHome
import com.kuro.mdp.features.home.presentation.ui.home.ui.home.HomeViewState
import com.kuro.mdp.shared.domain.model.settings.ViewToggleStatus
import kotlinx.datetime.LocalDateTime

/**
 * Created by: minhthinh.h on 12/19/2024
 *
 * Description:
 */
@Composable
internal fun HomeContent(
    state: HomeViewState,
    modifier: Modifier = Modifier,
    onChangeDate: (LocalDateTime) -> Unit,
    onCreateSchedule: () -> Unit,
    onTimeTaskEdit: (TimeTaskHome) -> Unit,
    onTaskDoneChange: (TimeTaskHome) -> Unit,
    onTimeTaskAdd: (startTime: LocalDateTime, endTime: LocalDateTime) -> Unit,
    onTimeTaskIncrease: (TimeTaskHome) -> Unit,
    onTimeTaskReduce: (TimeTaskHome) -> Unit,
    onChangeToggleStatus: (ViewToggleStatus) -> Unit,
) {
    Column(modifier = modifier.fillMaxSize()) {
        DateChooserSection(
            visible = state.currentDate != null,
            currentDate = state.currentDate,
            toggleState = state.taskViewStatus,
            onChangeDate = onChangeDate,
            onChangeToggleStatus = onChangeToggleStatus,
        )
        TimeTasksSection(
            currentDate = state.currentDate,
            dateStatus = state.dateStatus,
            timeTasks = state.timeTasks,
            timeTaskViewStatus = state.taskViewStatus,
            onCreateSchedule = onCreateSchedule,
            onTimeTaskEdit = onTimeTaskEdit,
            onTaskDoneChange = onTaskDoneChange,
            onTimeTaskAdd = onTimeTaskAdd,
            onTimeTaskIncrease = onTimeTaskIncrease,
            onTimeTaskReduce = onTimeTaskReduce,
        )
    }
}
