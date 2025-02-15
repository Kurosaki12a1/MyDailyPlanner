package com.kuro.mdp.features.home.presentation.ui.home.ui.editor.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.SwipeToDismissBoxState
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import com.kuro.mdp.features.home.domain.model.categories.MainCategoryHome
import com.kuro.mdp.features.home.presentation.ui.home.theme.HomeTheme
import com.kuro.mdp.features.home.presentation.ui.home.util.fetchName
import com.kuro.mdp.shared.domain.model.categories.DefaultCategoryType
import com.kuro.mdp.shared.presentation.extensions.string
import com.kuro.mdp.shared.presentation.mappers.mapToIconPainter
import com.kuro.mdp.shared.presentation.theme.AppTheme
import com.kuro.mdp.shared.presentation.views.BaseSelectorBottomSheet
import com.kuro.mdp.shared.presentation.views.CategoryIconMonogram
import com.kuro.mdp.shared.presentation.views.CategoryTextMonogram
import com.kuro.mdp.shared.presentation.views.SelectorSwipeItemView
import com.kuro.mdp.shared.presentation.views.SwipeToDismissBackground
import org.jetbrains.compose.resources.painterResource

/**
 * Created by: minhthinh.h on 2/10/2025
 *
 * Description:
 */
@Composable
internal fun MainCategoryChooser(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    isError: Boolean = false,
    allCategories: List<MainCategoryHome>,
    currentCategory: MainCategoryHome?,
    onEditCategory: (MainCategoryHome) -> Unit,
    onChangeCategory: (MainCategoryHome) -> Unit,
) {
    var openSubCategorySelectorSheet by rememberSaveable { mutableStateOf(false) }
    val categoryIcon = currentCategory?.defaultType?.mapToIconPainter()
    val categoryName = currentCategory?.fetchName()

    Surface(
        onClick = { openSubCategorySelectorSheet = true },
        modifier = modifier.height(68.dp),
        enabled = currentCategory != null && enabled,
        shape = MaterialTheme.shapes.medium,
        color = when (isError) {
            true -> MaterialTheme.colorScheme.errorContainer
            false -> MaterialTheme.colorScheme.surfaceContainerLow
        },
        border = when (isError) {
            true -> BorderStroke(1.5.dp, MaterialTheme.colorScheme.error)
            false -> null
        },
    ) {
        Row(
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            val categoryNameColor = when (currentCategory != null) {
                true -> MaterialTheme.colorScheme.onSurface
                false -> MaterialTheme.colorScheme.onSurfaceVariant
            }
            if (currentCategory != null && categoryIcon == null) {
                CategoryTextMonogram(
                    text = categoryName?.first()?.toString() ?: "",
                    textColor = MaterialTheme.colorScheme.primary,
                    backgroundColor = MaterialTheme.colorScheme.primaryContainer,
                )
            } else {
                CategoryIconMonogram(
                    icon = categoryIcon ?: DefaultCategoryType.EMPTY.mapToIconPainter(),
                    iconDescription = categoryName,
                    iconColor = when (isError) {
                        true -> MaterialTheme.colorScheme.errorContainer
                        false -> MaterialTheme.colorScheme.primary
                    },
                    backgroundColor = when (isError) {
                        true -> MaterialTheme.colorScheme.error
                        false -> MaterialTheme.colorScheme.primaryContainer
                    },
                )
            }
            Column(modifier = Modifier.weight(1f).animateContentSize()) {
                Text(
                    text = HomeTheme.strings.mainCategoryChooserTitle.string(),
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    style = MaterialTheme.typography.labelMedium,
                )
                Text(
                    text = categoryName ?: HomeTheme.strings.categoryNotSelectedTitle.string(),
                    color = categoryNameColor,
                    style = MaterialTheme.typography.titleMedium,
                )
            }
            Icon(
                painter = painterResource(HomeTheme.icons.showDialog),
                contentDescription = HomeTheme.strings.chooseCategoryTitle.string(),
                tint = categoryNameColor,
            )
        }
    }
    if (openSubCategorySelectorSheet && currentCategory != null) {
        MainCategorySelectorBottomSheet(
            initCategory = currentCategory,
            allCategories = allCategories,
            onDismiss = { openSubCategorySelectorSheet = false },
            onEditCategory = onEditCategory,
            onChooseCategory = {
                onChangeCategory(it)
                openSubCategorySelectorSheet = false
            },
        )
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
internal fun MainCategorySelectorBottomSheet(
    modifier: Modifier = Modifier,
    allCategories: List<MainCategoryHome>,
    initCategory: MainCategoryHome,
    onDismiss: () -> Unit,
    onEditCategory: (MainCategoryHome) -> Unit,
    onChooseCategory: (MainCategoryHome) -> Unit,
) {
    val coreString = AppTheme.strings
    var selectedCategory by rememberSaveable { mutableStateOf(initCategory) }
    var searchQuery by rememberSaveable { mutableStateOf<String?>(null) }
    var searchedCategory by rememberSaveable { mutableStateOf<List<MainCategoryHome>>(listOf()) }

    LaunchedEffect(searchQuery, allCategories) {
        searchedCategory = allCategories.filter { category ->
            searchQuery == null || (category.fetchName(coreString)?.contains(searchQuery ?: "", true) == true)
        }
    }

    BaseSelectorBottomSheet(
        modifier = modifier,
        selected = selectedCategory,
        items = searchedCategory,
        header = HomeTheme.strings.mainCategoryChooserTitle.string(),
        title = null,
        itemView = { category ->
            val isSelected = category.id == selectedCategory.id
            val density = LocalDensity.current
            val dismissState = remember(category) {
                SwipeToDismissBoxState(
                    initialValue = SwipeToDismissBoxValue.Settled,
                    density = density,
                    confirmValueChange = { dismissBoxValue ->
                        when (dismissBoxValue) {
                            SwipeToDismissBoxValue.StartToEnd -> Unit
                            SwipeToDismissBoxValue.EndToStart -> onEditCategory(category)
                            SwipeToDismissBoxValue.Settled -> Unit
                        }
                        false
                    },
                    positionalThreshold = { it * .60f }
                )
            }
            SelectorSwipeItemView(
                onClick = { selectedCategory = category },
                state = dismissState,
                selected = isSelected,
                title = category.fetchName() ?: "*",
                label = null,
                enableDismissFromStartToEnd = false,
                backgroundContent = {
                    SwipeToDismissBackground(
                        dismissState = dismissState,
                        endToStartContent = {
                            Icon(
                                imageVector = Icons.Default.Edit,
                                contentDescription = null,
                            )
                        },
                        endToStartColor = MaterialTheme.colorScheme.tertiaryContainer,
                    )
                },
                leadingIcon = {
                    val title = category.fetchName() ?: "*"
                    val icon = category.defaultType?.mapToIconPainter()
                    if (icon != null) {
                        CategoryIconMonogram(
                            icon = icon,
                            iconColor = MaterialTheme.colorScheme.onPrimary,
                            backgroundColor = MaterialTheme.colorScheme.primary,
                            iconDescription = title,
                        )
                    } else {
                        CategoryTextMonogram(
                            text = title.first().toString(),
                            textColor = MaterialTheme.colorScheme.onPrimary,
                            backgroundColor = MaterialTheme.colorScheme.primary,
                        )
                    }
                }
            )
        },
        searchBar = {
            SearchBar(
                inputField = {
                    val focusManager = LocalFocusManager.current
                    val searchInteractionSource = remember { MutableInteractionSource() }
                    val isFocus = searchInteractionSource.collectIsFocusedAsState().value

                    SearchBarDefaults.InputField(
                        query = searchQuery ?: "",
                        onQueryChange = { searchQuery = it.takeIf { it.isNotBlank() } },
                        onSearch = {
                            searchQuery = it.takeIf { it.isNotBlank() }
                            focusManager.clearFocus()
                        },
                        expanded = false,
                        onExpandedChange = {},
                        enabled = true,
                        placeholder = {
                            Text(text = HomeTheme.strings.chooseCategoryTitle.string())
                        },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = HomeTheme.strings.topAppBarBackIconDesc.string(),
                                tint = MaterialTheme.colorScheme.onSurface,
                            )
                        },
                        trailingIcon = {
                            AnimatedVisibility(
                                visible = isFocus,
                                enter = fadeIn() + scaleIn(),
                                exit = fadeOut() + scaleOut(),
                            ) {
                                IconButton(
                                    onClick = {
                                        focusManager.clearFocus()
                                        searchQuery = null
                                    }
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Close,
                                        contentDescription = null,
                                        tint = MaterialTheme.colorScheme.error,
                                    )
                                }
                            }
                        },
                        interactionSource = searchInteractionSource,
                    )
                },
                expanded = false,
                onExpandedChange = {},
                modifier = Modifier.fillMaxWidth(),
                windowInsets = WindowInsets(0.dp),
                content = {},
            )
        },
        onDismissRequest = onDismiss,
        onConfirm = { category -> if (category != null) onChooseCategory(category) },
    )
}
