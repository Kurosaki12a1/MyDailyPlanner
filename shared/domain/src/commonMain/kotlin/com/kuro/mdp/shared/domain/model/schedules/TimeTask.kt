package com.kuro.mdp.shared.domain.model.schedules

import com.kuro.mdp.shared.domain.model.categories.MainCategory
import com.kuro.mdp.shared.domain.model.categories.SubCategory
import com.kuro.mdp.shared.utils.functional.LocalDateTimeSerializer
import com.kuro.mdp.shared.utils.functional.Mapper
import com.kuro.mdp.shared.utils.functional.TimeRange
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class TimeTask(
    val key: Long = 0L,
    @Serializable(LocalDateTimeSerializer::class)
    val date: LocalDateTime,
    @Serializable(LocalDateTimeSerializer::class)
    val createdAt: LocalDateTime? = null,
    val timeRange: TimeRange,
    val category: MainCategory = MainCategory(),
    val subCategory: SubCategory? = null,
    val isCompleted: Boolean = true,
    val priority: TaskPriority = TaskPriority.STANDARD,
    val isEnableNotification: Boolean = true,
    val taskNotifications: TaskNotifications = TaskNotifications(),
    val isConsiderInStatistics: Boolean = true,
    val note: String? = null,
) {
    fun <T> map(mapper: Mapper<TimeTask, T>) = mapper.map(this)
}
