package com.kurp.mdp.shared.data.data_sources.api.categories

import com.kuro.mdp.shared.domain.model.categories.MainCategory
import com.kurp.mdp.shared.data.entities.categories.MainCategoryDetails
import com.kurp.mdp.shared.data.entities.categories.MainCategoryEntity
import kotlinx.coroutines.flow.Flow

interface CategoriesLocalDataSource {
    suspend fun addMainCategory(mainCategory: MainCategoryEntity): Long
    suspend fun addMainCategories(mainCategories: List<MainCategoryEntity>): List<Long>
    fun fetchMainCategories(): Flow<List<MainCategoryDetails>>
    suspend fun fetchCategoriesByType(mainCategory: MainCategory): MainCategoryDetails?
    suspend fun updateMainCategory(mainCategory: MainCategoryEntity)
    suspend fun removeMainCategory(id: Int)
    suspend fun removeAllCategories()
}
