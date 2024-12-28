package com.kuro.mdp.features.home.domain.model.categories

import androidx.compose.runtime.Composable
import com.kuro.mdp.shared.domain.model.categories.DefaultCategoryType
import com.kuro.mdp.shared.presentation.mappers.mapToKey
import kotlinx.serialization.Serializable

@Serializable
data class MainCategoryHome(
    val id: Int = 0,
    val customName: String? = null,
    val defaultType: DefaultCategoryType? = DefaultCategoryType.EMPTY,
) {

    @Composable
    fun fetchName() = when (customName != null && customName != "null") {
        true -> customName
        false -> defaultType?.mapToKey()
    }
}
