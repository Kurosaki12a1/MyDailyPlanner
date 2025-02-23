package com.kuro.mdp.features.analytics.domain.mappers

import com.kuro.mdp.features.analytics.domain.models.categories.MainCategoriesAnalytics
import com.kuro.mdp.features.analytics.domain.models.categories.SubCategoriesAnalytics
import com.kuro.mdp.shared.domain.model.categories.MainCategory
import com.kuro.mdp.shared.domain.model.categories.SubCategory

/**
 * Created by: minhthinh.h on 2/19/2025
 *
 * Description:
 */
fun MainCategoriesAnalytics.mapToDomain() = MainCategory(
    id = id,
    customName = customName,
    default = defaultType,
)

fun MainCategory.mapToUi() = MainCategoriesAnalytics(
    id = id,
    customName = customName,
    defaultType = default,
)

fun SubCategoriesAnalytics.mapToDomain() = SubCategory(
    id = id,
    name = name,
    mainCategory = mainCategory.mapToDomain(),
    description = description,
)

fun SubCategory.mapToUi() = SubCategoriesAnalytics(
    id = id,
    name = name,
    mainCategory = mainCategory.mapToUi(),
    description = description,
)
