package com.kuro.mdp.features.home.data.repository.home

import com.kuro.mdp.features.home.domain.repository.feature.FeatureCategoryRepository
import com.kuro.mdp.features.home.domain.repository.home.HomeCategoryRepository
import com.kuro.mdp.shared.domain.model.categories.Categories
import com.kuro.mdp.shared.domain.model.categories.MainCategory
import com.kuro.mdp.shared.domain.repository.CategoriesRepository
import com.kuro.mdp.shared.utils.functional.ResultState
import com.kuro.mdp.shared.utils.functional.wrap
import com.kuro.mdp.shared.utils.functional.wrapFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

/**
 * Created by: minhthinh.h on 12/23/2024
 *
 * Description:
 */
class HomeCategoryRepositoryImpl(
    private val categoryRepository: CategoriesRepository,
    private val featureCategoryRepository: FeatureCategoryRepository,
) : HomeCategoryRepository {
    override suspend fun fetchCategories(): Flow<ResultState<List<Categories>>> = wrapFlow {
        categoryRepository.fetchCategories().map { categories -> categories.sortedBy { it.category.id != 0 } }
    }

    override suspend fun addMainCategory(mainCategory: MainCategory): ResultState<Int> = wrap {
        categoryRepository.addMainCategories(listOf(mainCategory))[0]
    }

    override suspend fun updateMainCategory(mainCategory: MainCategory): ResultState<Unit> = wrap {
        categoryRepository.updateMainCategory(mainCategory)
    }

    override suspend fun deleteMainCategory(mainCategory: MainCategory): ResultState<Unit> = wrap {
        categoryRepository.deleteMainCategory(mainCategory)
    }

    override suspend fun restoreDefaultCategories(): ResultState<Unit> = wrap {
        val categories = categoryRepository.fetchCategories().first()
        categories.forEach {
            if (it.category.default != null) {
                categoryRepository.updateMainCategory(it.category.copy(customName = null))
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

}