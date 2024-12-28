package com.kurp.mdp.shared.data.repository

import com.kuro.mdp.shared.domain.model.categories.MainCategory
import com.kuro.mdp.shared.domain.model.categories.SubCategory
import com.kuro.mdp.shared.domain.repository.SubCategoriesRepository
import com.kurp.mdp.shared.data.data_sources.api.subcategories.SubCategoriesLocalDataSource
import com.kurp.mdp.shared.data.mappers.categories.mapToData
import com.kurp.mdp.shared.data.mappers.categories.mapToDomain

/**
 * Created by: minhthinh.h on 12/23/2024
 *
 * Description:
 */
class SubCategoriesRepositoryImpl(
    private val localDataSource: SubCategoriesLocalDataSource,
) : SubCategoriesRepository {

    override suspend fun addSubCategories(categories: List<SubCategory>) {
        localDataSource.addSubCategories(categories.map { it.mapToData() })
    }

    override suspend fun fetchSubCategories(type: MainCategory): List<SubCategory> {
        return localDataSource.fetchSubCategoriesByType(type).map { subCategory ->
            subCategory.mapToDomain(type)
        }
    }

    override suspend fun updateSubCategory(category: SubCategory) {
        localDataSource.updateSubCategory(category.mapToData())
    }

    override suspend fun deleteSubCategory(category: SubCategory) {
        localDataSource.removeSubCategory(category.id)
    }

    override suspend fun deleteAllSubCategories() {
        localDataSource.removeAllSubCategories()
    }
}
