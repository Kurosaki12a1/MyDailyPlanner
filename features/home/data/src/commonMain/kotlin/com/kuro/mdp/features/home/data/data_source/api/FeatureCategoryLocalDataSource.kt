package com.kuro.mdp.features.home.data.data_source.api

/**
 * Created by: minhthinh.h on 12/23/2024
 *
 * Description:
 */
interface FeatureCategoryLocalDataSource {
    suspend fun fetchMainCategoryId(): Int?

    fun setMainCategoryId(id: Int?)
}