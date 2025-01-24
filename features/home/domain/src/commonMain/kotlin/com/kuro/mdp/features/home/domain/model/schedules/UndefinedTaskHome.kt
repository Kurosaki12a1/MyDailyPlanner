package com.kuro.mdp.features.home.domain.model.schedules

import com.kuro.mdp.features.home.domain.model.categories.MainCategoryHome
import com.kuro.mdp.features.home.domain.model.categories.SubCategoryHome
import com.kuro.mdp.shared.domain.model.schedules.TaskPriority
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

/**
 * Created by: minhthinh.h on 12/19/2024
 *
 * Description:
 */
@Serializable
data class UndefinedTaskHome(
    val id: Long = 0L,
    val createdAt: LocalDateTime? = null,
    val deadline: LocalDateTime? = null,
    val mainCategory: MainCategoryHome,
    val subCategory: SubCategoryHome? = null,
    val priority: TaskPriority = TaskPriority.STANDARD,
    val note: String? = null,
)