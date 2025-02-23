package com.kuro.mdp.features.home.domain.use_case.home

import com.kuro.mdp.features.home.domain.model.actions.HomeAction
import com.kuro.mdp.shared.domain.model.categories.MainCategory
import com.kuro.mdp.shared.domain.model.schedules.TimeTask
import com.kuro.mdp.shared.presentation.navigation.destination.Destination
import com.kuro.mdp.shared.presentation.navigation.navigator.Navigator
import com.kuro.mdp.shared.utils.extensions.getLocalDateTimeNow
import com.kuro.mdp.shared.utils.functional.ResultState
import com.kuro.mdp.shared.utils.functional.TimeRange
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

/**
 * Created by: minhthinh.h on 2/12/2025
 *
 * Description:
 */
class AddTimeTaskUseCase(
    private val navigator: Navigator
) {

    suspend operator fun invoke(
        date: LocalDateTime?,
        startTime: LocalDateTime,
        endTime: LocalDateTime
    ): ResultState<HomeAction> {
        val timeTask = TimeTask(
            date = date ?: getLocalDateTimeNow(),
            category = MainCategory(),
            createdAt = getLocalDateTimeNow(),
            timeRange = TimeRange(startTime, endTime)
        )
        val editor = Destination.Editor(
            timeTask = timeTask,
            template = null
        )
        navigator.navigateTo(Destination.EditorRoute(Json.encodeToString(editor)))
        return ResultState.Success(HomeAction.Navigate)
    }
}