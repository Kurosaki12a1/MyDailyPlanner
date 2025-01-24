package com.kuro.mdp.features.home.domain.repository.home

interface HomeFeatureCategoryRepository {
    suspend fun fetchMainCategoryId(): Int?
    fun setMainCategoryId(id: Int?)
}
