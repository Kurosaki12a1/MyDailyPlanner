package com.kuro.mdp.features.analytics.presentation.util

import androidx.compose.runtime.Composable
import com.kuro.mdp.features.analytics.domain.models.categories.MainCategoriesAnalytics
import com.kuro.mdp.shared.presentation.mappers.mapToName

/**
 * Created by: minhthinh.h on 2/18/2025
 *
 * Description:
 */
@Composable
fun MainCategoriesAnalytics.fetchName(): String? {
    return when (customName != null && customName != "null") {
        true -> customName
        false -> defaultType?.mapToName()
    }
}