package com.kuro.mdp.features.home.data.data_source.impl

import com.kuro.mdp.features.home.data.data_source.api.FeatureCategoryLocalDataSource

/**
 * Created by: minhthinh.h on 12/23/2024
 *
 * Description:
 */
class FeatureCategoryLocalDataSourceImpl : FeatureCategoryLocalDataSource {
    private var mainCategoryId: Int? = null

    override suspend fun fetchMainCategoryId(): Int? = mainCategoryId

    override fun setMainCategoryId(id: Int?) {
        this.mainCategoryId = id
    }
}