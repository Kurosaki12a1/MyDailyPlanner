package com.kuro.mdp.features.home.domain.model.categories

import kotlinx.serialization.Serializable

@Serializable
data class CategoriesHome(
    val mainCategory: MainCategoryHome,
    val subCategories: List<SubCategoryHome>,
)
