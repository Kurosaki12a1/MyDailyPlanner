package com.kuro.mdp.shared.domain.model.categories

import kotlinx.serialization.Serializable

@Serializable
data class SubCategory(
    val id: Int = 0,
    val mainCategory: MainCategory = MainCategory(),
    val name: String? = null,
    val description: String? = null,
)
