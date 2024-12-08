package com.kuro.mdp.shared.domain.repository

import com.kuro.mdp.shared.domain.model.categories.MainCategory
import com.kuro.mdp.shared.domain.model.categories.SubCategory

interface SubCategoriesRepository {
    suspend fun addSubCategories(categories: List<SubCategory>)
    suspend fun fetchSubCategories(type: MainCategory): List<SubCategory>
    suspend fun updateSubCategory(category: SubCategory)
    suspend fun deleteSubCategory(category: SubCategory)
    suspend fun deleteAllSubCategories()
}
