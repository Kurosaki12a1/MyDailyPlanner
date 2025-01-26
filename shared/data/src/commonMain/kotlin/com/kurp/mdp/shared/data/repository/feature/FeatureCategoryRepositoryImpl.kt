package com.kurp.mdp.shared.data.repository.feature

import com.kuro.mdp.shared.domain.repository.feature.FeatureCategoryLocalDataSource
import com.kuro.mdp.shared.domain.repository.feature.FeatureCategoryRepository

class FeatureCategoryRepositoryImpl(
    private val localDataSource: FeatureCategoryLocalDataSource
) : FeatureCategoryRepository {
    override suspend fun fetchMainCategoryId(): Int? {
        return localDataSource.fetchMainCategoryId()
    }

    override fun setMainCategoryId(id: Int?) {
        localDataSource.setMainCategoryId(id)
    }
}