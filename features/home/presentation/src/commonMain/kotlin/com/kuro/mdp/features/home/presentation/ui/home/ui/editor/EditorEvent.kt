package com.kuro.mdp.features.home.presentation.Home.home.Home.editor

import com.kuro.mdp.features.home.domain.model.categories.MainCategoryHome
import com.kuro.mdp.features.home.domain.model.categories.SubCategoryHome
import com.kuro.mdp.features.home.domain.model.editor.EditParameters
import com.kuro.mdp.features.home.domain.model.schedules.UndefinedTaskHome
import com.kuro.mdp.features.home.domain.model.templates.TemplateHome
import com.kuro.mdp.shared.presentation.screenmodel.contract.BaseEvent
import com.kuro.mdp.shared.utils.functional.TimeRange
import kotlinx.serialization.Serializable

/**
 * Created by: minhthinh.h on 2/6/2025
 *
 * Description:
 */
@Serializable
sealed class EditorEvent : BaseEvent {
    data object Init : EditorEvent()
    data object CreateTemplate : EditorEvent()
    data object ClearFailure : EditorEvent()
    data class ApplyTemplate(val template: TemplateHome) : EditorEvent()
    data class ApplyUndefinedTask(val task: UndefinedTaskHome) : EditorEvent()
    data class ChangeTime(val timeRange: TimeRange) : EditorEvent()
    data class ChangeCategories(val category: MainCategoryHome, val subCategory: SubCategoryHome?) : EditorEvent()
    data class ChangeNote(val note: String?) : EditorEvent()
    data class ChangeParameters(val parameters: EditParameters) : EditorEvent()
    data class AddSubCategory(val name: String) : EditorEvent()
    data class NavigateToCategoryEditor(val category: MainCategoryHome) : EditorEvent()
    data class NavigateToSubCategoryEditor(val category: SubCategoryHome) : EditorEvent()
    data class OpenUndefinedTasksSheet(val shouldOpen: Boolean) : EditorEvent()
    data class OpenTemplatesSheet(val shouldOpen: Boolean) : EditorEvent()
    data object PressDeleteButton : EditorEvent()
    data object PressSaveButton : EditorEvent()
    data object PressControlTemplateButton : EditorEvent()
    data object PressBackButton : EditorEvent()
}