package com.kuro.mdp.features.overview.domain.repository.over_view

interface OverViewFeatureCategoryRepository {
    suspend fun fetchMainCategoryId(): Int?
    fun setMainCategoryId(id: Int?)
}
