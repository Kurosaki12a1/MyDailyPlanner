package com.kuro.mdp.features.home.data.repository.home

import com.kuro.mdp.features.home.domain.repository.home.HomeSubCategoriesRepository
import com.kuro.mdp.shared.domain.model.categories.SubCategory
import com.kuro.mdp.shared.domain.repository.SubCategoriesRepository
import com.kuro.mdp.shared.utils.functional.ResultState
import com.kuro.mdp.shared.utils.functional.wrap

/**
 * Created by: minhthinh.h on 12/24/2024
 *
 * Description:
 */
class HomeSubCategoriesRepositoryImpl(
    private val subCategoriesRepository: SubCategoriesRepository
) : HomeSubCategoriesRepository {
    override suspend fun addSubCategory(subCategory: SubCategory): ResultState<Unit> = wrap {
        subCategoriesRepository.addSubCategories(listOf(subCategory))
    }

    override suspend fun updateSubCategory(subCategory: SubCategory): ResultState<Unit> = wrap {
        subCategoriesRepository.updateSubCategory(subCategory)
    }

    override suspend fun deleteSubCategory(subCategory: SubCategory): ResultState<Unit> = wrap {
        subCategoriesRepository.updateSubCategory(subCategory)
    }
}