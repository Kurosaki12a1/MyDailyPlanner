package com.kuro.mdp.features.home.data.repository.home

import com.kuro.mdp.features.home.domain.repository.home.HomeCategoryRepository
import com.kuro.mdp.shared.domain.model.categories.SubCategory
import com.kuro.mdp.shared.domain.repository.CategoriesRepository
import com.kuro.mdp.shared.domain.repository.SubCategoriesRepository
import com.kuro.mdp.shared.utils.functional.wrap
import kotlinx.coroutines.flow.first

/**
 * Created by: minhthinh.h on 12/23/2024
 *
 * Description:
 */
class HomeCategoryRepositoryImpl(
    private val categoriesRepository: CategoriesRepository,
    private val subCategoriesRepository: SubCategoriesRepository,
) : HomeCategoryRepository {
    override suspend fun fetchCategories() = wrap {
        categoriesRepository.fetchCategories().first().sortedBy { it.category.id != 0 }
    }

    override suspend fun addSubCategory(subCategory: SubCategory) = wrap {
        subCategoriesRepository.addSubCategories(listOf(subCategory))
    }

    /*override suspend fun addMainCategory(mainCategory: MainCategory): ResultState<Int> = wrap {
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
    }*/

}