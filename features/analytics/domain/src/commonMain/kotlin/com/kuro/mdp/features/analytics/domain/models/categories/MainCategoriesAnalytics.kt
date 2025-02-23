package com.kuro.mdp.features.analytics.domain.models.categories

import com.kuro.mdp.shared.domain.model.categories.DefaultCategoryType
import kotlinx.serialization.Serializable

/**
 * Created by: minhthinh.h on 2/18/2025
 *
 * Description:
 */

@Serializable
data class MainCategoriesAnalytics(
    val id: Int = 0,
    val customName: String? = null,
    val defaultType: DefaultCategoryType? = DefaultCategoryType.EMPTY,
)