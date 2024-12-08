package com.kurp.mdp.shared.data.data_sources.impl.categories

import com.kuro.mdp.shared.domain.model.categories.MainCategory
import com.kurp.mdp.shared.data.data_sources.api.categories.CategoriesLocalDataSource
import com.kurp.mdp.shared.data.data_sources.dao.categories.MainCategoriesDao
import com.kurp.mdp.shared.data.entities.categories.MainCategoryDetails
import com.kurp.mdp.shared.data.entities.categories.MainCategoryEntity
import kotlinx.coroutines.flow.Flow

class CategoriesLocalDataSourceImpl(
    private val mainCategoriesDao: MainCategoriesDao
) : CategoriesLocalDataSource {
    override suspend fun addMainCategory(mainCategory: MainCategoryEntity): Long {
        return mainCategoriesDao.addCategory(mainCategory)
    }

    override suspend fun addMainCategories(mainCategories: List<MainCategoryEntity>): List<Long> {
        return mainCategoriesDao.addCategories(mainCategories)
    }

    override fun fetchMainCategories(): Flow<List<MainCategoryDetails>> {
        return mainCategoriesDao.fetchAllCategories()
    }

    override suspend fun fetchCategoriesByType(mainCategory: MainCategory): MainCategoryDetails? {
        return mainCategoriesDao.fetchCategoriesById(mainCategory.id)
    }

    override suspend fun updateMainCategory(mainCategory: MainCategoryEntity) {
        mainCategoriesDao.updateCategory(mainCategory)
    }

    override suspend fun removeMainCategory(id: Int) {
        mainCategoriesDao.removeCategory(id)
    }

    override suspend fun removeAllCategories() {
        mainCategoriesDao.removeAllCategories()
    }
}