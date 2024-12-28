package com.kurp.mdp.shared.data.repository

import com.kuro.mdp.shared.domain.model.categories.Categories
import com.kuro.mdp.shared.domain.model.categories.MainCategory
import com.kuro.mdp.shared.domain.repository.CategoriesRepository
import com.kurp.mdp.shared.data.data_sources.api.categories.CategoriesLocalDataSource
import com.kurp.mdp.shared.data.mappers.categories.mapToData
import com.kurp.mdp.shared.data.mappers.categories.mapToDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CategoriesRepositoryImpl(
    private val localDataSource: CategoriesLocalDataSource
) : CategoriesRepository {
    override suspend fun addMainCategories(categories: List<MainCategory>): List<Int> {
        return localDataSource.addMainCategories(categories.map { it.mapToData() }).map { it.toInt() }
    }

    override fun fetchCategories(): Flow<List<Categories>> {
        return localDataSource.fetchMainCategories().map { categories ->
            categories.map { it.mapToDomain() }
        }
    }

    override suspend fun fetchCategoriesByType(mainCategory: MainCategory): Categories? {
        return localDataSource.fetchCategoriesByType(mainCategory)?.mapToDomain()
    }

    override suspend fun updateMainCategory(category: MainCategory) {
        localDataSource.updateMainCategory(category.mapToData())
    }

    override suspend fun deleteMainCategory(category: MainCategory) {
        localDataSource.removeMainCategory(category.id)
    }

    override suspend fun deleteAllCategories() {
        localDataSource.removeAllCategories()
    }
}