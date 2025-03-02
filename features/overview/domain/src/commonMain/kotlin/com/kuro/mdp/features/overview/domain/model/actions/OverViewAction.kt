package com.kuro.mdp.features.overview.domain.model.actions

import com.kuro.mdp.features.overview.domain.model.categories.CategoriesOverView
import com.kuro.mdp.features.overview.domain.model.schedules.ScheduleOverView
import com.kuro.mdp.features.overview.domain.model.schedules.UndefinedTaskOverView
import com.kuro.mdp.shared.presentation.screenmodel.contract.BaseAction
import kotlinx.datetime.LocalDateTime

/**
 * Created by: minhthinh.h on 2/25/2025
 *
 * Description:
 */
sealed class OverViewAction : BaseAction {
    data object Navigate : OverViewAction()
    data class UpdateLoading(val isLoading: Boolean) : OverViewAction()
    data class UpdateSchedules(val date: LocalDateTime, val schedules: List<ScheduleOverView>) : OverViewAction()
    data class UpdateUndefinedTasks(val tasks: List<UndefinedTaskOverView>) : OverViewAction()
    data class UpdateCategories(val categories: List<CategoriesOverView>) : OverViewAction()
}
