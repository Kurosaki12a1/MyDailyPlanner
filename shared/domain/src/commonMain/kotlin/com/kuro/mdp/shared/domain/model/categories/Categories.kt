package com.kuro.mdp.shared.domain.model.categories

import kotlinx.serialization.Serializable

@Serializable
data class Categories(
    val category: MainCategory = MainCategory(),
    val subCategories: List<SubCategory> = emptyList(),
)
