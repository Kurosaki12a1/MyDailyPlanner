package com.kuro.mdp.features.overview.presentation.ui.component

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import com.kuro.mdp.features.overview.domain.mapper.categories.mapToDomain
import com.kuro.mdp.features.overview.domain.mapper.categories.mapToUi
import com.kuro.mdp.features.overview.domain.model.categories.CategoriesOverView
import com.kuro.mdp.features.overview.domain.model.categories.MainCategoryOverView
import com.kuro.mdp.features.overview.domain.model.schedules.UndefinedTaskOverView
import com.kuro.mdp.features.overview.presentation.theme.OverViewTheme
import com.kuro.mdp.shared.domain.model.schedules.TaskPriority
import com.kuro.mdp.shared.presentation.extensions.string
import com.kuro.mdp.shared.presentation.theme.AppTheme
import com.kuro.mdp.shared.presentation.views.CompactCategoryChooser
import com.kuro.mdp.shared.presentation.views.CompactSubCategoryChooser
import com.kuro.mdp.shared.presentation.views.DialogButtons
import com.kuro.mdp.shared.presentation.views.PriorityChooser
import com.kuro.mdp.shared.utils.extensions.generateUniqueKey
import com.kuro.mdp.shared.utils.extensions.getLocalDateTimeNow
import com.kuro.mdp.shared.utils.functional.Constants
import org.jetbrains.compose.resources.painterResource

/**
 * Created by: minhthinh.h on 1/21/2025
 *
 * Description:
 */
@Composable
@OptIn(ExperimentalMaterial3Api::class)
internal fun UndefinedTaskEditorDialog(
    modifier: Modifier = Modifier,
    categories: List<CategoriesOverView>,
    model: UndefinedTaskOverView?,
    onDismiss: () -> Unit,
    onConfirm: (UndefinedTaskOverView) -> Unit,
) {
    val scrollState = rememberScrollState()
    var mainCategory by remember { mutableStateOf(model?.mainCategory ?: MainCategoryOverView()) }
    var subCategory by remember { mutableStateOf(model?.subCategory) }
    var note by remember { mutableStateOf(model?.note) }
    var deadline by remember { mutableStateOf(model?.deadline) }
    var priority by remember { mutableStateOf(model?.priority ?: TaskPriority.STANDARD) }

    BasicAlertDialog(onDismissRequest = onDismiss) {
        Surface(
            modifier = modifier.width(328.dp).wrapContentHeight(),
            shape = MaterialTheme.shapes.extraLarge,
            color = MaterialTheme.colorScheme.surfaceContainer,
        ) {
            Column {
                UndefinedTaskEditorDialogHeader()
                HorizontalDivider()
                Column(
                    modifier = Modifier
                        .height(360.dp)
                        .padding(start = 20.dp, end = 20.dp, top = 16.dp, bottom = 0.dp)
                        .verticalScroll(scrollState),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    CompactCategoryChooser(
                        allCategories = categories.map { it.mapToDomain() },
                        selectedCategory = mainCategory.mapToDomain(),
                        onCategoryChange = {
                            mainCategory = it.mapToUi()
                            subCategory = null
                        },
                    )
                    CompactSubCategoryChooser(
                        allCategories = categories.map { it.mapToDomain() },
                        selectedMainCategory = mainCategory.mapToDomain(),
                        selectedSubCategory = subCategory?.mapToDomain(),
                        onSubCategoryChange = { subCategory = it?.mapToUi() },
                    )
                    NoteCompactTextField(
                        note = note,
                        onNoteChange = { note = it },
                    )
                    DeadlineChooser(
                        modifier = Modifier.fillMaxWidth(),
                        deadline = deadline,
                        onChooseDeadline = { deadline = it },
                    )
                    PriorityChooser(
                        priority = priority,
                        onPriorityChange = { priority = it },
                    )
                }

                val isEnabled = mainCategory.id != 0
                DialogButtons(
                    enabledConfirm = isEnabled,
                    confirmTitle = when (model != null) {
                        true -> AppTheme.strings.okConfirmTitle.string()
                        false -> AppTheme.strings.dialogCreateTitle.string()
                    },
                    onConfirmClick = {
                        if (isEnabled) {
                            val task = UndefinedTaskOverView(
                                id = model?.id ?: generateUniqueKey(),
                                createdAt = getLocalDateTimeNow(),
                                mainCategory = mainCategory,
                                subCategory = subCategory,
                                priority = priority,
                                deadline = deadline,
                                note = note,
                            )
                            onConfirm(task)
                        }
                    },
                    onCancelClick = onDismiss,
                )
            }
        }
    }
}

@Composable
internal fun UndefinedTaskEditorDialogHeader(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.padding(top = 24.dp, bottom = 12.dp, start = 24.dp, end = 24.dp),
        verticalArrangement = Arrangement.spacedBy(2.dp),
    ) {
        Text(
            text = OverViewTheme.strings.addTaskTitle.string(),
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.headlineSmall,
        )
    }
}

@Composable
internal fun NoteCompactTextField(
    modifier: Modifier = Modifier,
    note: String?,
    onNoteChange: (String?) -> Unit,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
) {
    val focusManager = LocalFocusManager.current
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Icon(
            painter = painterResource(OverViewTheme.icons.subCategory),
            contentDescription = AppTheme.strings.subCategoryLabel.string(),
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        OutlinedTextField(
            modifier = Modifier.weight(1f),
            value = note ?: "",
            onValueChange = {
                if (it.length <= Constants.Text.MAX_NOTE_LENGTH) onNoteChange(it.ifEmpty { null })
            },
            label = { Text(text = OverViewTheme.strings.noteTitle.string()) },
            trailingIcon = if (interactionSource.collectIsFocusedAsState().value) {
                {
                    IconButton(
                        modifier = Modifier.size(32.dp),
                        onClick = { focusManager.clearFocus(); },
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.CheckCircle,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onSurface,
                        )
                    }
                }
            } else {
                null
            },
            interactionSource = interactionSource,
            shape = MaterialTheme.shapes.large,
            colors = OutlinedTextFieldDefaults.colors(
                cursorColor = MaterialTheme.colorScheme.onSurface,
            ),
        )
    }
}