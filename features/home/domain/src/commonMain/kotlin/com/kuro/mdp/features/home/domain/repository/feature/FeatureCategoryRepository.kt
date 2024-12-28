package com.kuro.mdp.features.home.domain.repository.feature

interface FeatureCategoryRepository {
    suspend fun fetchMainCategoryId(): Int?
    fun setMainCategoryId(id: Int?)
}
