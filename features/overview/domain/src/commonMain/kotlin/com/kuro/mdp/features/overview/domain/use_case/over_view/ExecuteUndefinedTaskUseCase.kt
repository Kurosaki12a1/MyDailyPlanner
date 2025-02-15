package com.kuro.mdp.features.overview.domain.use_case.over_view

import com.kuro.mdp.features.overview.domain.mapper.schedules.mapToDomain
import com.kuro.mdp.features.overview.domain.model.schedules.UndefinedTaskOverView
import com.kuro.mdp.features.overview.domain.model.schedules.convertToTimeTask
import com.kuro.mdp.shared.presentation.navigation.destination.Destination
import com.kuro.mdp.shared.presentation.navigation.navigator.Navigator
import com.kuro.mdp.shared.utils.extensions.startThisDay
import com.kuro.mdp.shared.utils.functional.TimeRange
import com.kuro.mdp.shared.utils.managers.DateManager
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

/**
 * Created by: minhthinh.h on 1/22/2025
 *
 * Description:
 */
class ExecuteUndefinedTaskUseCase(
    private val dateManager: DateManager,
    private val navigator: Navigator
) {
    suspend operator fun invoke(date: LocalDateTime, task: UndefinedTaskOverView) {
        val targetTime = dateManager.setCurrentHMS(date)
        val timeTask = task.convertToTimeTask(date.startThisDay(), TimeRange(targetTime, targetTime))
        val screen = Destination.Editor(
            timeTask = timeTask.mapToDomain(),
            template = null,
            undefinedTaskId = task.id
        )
        navigator.navigateTo(route = Destination.EditorRoute(Json.encodeToString(screen)))
    }
}