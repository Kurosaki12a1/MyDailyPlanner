package com.kurp.mdp.shared.data.mappers.categories

import com.kuro.mdp.shared.domain.model.categories.Categories
import com.kuro.mdp.shared.domain.model.categories.MainCategory
import com.kuro.mdp.shared.domain.model.categories.SubCategory
import com.kurp.mdp.shared.data.entities.categories.MainCategoryDetails
import com.kurp.mdp.shared.data.entities.categories.MainCategoryEntity
import com.kurp.mdp.shared.data.entities.categories.SubCategoryEntity

fun MainCategoryDetails.mapToDomain() = Categories(
    category = mainCategory.mapToDomain(),
    subCategories = subCategories.map { subCategory ->
        subCategory.mapToDomain(mainCategory.mapToDomain())
    },
)

fun MainCategoryEntity.mapToDomain() = MainCategory(
    id = id,
    customName = customName,
    default = defaultType,
)

fun MainCategory.mapToData() = MainCategoryEntity(
    id = id,
    customName = customName,
    defaultType = default,
)

fun SubCategoryEntity.mapToDomain(mainCategory: MainCategory) = SubCategory(
    id = id,
    mainCategory = mainCategory,
    name = subCategoryName.ifEmpty { null },
    description = description,
)

fun SubCategory.mapToData() = SubCategoryEntity(
    id = id,
    mainCategoryId = mainCategory.id,
    subCategoryName = name ?: "",
    description = description,
)
