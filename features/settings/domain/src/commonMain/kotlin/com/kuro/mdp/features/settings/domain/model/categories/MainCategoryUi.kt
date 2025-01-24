package com.kuro.mdp.features.settings.domain.model.categories

import com.kuro.mdp.shared.domain.model.categories.DefaultCategoryType
import kotlinx.serialization.Serializable

/**
 * Created by: minhthinh.h on 12/26/2024
 *
 * Description:
 */
@Serializable
data class MainCategoryUi(
    val id: Int = 0,
    val customName: String? = null,
    val defaultType: DefaultCategoryType? = DefaultCategoryType.EMPTY,
)
