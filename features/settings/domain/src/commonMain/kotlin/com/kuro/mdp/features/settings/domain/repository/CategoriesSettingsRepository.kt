package com.kuro.mdp.features.settings.domain.repository

import com.kuro.mdp.shared.domain.model.categories.Categories
import com.kuro.mdp.shared.presentation.navigation.destination.Destination
import com.kuro.mdp.shared.utils.functional.ResultState

/**
 * Created by: minhthinh.h on 12/26/2024
 *
 * Description:
 */
interface CategoriesSettingsRepository {
    suspend fun removeAllCategories(): ResultState<Unit>
    suspend fun fetchAllCategories(): ResultState<List<Destination.Categories>>
    suspend fun addCategories(categories: List<Categories>): ResultState<Unit>
}