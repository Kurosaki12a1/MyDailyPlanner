package com.kuro.mdp.features.analytics.domain.models.analytics

import com.kuro.mdp.shared.domain.model.categories.MainCategory
import kotlinx.serialization.Serializable

/**
 * Created by: minhthinh.h on 2/18/2025
 *
 * Description:
 */
@Serializable
data class CategoryAnalytics(
    val mainCategory: MainCategory,
    val duration: Long = 0L,
    val subCategoriesInfo: List<SubCategoryAnalytics> = emptyList(),
)
