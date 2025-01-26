package com.kuro.mdp.features.settings.domain.mapper.categories

import com.kuro.mdp.features.settings.domain.model.categories.CategoriesUi
import com.kuro.mdp.features.settings.domain.model.categories.MainCategoryUi
import com.kuro.mdp.features.settings.domain.model.categories.SubCategoryUi
import com.kuro.mdp.shared.domain.model.categories.Categories
import com.kuro.mdp.shared.domain.model.categories.MainCategory
import com.kuro.mdp.shared.domain.model.categories.SubCategory

/**
 * Created by: minhthinh.h on 1/25/2025
 *
 * Description:
 */
fun MainCategoryUi.mapToDomain() = MainCategory(
    id = id,
    customName = customName,
    default = defaultType,
)

fun MainCategory.mapToUi() = MainCategoryUi(
    id = id,
    customName = customName,
    defaultType = default,
)

fun SubCategoryUi.mapToDomain() = SubCategory(
    id = id,
    name = name,
    mainCategory = mainCategory.mapToDomain(),
    description = description,
)

fun SubCategory.mapToUi() = SubCategoryUi(
    id = id,
    name = name,
    mainCategory = mainCategory.mapToUi(),
    description = description,
)

fun CategoriesUi.mapToDomain() = Categories(
    category = mainCategory.mapToDomain(),
    subCategories = subCategories.map { it.mapToDomain() },
)

fun Categories.mapToUi() = CategoriesUi(
    mainCategory = category.mapToUi(),
    subCategories = subCategories.map { it.mapToUi() },
)