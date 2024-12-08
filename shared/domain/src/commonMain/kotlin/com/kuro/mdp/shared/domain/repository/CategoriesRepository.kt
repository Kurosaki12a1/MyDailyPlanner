package com.kuro.mdp.shared.domain.repository

import com.kuro.mdp.shared.domain.model.categories.Categories
import com.kuro.mdp.shared.domain.model.categories.MainCategory
import kotlinx.coroutines.flow.Flow


interface CategoriesRepository {
    suspend fun addMainCategories(categories: List<MainCategory>): List<Int>
    fun fetchCategories(): Flow<List<Categories>>
    suspend fun fetchCategoriesByType(mainCategory: MainCategory): Categories?
    suspend fun updateMainCategory(category: MainCategory)
    suspend fun deleteMainCategory(category: MainCategory)
    suspend fun deleteAllCategories()
}
