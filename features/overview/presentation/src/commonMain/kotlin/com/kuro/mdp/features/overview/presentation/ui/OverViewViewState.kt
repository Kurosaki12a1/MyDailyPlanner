package com.kuro.mdp.features.overview.presentation.ui

import androidx.compose.runtime.Stable
import com.kuro.mdp.features.overview.domain.model.OverViewError
import com.kuro.mdp.features.overview.domain.model.categories.CategoriesOverView
import com.kuro.mdp.features.overview.domain.model.schedules.ScheduleOverView
import com.kuro.mdp.features.overview.domain.model.schedules.UndefinedTaskOverView
import com.kuro.mdp.shared.presentation.screenmodel.contract.BaseViewState
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

/**
 * Created by: minhthinh.h on 1/21/2025
 *
 * Description:
 */

@Serializable
@Stable
data class OverViewViewState(
    val isLoading: Boolean = true,
    val currentDate: LocalDateTime? = null,
    val currentSchedule: ScheduleOverView? = null,
    val schedules: List<ScheduleOverView> = emptyList(),
    val categories: List<CategoriesOverView> = emptyList(),
    val undefinedTasks: List<UndefinedTaskOverView> = emptyList(),
    val error: OverViewError? = null
) : BaseViewState