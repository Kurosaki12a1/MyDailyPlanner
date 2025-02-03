package com.kuro.mdp.shared.presentation.mappers

import androidx.compose.runtime.Composable
import com.kuro.mdp.shared.domain.model.categories.MainCategory

@Composable
fun MainCategory.fetchName() = when (customName != null && customName != "null") {
    true -> customName
    false -> default?.mapToName()
}