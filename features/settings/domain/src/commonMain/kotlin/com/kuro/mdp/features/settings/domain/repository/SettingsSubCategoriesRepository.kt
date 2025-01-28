package com.kuro.mdp.features.settings.domain.repository

import com.kuro.mdp.shared.domain.model.categories.SubCategory
import com.kuro.mdp.shared.utils.functional.ResultState

interface SettingsSubCategoriesRepository {
    suspend fun addSubCategory(subCategory: SubCategory): ResultState<Unit>
    suspend fun updateSubCategory(subCategory: SubCategory): ResultState<Unit>
    suspend fun deleteSubCategory(subCategory: SubCategory): ResultState<Unit>
}