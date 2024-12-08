package com.kuro.mdp.shared.domain.model.schedules

import com.kuro.mdp.shared.domain.model.categories.MainCategory
import com.kuro.mdp.shared.domain.model.categories.SubCategory
import com.kuro.mdp.shared.utils.functional.LocalDateTimeSerializer
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class UndefinedTask(
    val id: Long = 0L,
    @Serializable(LocalDateTimeSerializer::class) val createdAt: LocalDateTime? = null,
    @Serializable(LocalDateTimeSerializer::class) val deadline: LocalDateTime? = null,
    val mainCategory: MainCategory,
    val subCategory: SubCategory? = null,
    val priority: TaskPriority = TaskPriority.STANDARD,
    val note: String? = null,
)
