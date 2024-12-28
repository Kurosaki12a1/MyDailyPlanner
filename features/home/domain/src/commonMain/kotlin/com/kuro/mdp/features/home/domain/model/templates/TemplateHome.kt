package com.kuro.mdp.features.home.domain.model.templates

import com.kuro.mdp.features.home.domain.model.categories.MainCategoryHome
import com.kuro.mdp.features.home.domain.model.categories.SubCategoryHome
import com.kuro.mdp.shared.domain.model.schedules.TaskPriority
import com.kuro.mdp.shared.domain.model.template.RepeatTime
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

/**
 * Created by: minhthinh.h on 12/19/2024
 *
 * Description:
 */
@Serializable
data class TemplateHome(
    val templateId: Int,
    val startTime: LocalDateTime,
    val endTime: LocalDateTime,
    val category: MainCategoryHome,
    val subCategory: SubCategoryHome? = null,
    val priority: TaskPriority = TaskPriority.STANDARD,
    val isEnableNotification: Boolean = true,
    val isConsiderInStatistics: Boolean = true,
    val repeatEnabled: Boolean = false,
    val repeatTimes: List<RepeatTime> = emptyList(),
)
