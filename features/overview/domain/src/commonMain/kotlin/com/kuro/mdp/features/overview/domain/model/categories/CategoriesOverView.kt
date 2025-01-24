package com.kuro.mdp.features.overview.domain.model.categories

import kotlinx.serialization.Serializable

@Serializable
data class CategoriesOverView(
    val mainCategory: MainCategoryOverView,
    val subCategories: List<SubCategoryOverView>,
)
