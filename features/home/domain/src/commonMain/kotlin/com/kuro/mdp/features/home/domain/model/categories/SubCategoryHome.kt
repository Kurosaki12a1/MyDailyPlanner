package com.kuro.mdp.features.home.domain.model.categories

import kotlinx.serialization.Serializable

@Serializable
data class SubCategoryHome(
    val id: Int = 0,
    val name: String? = null,
    val mainCategory: MainCategoryHome = MainCategoryHome(),
    val description: String? = null,
)
