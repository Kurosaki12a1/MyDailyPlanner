package com.kuro.mdp.features.settings.presentation.ui.template

import androidx.compose.runtime.Stable
import com.kuro.mdp.features.settings.domain.model.categories.CategoriesUi
import com.kuro.mdp.features.settings.domain.model.template.TemplateUi
import com.kuro.mdp.features.settings.domain.model.template.TemplatesSortedType
import com.kuro.mdp.shared.presentation.screenmodel.contract.BaseViewState
import kotlinx.serialization.Serializable

@Serializable
@Stable
data class TemplateViewState(
    val templates: List<TemplateUi>? = null,
    val categories: List<CategoriesUi> = emptyList(),
    val isShowTemplateCreator: Boolean = false,
    val sortedType: TemplatesSortedType = TemplatesSortedType.DATE,
    val failure: String? = null
) : BaseViewState