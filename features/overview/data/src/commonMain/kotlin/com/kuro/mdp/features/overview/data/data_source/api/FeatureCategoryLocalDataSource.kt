package com.kuro.mdp.features.overview.data.data_source.api

/**
 * Created by: minhthinh.h on 1/22/2025
 *
 * Description:
 */
interface FeatureCategoryLocalDataSource {
    suspend fun fetchMainCategoryId(): Int?

    fun setMainCategoryId(id: Int?)
}