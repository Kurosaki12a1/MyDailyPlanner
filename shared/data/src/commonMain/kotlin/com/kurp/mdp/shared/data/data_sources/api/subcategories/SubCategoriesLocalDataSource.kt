package com.kurp.mdp.shared.data.data_sources.api.subcategories

import com.kuro.mdp.shared.domain.model.categories.MainCategory
import com.kurp.mdp.shared.data.entities.categories.SubCategoryEntity

interface SubCategoriesLocalDataSource {

    suspend fun addSubCategories(subCategories: List<SubCategoryEntity>)
    suspend fun fetchSubCategoriesByType(type: MainCategory): List<SubCategoryEntity>
    suspend fun updateSubCategory(subCategory: SubCategoryEntity)
    suspend fun removeSubCategory(id: Int)
    suspend fun removeAllSubCategories()
}