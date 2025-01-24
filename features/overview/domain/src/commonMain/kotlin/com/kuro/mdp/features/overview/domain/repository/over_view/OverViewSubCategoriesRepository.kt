package com.kuro.mdp.features.overview.domain.repository.over_view

import com.kuro.mdp.shared.domain.model.categories.SubCategory
import com.kuro.mdp.shared.utils.functional.ResultState

/**
 * Created by: minhthinh.h on 12/24/2024
 *
 * Description:
 */
interface OverViewSubCategoriesRepository {
    suspend fun addSubCategory(subCategory: SubCategory): ResultState<Unit>
    suspend fun updateSubCategory(subCategory: SubCategory): ResultState<Unit>
    suspend fun deleteSubCategory(subCategory: SubCategory): ResultState<Unit>
}