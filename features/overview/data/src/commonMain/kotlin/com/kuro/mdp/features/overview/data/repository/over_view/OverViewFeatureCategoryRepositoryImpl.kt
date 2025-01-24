package com.kuro.mdp.features.overview.data.repository.over_view

import com.kuro.mdp.features.overview.data.data_source.api.FeatureCategoryLocalDataSource
import com.kuro.mdp.features.overview.domain.repository.over_view.OverViewFeatureCategoryRepository

/**
 * Created by: minhthinh.h on 1/22/2025
 *
 * Description:
 */
class OverViewFeatureCategoryRepositoryImpl(
    private val localDataSource: FeatureCategoryLocalDataSource
) : OverViewFeatureCategoryRepository {
    override suspend fun fetchMainCategoryId(): Int? {
        return localDataSource.fetchMainCategoryId()
    }

    override fun setMainCategoryId(id: Int?) {
        localDataSource.setMainCategoryId(id)
    }
}