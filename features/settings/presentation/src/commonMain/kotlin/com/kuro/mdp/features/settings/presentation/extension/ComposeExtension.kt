package com.kuro.mdp.features.settings.presentation.extension

import androidx.compose.runtime.Composable
import com.kuro.mdp.features.settings.domain.model.categories.MainCategoryUi
import com.kuro.mdp.shared.presentation.mappers.mapToName

/**
 * Created by: minhthinh.h on 1/24/2025
 *
 * Description:
 */
@Composable
fun MainCategoryUi.fetchName(): String? = when (customName != null && customName != "null") {
    true -> customName
    false -> defaultType?.mapToName()
}