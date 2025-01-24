package com.kuro.mdp.features.overview.domain.model.categories

import com.kuro.mdp.shared.domain.model.categories.DefaultCategoryType
import kotlinx.serialization.Serializable

@Serializable
data class MainCategoryOverView(
    val id: Int = 0,
    val customName: String? = null,
    val defaultType: DefaultCategoryType? = DefaultCategoryType.EMPTY,
)
