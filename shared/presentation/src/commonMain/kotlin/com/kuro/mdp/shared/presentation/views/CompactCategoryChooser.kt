package com.kuro.mdp.shared.presentation.views

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.kuro.mdp.shared.domain.model.categories.Categories
import com.kuro.mdp.shared.domain.model.categories.MainCategory
import com.kuro.mdp.shared.domain.model.categories.SubCategory
import com.kuro.mdp.shared.presentation.extensions.string
import com.kuro.mdp.shared.presentation.mappers.fetchName
import com.kuro.mdp.shared.presentation.mappers.mapToIconPainter
import com.kuro.mdp.shared.presentation.theme.AppTheme
import org.jetbrains.compose.resources.painterResource

@Composable
fun CompactCategoryChooser(
    modifier: Modifier = Modifier,
    allCategories: List<Categories>,
    selectedCategory: MainCategory,
    onCategoryChange: (MainCategory) -> Unit,
) {
    var isCategoryMenuOpen by remember { mutableStateOf(false) }
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed: Boolean by interactionSource.collectIsPressedAsState()

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Icon(
            painter = painterResource(AppTheme.icons.category),
            contentDescription = AppTheme.strings.mainCategoryLabel.string(),
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = selectedCategory.fetchName() ?: "*",
            onValueChange = {},
            readOnly = true,
            label = { Text(text = AppTheme.strings.mainCategoryLabel.string()) },
            trailingIcon = { ExpandedIcon(isExpanded = isCategoryMenuOpen) },
            shape = MaterialTheme.shapes.large,
            interactionSource = interactionSource,
        )
        Box(contentAlignment = Alignment.TopEnd) {
            MainCategoriesChooseMenu(
                isExpanded = isCategoryMenuOpen,
                mainCategories = allCategories.map { it.category },
                onDismiss = { isCategoryMenuOpen = false },
                onChoose = { mainCategory ->
                    isCategoryMenuOpen = false
                    onCategoryChange(mainCategory)
                },
            )
        }
    }

    LaunchedEffect(key1 = isPressed) {
        if (isPressed) {
            isCategoryMenuOpen = !isCategoryMenuOpen
        }
    }
}

@Composable
fun MainCategoriesChooseMenu(
    modifier: Modifier = Modifier,
    isExpanded: Boolean,
    mainCategories: List<MainCategory>,
    onDismiss: () -> Unit,
    onChoose: (MainCategory) -> Unit,
) {
    DropdownMenu(
        expanded = isExpanded,
        onDismissRequest = onDismiss,
        modifier = modifier.sizeIn(maxHeight = 200.dp),
        shape = MaterialTheme.shapes.large,
        offset = DpOffset(0.dp, 6.dp),
    ) {
        mainCategories.forEach { category ->
            DropdownMenuItem(
                onClick = { onChoose(category) },
                leadingIcon = {
                    if (category.default != null) {
                        Icon(
                            modifier = Modifier.size(18.dp),
                            painter = category.default!!.mapToIconPainter(),
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                        )
                    } else {
                        Text(
                            text = category.customName?.first()?.uppercaseChar()?.toString() ?: "*",
                            color = MaterialTheme.colorScheme.primary,
                            style = MaterialTheme.typography.titleLarge,
                        )
                    }
                },
                text = {
                    Text(
                        text = category.fetchName() ?: "*",
                        color = MaterialTheme.colorScheme.onSurface,
                        style = MaterialTheme.typography.titleMedium,
                    )
                },
            )
        }
    }
}

@Composable
fun CompactSubCategoryChooser(
    modifier: Modifier = Modifier,
    allCategories: List<Categories>,
    selectedMainCategory: MainCategory,
    selectedSubCategory: SubCategory?,
    onSubCategoryChange: (SubCategory?) -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    var isSubCategoryMenuOpen by remember { mutableStateOf(false) }
    val isPressed: Boolean by interactionSource.collectIsPressedAsState()
    val subCategories =
        allCategories.find { it.category == selectedMainCategory }?.subCategories ?: emptyList()

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Icon(
            painter = painterResource(AppTheme.icons.subCategory),
            contentDescription = AppTheme.strings.subCategoryLabel.string(),
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = selectedSubCategory?.name ?: AppTheme.strings.subCategoryEmptyTitle.string(),
            onValueChange = {},
            readOnly = true,
            label = { Text(text = AppTheme.strings.subCategoryLabel.string()) },
            trailingIcon = { ExpandedIcon(isExpanded = isSubCategoryMenuOpen) },
            shape = MaterialTheme.shapes.large,
            interactionSource = interactionSource,
        )
        Box(contentAlignment = Alignment.TopEnd) {
            SubCategoriesChooseMenu(
                isExpanded = isSubCategoryMenuOpen,
                subCategories = subCategories.toMutableList().apply { add(SubCategory()) },
                onDismiss = { isSubCategoryMenuOpen = false },
                onChoose = { subCategory ->
                    isSubCategoryMenuOpen = false
                    onSubCategoryChange(subCategory)
                },
            )
        }
    }

    LaunchedEffect(key1 = isPressed) {
        if (isPressed) {
            isSubCategoryMenuOpen = !isSubCategoryMenuOpen
        }
    }
}

@Composable
fun SubCategoriesChooseMenu(
    modifier: Modifier = Modifier,
    isExpanded: Boolean,
    subCategories: List<SubCategory>,
    onDismiss: () -> Unit,
    onChoose: (SubCategory?) -> Unit,
) {
    DropdownMenu(
        expanded = isExpanded,
        onDismissRequest = onDismiss,
        modifier = modifier.sizeIn(maxHeight = 200.dp),
        shape = MaterialTheme.shapes.large,
        offset = DpOffset(0.dp, 6.dp),
    ) {
        subCategories.forEach { subCategory ->
            DropdownMenuItem(
                onClick = { if (subCategory.id == 0) onChoose(null) else onChoose(subCategory) },
                text = {
                    Text(
                        text = subCategory.name ?: AppTheme.strings.categoryEmptyTitle.string(),
                        color = MaterialTheme.colorScheme.onSurface,
                        style = MaterialTheme.typography.titleMedium,
                    )
                },
            )
        }
    }
}
