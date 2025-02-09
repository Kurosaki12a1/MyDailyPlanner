package com.kuro.mdp.features.home.presentation.ui.home.ui.editor

import com.kuro.mdp.features.home.domain.model.categories.CategoriesHome
import com.kuro.mdp.features.home.domain.model.editor.EditModelHome
import com.kuro.mdp.features.home.domain.model.editor.error.CategoryValidateError
import com.kuro.mdp.features.home.domain.model.editor.error.TimeRangeError
import com.kuro.mdp.features.home.domain.model.schedules.UndefinedTaskHome
import com.kuro.mdp.features.home.domain.model.templates.TemplateHome
import com.kuro.mdp.shared.presentation.screenmodel.contract.BaseViewState
import kotlinx.serialization.Serializable

/**
 * Created by: minhthinh.h on 2/6/2025
 *
 * Description:
 */
@Serializable
data class EditorViewState(
    val editModel: EditModelHome? = null,
    val categories: List<CategoriesHome> = emptyList(),
    val templates: List<TemplateHome>? = null,
    val undefinedTasks: List<UndefinedTaskHome>? = null,
    val isTemplatesSheetOpen: Boolean = false,
    val isUndefinedTasksSheetOpen: Boolean = false,
    val timeRangeValid: TimeRangeError? = null,
    val categoryValid: CategoryValidateError? = null,
) : BaseViewState