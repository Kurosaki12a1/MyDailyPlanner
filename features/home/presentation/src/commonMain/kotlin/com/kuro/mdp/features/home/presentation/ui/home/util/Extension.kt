package com.kuro.mdp.features.home.presentation.ui.home.util

import androidx.compose.runtime.Composable
import com.kuro.mdp.features.home.domain.model.categories.MainCategoryHome
import com.kuro.mdp.shared.presentation.mappers.mapToName

/**
 * Created by: minhthinh.h on 1/21/2025
 *
 * Description:
 */
@Composable
fun MainCategoryHome.fetchName() = when (customName != null && customName != "null") {
    true -> customName
    false -> defaultType?.mapToName()
}