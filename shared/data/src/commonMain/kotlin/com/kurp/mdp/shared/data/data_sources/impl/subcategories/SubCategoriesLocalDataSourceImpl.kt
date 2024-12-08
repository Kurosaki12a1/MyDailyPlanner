package com.kurp.mdp.shared.data.data_sources.impl.subcategories

import com.kuro.mdp.shared.domain.model.categories.MainCategory
import com.kurp.mdp.shared.data.data_sources.api.subcategories.SubCategoriesLocalDataSource
import com.kurp.mdp.shared.data.data_sources.dao.subcategories.SubCategoriesDao
import com.kurp.mdp.shared.data.entities.categories.SubCategoryEntity

class SubCategoriesLocalDataSourceImpl(
    private val subCategoriesDao: SubCategoriesDao,
) : SubCategoriesLocalDataSource {

    override suspend fun addSubCategories(subCategories: List<SubCategoryEntity>) {
        subCategoriesDao.addSubCategories(subCategories)
    }

    override suspend fun fetchSubCategoriesByType(type: MainCategory): List<SubCategoryEntity> {
        return subCategoriesDao.fetchSubCategoriesByTypeId(type.id)
    }

    override suspend fun updateSubCategory(subCategory: SubCategoryEntity) {
        subCategoriesDao.updateSubCategory(subCategory)
    }

    override suspend fun removeSubCategory(id: Int) {
        subCategoriesDao.removeSubCategory(id)
    }

    override suspend fun removeAllSubCategories() {
        subCategoriesDao.removeAllSubCategories()
    }
}