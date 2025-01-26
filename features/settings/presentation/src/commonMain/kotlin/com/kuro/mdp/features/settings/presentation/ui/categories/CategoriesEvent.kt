package com.kuro.mdp.features.settings.presentation.ui.categories

import com.kuro.mdp.features.settings.domain.model.categories.MainCategoryUi
import com.kuro.mdp.features.settings.domain.model.categories.SubCategoryUi
import com.kuro.mdp.shared.presentation.screenmodel.contract.BaseEvent

/**
 * Created by: minhthinh.h on 1/24/2025
 *
 * Description:
 */
sealed class CategoriesEvent : BaseEvent {
    data object Init : CategoriesEvent()
    data object CheckSelectedCategory : CategoriesEvent()
    data object RestoreDefaultCategories : CategoriesEvent()
    data object ClearFailure : CategoriesEvent()
    data class ShowSubCategoryDialog(val shouldShow: Boolean) : CategoriesEvent()
    data class AddSubCategory(val name: String, val mainCategory: MainCategoryUi) : CategoriesEvent()
    data class AddMainCategory(val name: String) : CategoriesEvent()
    data class ChangeMainCategory(val mainCategory: MainCategoryUi) : CategoriesEvent()
    data class UpdateMainCategory(val mainCategory: MainCategoryUi) : CategoriesEvent()
    data class UpdateSubCategory(val subCategory: SubCategoryUi) : CategoriesEvent()
    data class DeleteMainCategory(val mainCategory: MainCategoryUi) : CategoriesEvent()
    data class DeleteSubCategory(val subCategory: SubCategoryUi) : CategoriesEvent()
}