package com.kuro.mdp.features.overview.presentation.ui.component

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
import com.kuro.mdp.features.overview.domain.model.categories.CategoriesOverView
import com.kuro.mdp.features.overview.domain.model.categories.MainCategoryOverView
import com.kuro.mdp.features.overview.domain.model.categories.SubCategoryOverView
import com.kuro.mdp.features.overview.presentation.theme.OverViewTheme
import com.kuro.mdp.features.overview.presentation.util.fetchName
import com.kuro.mdp.shared.presentation.mappers.mapToIconPainter
import com.kuro.mdp.shared.presentation.theme.AppTheme
import com.kuro.mdp.shared.presentation.views.ExpandedIcon
import com.kuro.mdp.shared.utils.extensions.string
import org.jetbrains.compose.resources.painterResource

/**
 * Created by: minhthinh.h on 1/21/2025
 *
 * Description:
 */
@Composable
internal fun CompactCategoryChooser(
    modifier: Modifier = Modifier,
    allCategories: List<CategoriesOverView>,
    selectedCategory: MainCategoryOverView,
    onCategoryChange: (MainCategoryOverView) -> Unit,
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
            painter = painterResource(OverViewTheme.icons.category),
            contentDescription = OverViewTheme.strings.mainCategoryLabel.string(),
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = selectedCategory.fetchName() ?: "*",
            onValueChange = {},
            readOnly = true,
            label = { Text(text = OverViewTheme.strings.mainCategoryLabel.string()) },
            trailingIcon = { ExpandedIcon(isExpanded = isCategoryMenuOpen) },
            shape = MaterialTheme.shapes.large,
            interactionSource = interactionSource,
        )
        Box(contentAlignment = Alignment.TopEnd) {
            MainCategoriesChooseMenu(
                isExpanded = isCategoryMenuOpen,
                mainCategories = allCategories.map { it.mainCategory },
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
internal fun MainCategoriesChooseMenu(
    modifier: Modifier = Modifier,
    isExpanded: Boolean,
    mainCategories: List<MainCategoryOverView>,
    onDismiss: () -> Unit,
    onChoose: (MainCategoryOverView) -> Unit,
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
                    if (category.defaultType != null) {
                        Icon(
                            modifier = Modifier.size(18.dp),
                            painter = category.defaultType!!.mapToIconPainter(),
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
internal fun CompactSubCategoryChooser(
    modifier: Modifier = Modifier,
    allCategories: List<CategoriesOverView>,
    selectedMainCategory: MainCategoryOverView,
    selectedSubCategory: SubCategoryOverView?,
    onSubCategoryChange: (SubCategoryOverView?) -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    var isSubCategoryMenuOpen by remember { mutableStateOf(false) }
    val isPressed: Boolean by interactionSource.collectIsPressedAsState()
    val subCategories = allCategories.find { it.mainCategory == selectedMainCategory }?.subCategories ?: emptyList()

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Icon(
            painter = painterResource(OverViewTheme.icons.subCategory),
            contentDescription = OverViewTheme.strings.subCategoryLabel.string(),
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = selectedSubCategory?.name ?: OverViewTheme.strings.subCategoryEmptyTitle.string(),
            onValueChange = {},
            readOnly = true,
            label = { Text(text = OverViewTheme.strings.subCategoryLabel.string()) },
            trailingIcon = { ExpandedIcon(isExpanded = isSubCategoryMenuOpen) },
            shape = MaterialTheme.shapes.large,
            interactionSource = interactionSource,
        )
        Box(contentAlignment = Alignment.TopEnd) {
            SubCategoriesChooseMenu(
                isExpanded = isSubCategoryMenuOpen,
                subCategories = subCategories.toMutableList().apply { add(SubCategoryOverView()) },
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
internal fun SubCategoriesChooseMenu(
    modifier: Modifier = Modifier,
    isExpanded: Boolean,
    subCategories: List<SubCategoryOverView>,
    onDismiss: () -> Unit,
    onChoose: (SubCategoryOverView?) -> Unit,
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
