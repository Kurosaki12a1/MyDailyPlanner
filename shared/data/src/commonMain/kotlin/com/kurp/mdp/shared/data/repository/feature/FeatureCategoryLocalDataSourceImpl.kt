package com.kurp.mdp.shared.data.repository.feature

import com.kuro.mdp.shared.domain.repository.feature.FeatureCategoryLocalDataSource

class FeatureCategoryLocalDataSourceImpl : FeatureCategoryLocalDataSource {
    private var mainCategoryId: Int? = null

    override suspend fun fetchMainCategoryId(): Int? = mainCategoryId

    override fun setMainCategoryId(id: Int?) {
        this.mainCategoryId = id
    }
}