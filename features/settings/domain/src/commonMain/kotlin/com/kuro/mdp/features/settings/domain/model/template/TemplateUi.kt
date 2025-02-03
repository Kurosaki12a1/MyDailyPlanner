package com.kuro.mdp.features.settings.domain.model.template

import com.kuro.mdp.features.settings.domain.model.categories.MainCategoryUi
import com.kuro.mdp.features.settings.domain.model.categories.SubCategoryUi
import com.kuro.mdp.shared.domain.model.schedules.TaskPriority
import com.kuro.mdp.shared.domain.model.template.RepeatTime
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class TemplateUi(
    val templateId: Int,
    val startTime: LocalDateTime,
    val endTime: LocalDateTime,
    val category: MainCategoryUi,
    val subCategory: SubCategoryUi? = null,
    val priority: TaskPriority = TaskPriority.STANDARD,
    val isEnableNotification: Boolean = true,
    val isConsiderInStatistics: Boolean = true,
    val repeatEnabled: Boolean = false,
    val repeatTimes: List<RepeatTime> = emptyList(),
)