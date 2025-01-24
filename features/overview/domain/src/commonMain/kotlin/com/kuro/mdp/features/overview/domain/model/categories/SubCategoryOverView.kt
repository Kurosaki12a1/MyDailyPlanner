package com.kuro.mdp.features.overview.domain.model.categories

import kotlinx.serialization.Serializable

@Serializable
data class SubCategoryOverView(
    val id: Int = 0,
    val name: String? = null,
    val mainCategory: MainCategoryOverView = MainCategoryOverView(),
    val description: String? = null,
)
