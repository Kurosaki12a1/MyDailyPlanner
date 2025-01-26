package com.kuro.mdp.shared.domain.repository.feature

interface FeatureCategoryRepository {
    suspend fun fetchMainCategoryId(): Int?

    fun setMainCategoryId(id: Int?)
}
