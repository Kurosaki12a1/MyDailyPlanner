package com.kuro.mdp.features.home.domain.mapper.categories

import com.kuro.mdp.features.home.domain.model.categories.CategoriesHome
import com.kuro.mdp.features.home.domain.model.categories.MainCategoryHome
import com.kuro.mdp.features.home.domain.model.categories.SubCategoryHome
import com.kuro.mdp.shared.domain.model.categories.Categories
import com.kuro.mdp.shared.domain.model.categories.MainCategory
import com.kuro.mdp.shared.domain.model.categories.SubCategory

/**
 * Created by: minhthinh.h on 12/25/2024
 *
 * Description:
 */
fun MainCategoryHome.mapToDomain() = MainCategory(
    id = id,
    customName = customName,
    default = defaultType,
)

fun MainCategory.mapToUi() = MainCategoryHome(
    id = id,
    customName = customName,
    defaultType = default,
)

fun SubCategoryHome.mapToDomain() = SubCategory(
    id = id,
    name = name,
    mainCategory = mainCategory.mapToDomain(),
    description = description,
)

fun SubCategory.mapToUi() = SubCategoryHome(
    id = id,
    name = name,
    mainCategory = mainCategory.mapToUi(),
    description = description,
)

fun CategoriesHome.mapToDomain() = Categories(
    category = mainCategory.mapToDomain(),
    subCategories = subCategories.map { it.mapToDomain() },
)

fun Categories.mapToUi() = CategoriesHome(
    mainCategory = category.mapToUi(),
    subCategories = subCategories.map { it.mapToUi() },
)
