package com.kuro.mdp.features.overview.presentation.ui.details

import androidx.compose.runtime.Stable
import com.kuro.mdp.features.overview.domain.model.schedules.ScheduleOverView
import com.kuro.mdp.shared.presentation.screenmodel.contract.BaseViewState
import kotlinx.serialization.Serializable

/**
 * Created by: minhthinh.h on 2/18/2025
 *
 * Description:
 */
@Serializable
@Stable
data class DetailsViewState(
    val isLoading: Boolean = true,
    val currentSchedule: ScheduleOverView? = null,
    val schedules: List<ScheduleOverView> = emptyList()
) : BaseViewState