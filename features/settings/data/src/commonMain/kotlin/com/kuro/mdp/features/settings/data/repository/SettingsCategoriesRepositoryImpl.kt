package com.kuro.mdp.features.settings.data.repository

import com.kuro.mdp.features.settings.domain.repository.SettingsCategoriesRepository
import com.kuro.mdp.shared.domain.model.categories.Categories
import com.kuro.mdp.shared.domain.model.categories.MainCategory
import com.kuro.mdp.shared.domain.repository.CategoriesRepository
import com.kuro.mdp.shared.domain.repository.SubCategoriesRepository
import com.kuro.mdp.shared.domain.repository.feature.FeatureCategoryRepository
import com.kuro.mdp.shared.utils.extensions.extractAllItem
import com.kuro.mdp.shared.utils.functional.ResultState
import com.kuro.mdp.shared.utils.functional.wrap
import com.kuro.mdp.shared.utils.functional.wrapFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class SettingsCategoriesRepositoryImpl(
    private val categoriesRepository: CategoriesRepository,
    private val subCategoriesRepository: SubCategoriesRepository,
    private val featureCategoryRepository: FeatureCategoryRepository
) : SettingsCategoriesRepository {
    override suspend fun fetchCategories(): Flow<ResultState<List<Categories>>> = wrapFlow {
        categoriesRepository.fetchCategories().map { categories ->
            categories.sortedBy { it.category.id != 0 }
        }
    }

    override suspend fun addMainCategory(mainCategory: MainCategory): ResultState<Int> = wrap {
        categoriesRepository.addMainCategories(listOf(mainCategory))[0]
    }

    override suspend fun updateMainCategory(mainCategory: MainCategory): ResultState<Unit> = wrap {
        categoriesRepository.updateMainCategory(mainCategory)
    }

    override suspend fun deleteMainCategory(mainCategory: MainCategory): ResultState<Unit> = wrap {
        categoriesRepository.deleteMainCategory(mainCategory)
    }

    override suspend fun restoreDefaultCategories(): ResultState<Unit> = wrap {
        val categories = categoriesRepository.fetchCategories().first()
        categories.forEach {
            if (it.category.default != null) {
                categoriesRepository.updateMainCategory(it.category.copy(customName = null))
            }
        }
    }

    override suspend fun fetchFeatureMainCategory(): Int? {
        val mainCategory = featureCategoryRepository.fetchMainCategoryId()
        featureCategoryRepository.setMainCategoryId(null)
        return mainCategory
    }

    override fun setFeatureMainCategory(id: Int?) {
        featureCategoryRepository.setMainCategoryId(id)
    }

    override suspend fun removeAllCategories(): ResultState<Unit> = wrap {
        categoriesRepository.deleteAllCategories()
        subCategoriesRepository.deleteAllSubCategories()
    }

    override suspend fun fetchAllCategories(): ResultState<List<Categories>> = wrap {
        categoriesRepository.fetchCategories().first()
    }

    override suspend fun addCategories(categories: List<Categories>): ResultState<Unit> = wrap {
        val subCategories = categories.map { it.subCategories }.extractAllItem()
        categoriesRepository.addMainCategories(categories.map { it.category })
        subCategoriesRepository.addSubCategories(subCategories)
    }
}