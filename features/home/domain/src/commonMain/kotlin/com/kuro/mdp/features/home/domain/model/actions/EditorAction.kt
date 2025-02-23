package com.kuro.mdp.features.home.domain.model.actions

import com.kuro.mdp.features.home.domain.model.categories.CategoriesHome
import com.kuro.mdp.features.home.domain.model.editor.EditModelHome
import com.kuro.mdp.features.home.domain.model.editor.error.CategoryValidateError
import com.kuro.mdp.features.home.domain.model.editor.error.TimeRangeError
import com.kuro.mdp.features.home.domain.model.schedules.UndefinedTaskHome
import com.kuro.mdp.features.home.domain.model.templates.TemplateHome
import com.kuro.mdp.shared.presentation.screenmodel.contract.BaseAction
import kotlinx.serialization.Serializable

/**
 * Created by: minhthinh.h on 2/6/2025
 *
 * Description:
 */

@Serializable
sealed class EditorAction : BaseAction {
    data object Navigate : EditorAction()
    data class SetUp(val editModel: EditModelHome, val categories: List<CategoriesHome>) : EditorAction()
    data class UpdateUndefinedTasks(val tasks: List<UndefinedTaskHome>) : EditorAction()
    data class UpdateCategories(val categories: List<CategoriesHome>) : EditorAction()
    data class UpdateTemplates(val templates: List<TemplateHome>) : EditorAction()
    data class UpdateTemplateId(val templateId: Int?) : EditorAction()
    data class UpdateEditModel(val editModel: EditModelHome?) : EditorAction()
    data class SetValidError(val timeRange: TimeRangeError?, val category: CategoryValidateError?) : EditorAction()
}
