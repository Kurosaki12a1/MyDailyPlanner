package com.kuro.mdp.features.home.presentation.ui.home.ui.editor.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
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
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import com.kuro.mdp.features.home.domain.model.categories.MainCategoryHome
import com.kuro.mdp.features.home.domain.model.categories.SubCategoryHome
import com.kuro.mdp.features.home.presentation.ui.home.theme.HomeTheme
import com.kuro.mdp.features.home.presentation.ui.home.util.fetchName
import com.kuro.mdp.shared.presentation.extensions.string
import com.kuro.mdp.shared.presentation.theme.AppTheme
import com.kuro.mdp.shared.presentation.views.BaseSelectorBottomSheet
import com.kuro.mdp.shared.presentation.views.SelectorAddItemView
import com.kuro.mdp.shared.presentation.views.SelectorNotSelectedItemView
import com.kuro.mdp.shared.presentation.views.SelectorSwipeItemView
import com.kuro.mdp.shared.presentation.views.SelectorTextField
import com.kuro.mdp.shared.presentation.views.SwipeToDismissBackground
import com.kuro.mdp.shared.utils.format
import com.kuro.mdp.shared.utils.functional.Constants
import org.jetbrains.compose.resources.painterResource

/**
 * Created by: minhthinh.h on 2/10/2025
 *
 * Description:
 */
@Composable
internal fun SubCategoryChooser(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    mainCategory: MainCategoryHome?,
    allSubCategories: List<SubCategoryHome>,
    currentSubCategory: SubCategoryHome?,
    onChangeSubCategory: (SubCategoryHome?) -> Unit,
    onEditSubCategory: (SubCategoryHome) -> Unit,
    onAddSubCategory: (String) -> Unit,
) {
    var openSubCategorySelectorSheet by rememberSaveable { mutableStateOf(false) }
    Surface(
        enabled = enabled,
        onClick = { openSubCategorySelectorSheet = true },
        modifier = modifier.sizeIn(minHeight = 68.dp),
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.surfaceContainerLow,
    ) {
        Row(
            modifier = Modifier.padding(top = 8.dp, bottom = 8.dp, start = 16.dp, end = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                painter = painterResource(HomeTheme.icons.subCategory),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurface,
            )
            Column(modifier = Modifier.weight(1f).animateContentSize()) {
                val mainTitle = if (mainCategory != null) currentSubCategory?.name else ""
                Text(
                    text = HomeTheme.strings.subCategoryChooserTitle.string(),
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    style = MaterialTheme.typography.labelMedium,
                )
                Text(
                    text = mainTitle ?: HomeTheme.strings.categoryNotSelectedTitle.string(),
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.titleMedium,
                )
            }

            val icon = when (currentSubCategory != null) {
                true -> HomeTheme.icons.showDialog
                false -> HomeTheme.icons.add
            }
            val tint = when (mainCategory != null) {
                true -> MaterialTheme.colorScheme.onSurface
                false -> MaterialTheme.colorScheme.surfaceVariant
            }
            Icon(
                modifier = Modifier.animateContentSize(),
                painter = painterResource(icon),
                contentDescription = HomeTheme.strings.chooseCategoryTitle.string(),
                tint = tint,
            )
        }
    }
    if (openSubCategorySelectorSheet) {
        SubCategorySelectorBottomSheet(
            initCategory = currentSubCategory,
            mainCategory = mainCategory,
            allSubCategories = allSubCategories,
            onDismiss = { openSubCategorySelectorSheet = false },
            onAddCategory = onAddSubCategory,
            onEditSubCategory = onEditSubCategory,
            onChooseSubCategory = { onChangeSubCategory(it); openSubCategorySelectorSheet = false },
        )
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
internal fun SubCategorySelectorBottomSheet(
    modifier: Modifier = Modifier,
    initCategory: SubCategoryHome?,
    mainCategory: MainCategoryHome?,
    allSubCategories: List<SubCategoryHome>,
    onDismiss: () -> Unit,
    onChooseSubCategory: (SubCategoryHome?) -> Unit,
    onEditSubCategory: (SubCategoryHome) -> Unit,
    onAddCategory: (String) -> Unit,
) {
    var selectedSubCategory by rememberSaveable { mutableStateOf(initCategory) }
    var searchQuery by rememberSaveable { mutableStateOf<String?>(null) }
    var isEdited by remember { mutableStateOf(false) }
    var editableSubCategory by remember { mutableStateOf("") }
    val focusRequester = remember { FocusRequester() }
    val searchedSubCategory = remember(searchQuery, allSubCategories) {
        allSubCategories.filter { category ->
            searchQuery == null || (category.name?.contains(searchQuery ?: "", true) == true)
        }
    }

    BaseSelectorBottomSheet(
        modifier = modifier,
        selected = selectedSubCategory,
        items = searchedSubCategory,
        header = HomeTheme.strings.subCategoryChooserTitle.string(),
        title = HomeTheme.strings.subCategoryDialogMainCategoryFormat.string().format(
            mainCategory?.fetchName() ?: HomeTheme.strings.categoryNotSelectedTitle,
        ),
        itemView = { subCategory ->
            val dismissState = rememberSwipeToDismissBoxState(
                confirmValueChange = { dismissBoxValue ->
                    when (dismissBoxValue) {
                        SwipeToDismissBoxValue.StartToEnd -> Unit
                        SwipeToDismissBoxValue.EndToStart -> onEditSubCategory(subCategory)
                        SwipeToDismissBoxValue.Settled -> Unit
                    }
                    return@rememberSwipeToDismissBoxState false
                },
                positionalThreshold = { it * .60f },
            )
            SelectorSwipeItemView(
                onClick = { selectedSubCategory = subCategory },
                state = dismissState,
                selected = subCategory.id == selectedSubCategory?.id,
                title = subCategory.name ?: AppTheme.strings.categoryEmptyTitle.string(),
                label = subCategory.description,
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
            )
        },
        addItemView = if (searchQuery == null) {
            {
                AnimatedContent(targetState = isEdited, label = "subCategory") { edit ->
                    if (edit) {
                        SelectorTextField(
                            modifier = Modifier.focusRequester(focusRequester),
                            value = editableSubCategory,
                            onValueChange = {
                                if (it.length < Constants.Text.MAX_LENGTH) {
                                    editableSubCategory = it
                                }
                            },
                            onDismiss = {
                                editableSubCategory = ""
                                isEdited = false
                            },
                            maxLines = 1,
                            onConfirm = {
                                onAddCategory(editableSubCategory)
                                editableSubCategory = ""
                                isEdited = false
                            }
                        )
                        SideEffect { focusRequester.requestFocus() }
                    } else {
                        SelectorAddItemView(
                            text = HomeTheme.strings.subCategoryDialogAddedTitle.string(),
                            enabled = mainCategory?.id != 0 && mainCategory != null,
                            onClick = { isEdited = true },
                        )
                    }
                }
            }
        } else {
            null
        },
        notSelectedItem = if (searchQuery == null) {
            {
                SelectorNotSelectedItemView(
                    text = HomeTheme.strings.categoryNotSelectedTitle.string(),
                    selected = selectedSubCategory == null,
                    onClick = { selectedSubCategory = null },
                )
            }
        } else {
            null
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
        onConfirm = onChooseSubCategory,
    )
}