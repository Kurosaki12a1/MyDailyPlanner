package com.kuro.mdp.features.settings.domain.model.categories

import kotlinx.serialization.Serializable

/**
 * Created by: minhthinh.h on 12/26/2024
 *
 * Description:
 */
@Serializable
data class CategoriesUi(
    val mainCategory: MainCategoryUi,
    val subCategories: List<SubCategoryUi>,
)
