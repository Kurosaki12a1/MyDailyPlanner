package com.kuro.mdp.features.settings.presentation.ui.categories.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kuro.mdp.features.settings.domain.model.categories.MainCategoryUi
import com.kuro.mdp.features.settings.domain.model.categories.SubCategoryUi
import com.kuro.mdp.features.settings.presentation.ui.categories.CategoriesViewState

@Composable
internal fun CategoriesContent(
    state: CategoriesViewState,
    modifier: Modifier = Modifier,
    onAddMainCategory: (name: String) -> Unit,
    onAddSubCategory: () -> Unit,
    onChangeMainCategory: (MainCategoryUi) -> Unit,
    onSubCategoryUpdate: (SubCategoryUi) -> Unit,
    onMainCategoryUpdate: (MainCategoryUi) -> Unit,
    onMainCategoryDelete: (MainCategoryUi) -> Unit,
    onSubCategoryDelete: (SubCategoryUi) -> Unit,
    onRestoreDefaultCategories: () -> Unit,
) {
    var isMainCategoryCreatorOpen by rememberSaveable { mutableStateOf(false) }
    val scrollableState = rememberScrollState()

    Column(modifier = modifier.fillMaxSize().verticalScroll(scrollableState)) {
        val categories = state.categories.find { it.mainCategory == state.selectedMainCategory }
        val subCategories = categories?.subCategories ?: emptyList()

        MainCategoriesHeader(
            modifier = Modifier.padding(top = 8.dp, bottom = 4.dp).fillMaxWidth(),
            onRestoreDefaultCategories = onRestoreDefaultCategories,
        )
        MainCategoriesHorizontalList(
            modifier = Modifier.fillMaxWidth().padding(top = 8.dp, bottom = 12.dp),
            mainCategories = state.categories.map { it.mainCategory },
            selectedCategory = state.selectedMainCategory,
            onSelectCategory = onChangeMainCategory,
            onUpdateCategory = onMainCategoryUpdate,
            onDeleteCategory = onMainCategoryDelete,
            onAddCategory = { isMainCategoryCreatorOpen = true },
        )
        SubCategoriesHeader(
            modifier = Modifier.padding(top = 12.dp, bottom = 8.dp).fillMaxWidth(),
        )
        SubCategoriesList(
            mainCategory = state.selectedMainCategory,
            subCategories = subCategories,
            onCategoryUpdate = onSubCategoryUpdate,
            onCategoryDelete = onSubCategoryDelete,
            onAddSubCategory = onAddSubCategory,
        )
    }
    if (isMainCategoryCreatorOpen) {
        MainCategoryEditorDialog(
            onDismiss = { isMainCategoryCreatorOpen = false },
            onConfirm = { name ->
                onAddMainCategory(name)
                isMainCategoryCreatorOpen = false
            },
        )
    }
}
