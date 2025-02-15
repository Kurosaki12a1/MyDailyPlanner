package com.kuro.mdp.features.home.presentation.ui.home.ui.editor

import androidx.compose.runtime.Stable
import com.kuro.mdp.features.home.domain.model.categories.CategoriesHome
import com.kuro.mdp.features.home.domain.model.editor.EditModelHome
import com.kuro.mdp.features.home.domain.model.editor.error.CategoryValidateError
import com.kuro.mdp.features.home.domain.model.editor.error.TimeRangeError
import com.kuro.mdp.features.home.domain.model.schedules.UndefinedTaskHome
import com.kuro.mdp.features.home.domain.model.templates.TemplateHome
import com.kuro.mdp.shared.presentation.screenmodel.contract.BaseViewState
import com.kuro.mdp.shared.utils.functional.TimeRange
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

/**
 * Created by: minhthinh.h on 2/6/2025
 *
 * Description:
 */
@Serializable
@Stable
data class EditorViewState(
    val editModel: EditModelHome? = null,
    val categories: List<CategoriesHome> = emptyList(),
    val templates: List<TemplateHome>? = null,
    val undefinedTasks: List<UndefinedTaskHome>? = null,
    val isTemplatesSheetOpen: Boolean = false,
    val isUndefinedTasksSheetOpen: Boolean = false,
    val timeRangeValid: TimeRangeError? = null,
    val categoryValid: CategoryValidateError? = null,
    val error: EditorError? = null
) : BaseViewState

@Serializable
@Stable
sealed class EditorError {
    data class ShowError(val throwable: Throwable) : EditorError()
    data class ShowOverLayError(
        val currentTimeRange: TimeRange,
        val startOverlay: LocalDateTime?,
        val endOverlay: LocalDateTime?
    ) : EditorError()
}