package com.kuro.mdp.features.home.domain.repository.home

import com.kuro.mdp.shared.domain.model.categories.Categories
import com.kuro.mdp.shared.domain.model.categories.MainCategory
import com.kuro.mdp.shared.utils.functional.ResultState
import kotlinx.coroutines.flow.Flow

/**
 * Created by: minhthinh.h on 12/23/2024
 *
 * Description:
 */
interface HomeCategoryRepository {
    suspend fun fetchCategories(): Flow<ResultState<List<Categories>>>
    suspend fun addMainCategory(mainCategory: MainCategory): ResultState<Int>
    suspend fun updateMainCategory(mainCategory: MainCategory): ResultState<Unit>
    suspend fun deleteMainCategory(mainCategory: MainCategory): ResultState<Unit>
    suspend fun restoreDefaultCategories(): ResultState<Unit>
    suspend fun fetchFeatureMainCategory(): Int?
    fun setFeatureMainCategory(id: Int?)
}