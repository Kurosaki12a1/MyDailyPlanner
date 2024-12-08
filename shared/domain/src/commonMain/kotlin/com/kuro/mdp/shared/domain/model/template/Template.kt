package com.kuro.mdp.shared.domain.model.template

import com.kuro.mdp.shared.domain.model.categories.MainCategory
import com.kuro.mdp.shared.domain.model.categories.SubCategory
import com.kuro.mdp.shared.domain.model.schedules.TaskPriority
import com.kuro.mdp.shared.domain.model.schedules.TimeTask
import com.kuro.mdp.shared.utils.extensions.compareByHoursAndMinutes
import com.kuro.mdp.shared.utils.functional.LocalDateTimeSerializer
import com.kuro.mdp.shared.utils.functional.Mapper
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class Template(
    val templateId: Int,
    @Serializable(LocalDateTimeSerializer::class)
    val startTime: LocalDateTime,
    @Serializable(LocalDateTimeSerializer::class)
    val endTime: LocalDateTime,
    val category: MainCategory,
    val subCategory: SubCategory? = null,
    val priority: TaskPriority = TaskPriority.STANDARD,
    val isEnableNotification: Boolean = true,
    val isConsiderInStatistics: Boolean = true,
    val repeatEnabled: Boolean = false,
    val repeatTimes: List<RepeatTime> = emptyList(),
) {
    fun <T> map(mapper: Mapper<Template, T>) = mapper.map(this)

    fun checkDateIsRepeat(date: LocalDateTime): Boolean {
        return repeatEnabled && repeatTimes.find { it.checkDateIsRepeat(date) } != null
    }

    fun equalsIsTemplate(timeTask: TimeTask) =
        startTime.compareByHoursAndMinutes(timeTask.timeRange.from) &&
                endTime.compareByHoursAndMinutes(timeTask.timeRange.to) &&
                category.id == timeTask.category.id &&
                subCategory == timeTask.subCategory &&
                priority == timeTask.priority &&
                isEnableNotification == timeTask.isEnableNotification &&
                isConsiderInStatistics == timeTask.isConsiderInStatistics
}
