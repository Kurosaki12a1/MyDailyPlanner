package com.kuro.mdp.features.home.data.repository.feature

import com.kuro.mdp.features.home.data.data_source.api.FeatureCategoryLocalDataSource
import com.kuro.mdp.features.home.domain.repository.home.HomeFeatureCategoryRepository

/**
 * Created by: minhthinh.h on 12/23/2024
 *
 * Description:
 */
class HomeFeatureCategoryRepositoryImpl(
    private val localDataSource: FeatureCategoryLocalDataSource
) : HomeFeatureCategoryRepository {
    override suspend fun fetchMainCategoryId(): Int? {
        return localDataSource.fetchMainCategoryId()
    }

    override fun setMainCategoryId(id: Int?) {
        localDataSource.setMainCategoryId(id)
    }
}