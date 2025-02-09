package com.kuro.mdp.features.home.domain.model.editor

import com.kuro.mdp.features.home.domain.model.categories.MainCategoryHome
import com.kuro.mdp.features.home.domain.model.categories.SubCategoryHome
import com.kuro.mdp.shared.domain.model.template.RepeatTime
import com.kuro.mdp.shared.utils.extensions.duration
import com.kuro.mdp.shared.utils.functional.TimeRange
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable


@Serializable
data class EditModelHome(
    val key: Long = 0L,
    val date: LocalDateTime,
    val timeRange: TimeRange,
    val createdAt: LocalDateTime? = null,
    val duration: Long = duration(timeRange.from, timeRange.to),
    val mainCategory: MainCategoryHome = MainCategoryHome(),
    val subCategory: SubCategoryHome? = null,
    val isCompleted: Boolean = true,
    val parameters: EditParameters = EditParameters(),
    val repeatEnabled: Boolean = false,
    val templateId: Int? = null,
    val undefinedTaskId: Long? = null,
    val repeatTimes: List<RepeatTime> = emptyList(),
    val note: String? = null,
) {
    fun checkDateIsRepeat(): Boolean {
        return repeatEnabled && repeatTimes.find { it.checkDateIsRepeat(date) } != null
    }
}
