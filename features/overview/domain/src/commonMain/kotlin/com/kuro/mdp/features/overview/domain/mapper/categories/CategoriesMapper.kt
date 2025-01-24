package com.kuro.mdp.features.overview.domain.mapper.categories

import com.kuro.mdp.features.overview.domain.model.categories.CategoriesOverView
import com.kuro.mdp.features.overview.domain.model.categories.MainCategoryOverView
import com.kuro.mdp.features.overview.domain.model.categories.SubCategoryOverView
import com.kuro.mdp.shared.domain.model.categories.Categories
import com.kuro.mdp.shared.domain.model.categories.MainCategory
import com.kuro.mdp.shared.domain.model.categories.SubCategory

/**
 * Created by: minhthinh.h on 12/25/2024
 *
 * Description:
 */
fun MainCategoryOverView.mapToDomain() = MainCategory(
    id = id,
    customName = customName,
    default = defaultType,
)

fun MainCategory.mapToUi() = MainCategoryOverView(
    id = id,
    customName = customName,
    defaultType = default,
)

fun SubCategoryOverView.mapToDomain() = SubCategory(
    id = id,
    name = name,
    mainCategory = mainCategory.mapToDomain(),
    description = description,
)

fun SubCategory.mapToUi() = SubCategoryOverView(
    id = id,
    name = name,
    mainCategory = mainCategory.mapToUi(),
    description = description,
)

fun CategoriesOverView.mapToDomain() = Categories(
    category = mainCategory.mapToDomain(),
    subCategories = subCategories.map { it.mapToDomain() },
)

fun Categories.mapToUi() = CategoriesOverView(
    mainCategory = category.mapToUi(),
    subCategories = subCategories.map { it.mapToUi() },
)
