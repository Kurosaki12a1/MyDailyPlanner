package com.kuro.mdp.app.presentation.ui

import com.kuro.mdp.shared.presentation.screenmodel.contract.BaseEvent
import kotlinx.serialization.Serializable

/**
 * Created by: minhthinh.h on 1/3/2025
 *
 * Description:
 */
@Serializable
sealed class MainEvent : BaseEvent {
    data object Init : MainEvent()
    data class UpdateScheduleDate(val date: Long?) : MainEvent()
    data class UpdateMainCategoryId(val id: Int?) : MainEvent()
}