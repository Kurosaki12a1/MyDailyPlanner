package com.kuro.mdp.features.home.presentation.ui.home.util

import androidx.compose.runtime.Composable
import com.kuro.mdp.features.home.domain.model.categories.MainCategoryHome
import com.kuro.mdp.shared.presentation.mappers.mapToName
import com.kuro.mdp.shared.presentation.mappers.mapToString
import com.kuro.mdp.shared.presentation.theme.resources.AppStrings
import org.jetbrains.compose.resources.getString

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

suspend fun MainCategoryHome.fetchName(appStrings: AppStrings): String? = when (customName != null && customName != "null") {
    true -> customName
    false -> defaultType?.mapToString(appStrings)?.let { getString(it) } ?: ""
}

