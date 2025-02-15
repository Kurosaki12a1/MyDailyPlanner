package com.kuro.mdp.features.home.domain.repository.home

import com.kuro.mdp.shared.domain.model.categories.Categories
import com.kuro.mdp.shared.domain.model.categories.SubCategory
import com.kuro.mdp.shared.utils.functional.ResultState

/**
 * Created by: minhthinh.h on 12/23/2024
 *
 * Description:
 */
interface HomeCategoryRepository {
    /* suspend fun fetchCategories(): Flow<ResultState<List<Categories>>>
     suspend fun addMainCategory(mainCategory: MainCategory): ResultState<Int>
     suspend fun updateMainCategory(mainCategory: MainCategory): ResultState<Unit>
     suspend fun deleteMainCategory(mainCategory: MainCategory): ResultState<Unit>
     suspend fun restoreDefaultCategories(): ResultState<Unit>
     suspend fun fetchFeatureMainCategory(): Int?
     fun setFeatureMainCategory(id: Int?)*/

    suspend fun fetchCategories(): ResultState<List<Categories>>
    suspend fun addSubCategory(subCategory: SubCategory): ResultState<Unit>
}