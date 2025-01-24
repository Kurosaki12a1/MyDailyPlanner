package com.kuro.mdp.features.overview.presentation.util

import androidx.compose.runtime.Composable
import com.kuro.mdp.features.overview.domain.model.categories.MainCategoryOverView
import com.kuro.mdp.shared.presentation.mappers.mapToName

/**
 * Created by: minhthinh.h on 1/20/2025
 *
 * Description:
 */
@Composable
fun MainCategoryOverView.fetchName() = when (customName != null && customName != "null") {
    true -> customName
    false -> defaultType?.mapToName()
}