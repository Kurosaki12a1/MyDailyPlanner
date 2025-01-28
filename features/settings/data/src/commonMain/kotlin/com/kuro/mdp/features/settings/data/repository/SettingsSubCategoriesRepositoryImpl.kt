package com.kuro.mdp.features.settings.data.repository

import com.kuro.mdp.features.settings.domain.repository.SettingsSubCategoriesRepository
import com.kuro.mdp.shared.domain.model.categories.SubCategory
import com.kuro.mdp.shared.domain.repository.SubCategoriesRepository
import com.kuro.mdp.shared.utils.functional.ResultState
import com.kuro.mdp.shared.utils.functional.wrap

class SettingsSubCategoriesRepositoryImpl(
    private val subCategoryRepository: SubCategoriesRepository
) : SettingsSubCategoriesRepository {
    override suspend fun addSubCategory(subCategory: SubCategory): ResultState<Unit> = wrap {
        subCategoryRepository.addSubCategories(listOf(subCategory))
    }

    override suspend fun updateSubCategory(subCategory: SubCategory): ResultState<Unit> = wrap {
        subCategoryRepository.updateSubCategory(subCategory)
    }

    override suspend fun deleteSubCategory(subCategory: SubCategory): ResultState<Unit> = wrap {
        subCategoryRepository.deleteSubCategory(subCategory)
    }
}