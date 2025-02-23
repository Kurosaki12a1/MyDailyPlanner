package com.kuro.mdp.features.overview.presentation.ui.details

import com.kuro.mdp.features.overview.domain.model.schedules.ScheduleOverView
import com.kuro.mdp.shared.presentation.screenmodel.contract.BaseEvent
import kotlinx.serialization.Serializable

/**
 * Created by: minhthinh.h on 2/18/2025
 *
 * Description:
 */
@Serializable
sealed class DetailsEvent : BaseEvent {
    data object Init : DetailsEvent()
    data object PressBackButton : DetailsEvent()
    data class OpenSchedule(val schedule: ScheduleOverView) : DetailsEvent()
}