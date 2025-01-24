package com.kuro.mdp.features.overview.data.data_source.impl

import com.kuro.mdp.features.overview.data.data_source.api.FeatureCategoryLocalDataSource

/**
 * Created by: minhthinh.h on 1/22/2025
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