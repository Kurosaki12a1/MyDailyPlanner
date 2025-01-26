package com.kuro.mdp.features.settings.domain.repository

import com.kuro.mdp.shared.domain.model.categories.Categories
import com.kuro.mdp.shared.domain.model.categories.MainCategory
import com.kuro.mdp.shared.utils.functional.ResultState
import kotlinx.coroutines.flow.Flow

/**
 * Created by: minhthinh.h on 12/26/2024
 *
 * Description:
 */
interface SettingsCategoriesRepository {
    suspend fun fetchCategories(): Flow<ResultState<List<Categories>>>
    suspend fun addMainCategory(mainCategory: MainCategory): ResultState<Int>
    suspend fun updateMainCategory(mainCategory: MainCategory): ResultState<Unit>
    suspend fun deleteMainCategory(mainCategory: MainCategory): ResultState<Unit>
    suspend fun restoreDefaultCategories(): ResultState<Unit>

    suspend fun fetchFeatureMainCategory(): Int?
    fun setFeatureMainCategory(id: Int?)

    suspend fun removeAllCategories(): ResultState<Unit>
    suspend fun fetchAllCategories(): ResultState<List<Categories>>
    suspend fun addCategories(categories: List<Categories>): ResultState<Unit>
}