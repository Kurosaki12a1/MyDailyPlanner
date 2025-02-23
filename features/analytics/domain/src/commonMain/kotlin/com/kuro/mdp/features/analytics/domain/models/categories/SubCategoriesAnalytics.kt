package com.kuro.mdp.features.analytics.domain.models.categories

import kotlinx.serialization.Serializable

/**
 * Created by: minhthinh.h on 2/18/2025
 *
 * Description:
 */
@Serializable
data class SubCategoriesAnalytics(
    val id: Int = 0,
    val name: String? = null,
    val mainCategory: MainCategoriesAnalytics = MainCategoriesAnalytics(),
    val description: String? = null,
)