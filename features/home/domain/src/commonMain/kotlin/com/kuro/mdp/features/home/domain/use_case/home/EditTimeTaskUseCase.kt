package com.kuro.mdp.features.home.domain.use_case.home

import com.kuro.mdp.features.home.domain.model.actions.HomeAction
import com.kuro.mdp.shared.domain.model.schedules.TimeTask
import com.kuro.mdp.shared.presentation.navigation.destination.Destination
import com.kuro.mdp.shared.presentation.navigation.navigator.Navigator
import com.kuro.mdp.shared.utils.functional.ResultState
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

/**
 * Created by: minhthinh.h on 2/12/2025
 *
 * Description:
 */
class EditTimeTaskUseCase(
    private val navigator: Navigator
) {

    suspend operator fun invoke(timeTask: TimeTask): ResultState<HomeAction> {
        val editor = Destination.Editor(
            timeTask = timeTask,
            template = null
        )
        navigator.navigateTo(Destination.EditorRoute(Json.encodeToString(editor)))
        return ResultState.Success(HomeAction.Navigate)
    }
}