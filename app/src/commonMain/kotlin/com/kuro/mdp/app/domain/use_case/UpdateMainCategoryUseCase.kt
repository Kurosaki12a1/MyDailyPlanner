package com.kuro.mdp.app.domain.use_case

import com.kuro.mdp.shared.domain.repository.feature.FeatureCategoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by: minhthinh.h on 2/7/2025
 *
 * Description:
 */
class UpdateMainCategoryUseCase(
    private val featureCategoryRepository: FeatureCategoryRepository,
) {
    operator fun invoke(id: Int?): Flow<Int> = flow {
        if (id != null || featureCategoryRepository.fetchMainCategoryId() != null) {
            val result = id ?: 0
            featureCategoryRepository.setMainCategoryId(result)
            emit(result)
        }
    }
}