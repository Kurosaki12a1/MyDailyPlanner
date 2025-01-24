package com.kuro.mdp.features.settings.domain.model.categories

import kotlinx.serialization.Serializable

/**
 * Created by: minhthinh.h on 12/26/2024
 *
 * Description:
 */
@Serializable
data class SubCategoryUi(
    val id: Int = 0,
    val name: String? = null,
    val mainCategory: MainCategoryUi = MainCategoryUi(),
    val description: String? = null,
)
