package com.kuro.mdp.features.overview.data.repository.over_view

import com.kuro.mdp.features.overview.domain.repository.over_view.OverViewSubCategoriesRepository
import com.kuro.mdp.shared.domain.model.categories.SubCategory
import com.kuro.mdp.shared.domain.repository.SubCategoriesRepository
import com.kuro.mdp.shared.utils.functional.ResultState
import com.kuro.mdp.shared.utils.functional.wrap

/**
 * Created by: minhthinh.h on 12/24/2024
 *
 * Description:
 */
class OverViewSubCategoriesRepositoryImpl(
    private val subCategoriesRepository: SubCategoriesRepository
) : OverViewSubCategoriesRepository {
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