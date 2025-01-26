package com.kuro.mdp.shared.domain.repository.feature

interface FeatureCategoryLocalDataSource {
    suspend fun fetchMainCategoryId(): Int?

    fun setMainCategoryId(id: Int?)
}