package com.kuro.mdp.features.home.presentation.ui.home.ui.editor.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.kuro.mdp.features.home.domain.model.categories.CategoriesHome
import com.kuro.mdp.features.home.domain.model.categories.MainCategoryHome
import com.kuro.mdp.features.home.domain.model.categories.SubCategoryHome
import com.kuro.mdp.features.home.domain.model.editor.EditParameters
import com.kuro.mdp.features.home.domain.model.editor.error.CategoryValidateError
import com.kuro.mdp.features.home.domain.model.editor.error.TimeRangeError
import com.kuro.mdp.features.home.presentation.ui.home.mappers.convertToModel
import com.kuro.mdp.features.home.presentation.ui.home.mappers.convertToParameter
import com.kuro.mdp.features.home.presentation.ui.home.theme.HomeTheme
import com.kuro.mdp.features.home.presentation.ui.home.ui.editor.PriorityParameters
import com.kuro.mdp.features.home.presentation.ui.home.ui.editor.contract.EditorViewState
import com.kuro.mdp.shared.presentation.extensions.string
import com.kuro.mdp.shared.presentation.theme.AppTheme
import com.kuro.mdp.shared.presentation.views.CustomLargeTextField
import com.kuro.mdp.shared.utils.extensions.shiftDays
import com.kuro.mdp.shared.utils.extensions.shiftMillis
import com.kuro.mdp.shared.utils.functional.Constants
import com.kuro.mdp.shared.utils.functional.TimeRange
import org.jetbrains.compose.resources.painterResource

/**
 * Created by: minhthinh.h on 2/10/2025
 *
 * Description:
 */
@Composable
internal fun EditorContent(
    state: EditorViewState,
    modifier: Modifier = Modifier,
    onCategoriesChange: (MainCategoryHome, SubCategoryHome?) -> Unit,
    onNoteChange: (String?) -> Unit,
    onAddSubCategory: (String) -> Unit,
    onTimeRangeChange: (TimeRange) -> Unit,
    onChangeParameters: (EditParameters) -> Unit,
    onEditCategory: (MainCategoryHome) -> Unit,
    onEditSubCategory: (SubCategoryHome) -> Unit,
    onControlTemplate: () -> Unit,
    onCreateTemplate: () -> Unit,
    onSaveClick: () -> Unit,
    onCancelClick: () -> Unit,
) {
    val scrollState = rememberScrollState()
    AnimatedVisibility(
        visible = state.editModel != null,
        enter = fadeIn(),
        exit = fadeOut(),
    ) {
        Column(modifier = modifier.fillMaxSize().animateContentSize()) {
            if (state.editModel != null) {
                Column(
                    modifier = Modifier.weight(1f).verticalScroll(scrollState).padding(top = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    CategoriesSection(
                        enabledCategories = !state.editModel.checkDateIsRepeat(),
                        isMainCategoryValidError = state.categoryValid is CategoryValidateError.EmptyCategoryError,
                        mainCategory = state.editModel.mainCategory,
                        subCategory = state.editModel.subCategory,
                        allCategories = state.categories,
                        note = state.editModel.note,
                        onEditCategory = onEditCategory,
                        onEditSubCategory = onEditSubCategory,
                        onCategoriesChange = onCategoriesChange,
                        onAddSubCategory = onAddSubCategory,
                        onNoteChange = onNoteChange,
                    )
                    HorizontalDivider(Modifier.padding(horizontal = 32.dp))
                    DateTimeSection(
                        enabled = !state.editModel.checkDateIsRepeat(),
                        isTimeValidError = state.timeRangeValid is TimeRangeError.DurationError,
                        timeRanges = state.editModel.timeRange,
                        duration = state.editModel.duration,
                        onTimeRangeChange = onTimeRangeChange,
                    )
                    HorizontalDivider(Modifier.padding(horizontal = 32.dp))
                    ParametersSection(
                        enabled = !state.editModel.checkDateIsRepeat(),
                        parameters = state.editModel.parameters,
                        onChangeParameters = onChangeParameters,
                    )
                }
                ActionButtonsSection(
                    enableTemplateSelector = state.editModel.key != 0L,
                    isRepeatTemplate = state.editModel.checkDateIsRepeat(),
                    isTemplateSelect = state.editModel.templateId != null,
                    onCancelClick = onCancelClick,
                    onControl = onControlTemplate,
                    onCreateTemplate = onCreateTemplate,
                    onSaveClick = onSaveClick,
                )
            }
        }
    }
}

@Composable
internal fun CategoriesSection(
    modifier: Modifier = Modifier,
    enabledCategories: Boolean = true,
    enabledNote: Boolean = true,
    isMainCategoryValidError: Boolean,
    mainCategory: MainCategoryHome?,
    subCategory: SubCategoryHome?,
    allCategories: List<CategoriesHome>,
    note: String?,
    onEditCategory: (MainCategoryHome) -> Unit,
    onEditSubCategory: (SubCategoryHome) -> Unit,
    onCategoriesChange: (MainCategoryHome, SubCategoryHome?) -> Unit,
    onAddSubCategory: (String) -> Unit,
    onNoteChange: (String?) -> Unit,
) {
    val focusManager = LocalFocusManager.current
    val noteInteractionSource = remember { MutableInteractionSource() }
    var editableNote by remember {
        mutableStateOf(TextFieldValue(text = note ?: ""))
    }

    Column(
        modifier = modifier.padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Column(
            modifier = Modifier.animateContentSize(),
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            MainCategoryChooser(
                enabled = enabledCategories,
                modifier = Modifier.fillMaxWidth(),
                isError = isMainCategoryValidError,
                currentCategory = mainCategory,
                allCategories = allCategories.map { it.mainCategory },
                onEditCategory = onEditCategory,
                onChangeCategory = { newMainCategory ->
                    onCategoriesChange(newMainCategory, null)
                },
            )
            if (isMainCategoryValidError) {
                Text(
                    text = HomeTheme.strings.categoryValidateError.string(),
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.error,
                )
            }
        }
        val findCategories = allCategories.find { it.mainCategory == mainCategory }
        SubCategoryChooser(
            enabled = enabledCategories,
            modifier = Modifier.fillMaxWidth(),
            mainCategory = mainCategory,
            allSubCategories = findCategories?.subCategories ?: emptyList(),
            currentSubCategory = subCategory,
            onAddSubCategory = onAddSubCategory,
            onEditSubCategory = onEditSubCategory,
            onChangeSubCategory = { newSubCategory ->
                if (mainCategory != null) onCategoriesChange(mainCategory, newSubCategory)
            },
        )
        CustomLargeTextField(
            enabled = enabledNote,
            text = editableNote,
            onTextChange = {
                if (it.text.length <= Constants.Text.MAX_NOTE_LENGTH) {
                    editableNote = it
                    onNoteChange(editableNote.text.ifEmpty { null })
                }
            },
            label = { Text(text = HomeTheme.strings.noteLabel.string()) },
            placeholder = { Text(text = HomeTheme.strings.notePlaceholder.string()) },
            leadingIcon = {
                Icon(
                    painter = painterResource(HomeTheme.icons.notesField),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface,
                )
            },
            maxLines = 4,
            trailingIcon = if (noteInteractionSource.collectIsFocusedAsState().value) {
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
            interactionSource = noteInteractionSource,
        )
    }
}

@Composable
internal fun DateTimeSection(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    isTimeValidError: Boolean,
    timeRanges: TimeRange,
    duration: Long,
    onTimeRangeChange: (TimeRange) -> Unit,
) {
    Row(
        modifier = modifier.padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        StartTimeField(
            enabled = enabled,
            modifier = Modifier.weight(1f),
            currentTime = timeRanges.from,
            isError = isTimeValidError,
            onChangeTime = { newStartTime ->
                if (newStartTime <= timeRanges.to) {
                    onTimeRangeChange(timeRanges.copy(from = newStartTime))
                } else {
                    onTimeRangeChange(timeRanges.copy(from = newStartTime, to = timeRanges.to.shiftDays(1)))
                }
            },
        )
        EndTimeField(
            enabled = enabled,
            modifier = Modifier.weight(1f),
            currentTime = timeRanges.to,
            isError = isTimeValidError,
            onChangeTime = { newEndTime ->
                val newTime = if (newEndTime >= timeRanges.from) newEndTime else newEndTime.shiftDays(1)
                onTimeRangeChange(timeRanges.copy(to = newTime))
            },
        )
        DurationTitle(
            enabled = enabled,
            duration = duration,
            startTime = timeRanges.from,
            isError = isTimeValidError,
            onChangeDuration = { duration ->
                onTimeRangeChange(timeRanges.copy(to = timeRanges.from.shiftMillis(duration.toInt())))
            },
        )
    }
}

@Composable
internal fun ParametersSection(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    parameters: EditParameters,
    onChangeParameters: (EditParameters) -> Unit,
) {
    var openTaskNotificationMenu by remember { mutableStateOf(false) }
    Column(
        modifier = modifier.padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        ParameterChooser(
            enabled = enabled,
            selected = parameters.isConsiderInStatistics,
            leadingIcon = painterResource(HomeTheme.icons.statistics),
            title = HomeTheme.strings.statisticsParameterTitle.string(),
            description = HomeTheme.strings.statisticsParameterDesc.string(),
            onChangeSelected = { isConsider ->
                onChangeParameters(parameters.copy(isConsiderInStatistics = isConsider))
            },
        )
        ParameterChooser(
            enabled = enabled,
            selected = parameters.isEnableNotification,
            leadingIcon = painterResource(HomeTheme.icons.notifications),
            title = HomeTheme.strings.notifyParameterTitle.string(),
            description = HomeTheme.strings.notifyParameterDesc.string(),
            optionsButton = if (parameters.isEnableNotification) {
                {
                    Box {
                        IconButton(
                            modifier = Modifier.size(32.dp),
                            onClick = { openTaskNotificationMenu = true },
                            colors = IconButtonDefaults.iconButtonColors(
                                containerColor = MaterialTheme.colorScheme.surfaceVariant,
                                contentColor = MaterialTheme.colorScheme.onSurfaceVariant,
                            ),
                        ) {
                            Icon(imageVector = Icons.Default.Settings, contentDescription = null)
                        }
                        TaskNotificationsMenu(
                            isExpanded = openTaskNotificationMenu,
                            taskNotification = parameters.taskNotifications,
                            onDismiss = { openTaskNotificationMenu = false },
                            onUpdate = { onChangeParameters(parameters.copy(taskNotifications = it)) },
                        )
                    }
                }
            } else {
                null
            },
            onChangeSelected = { notification ->
                onChangeParameters(parameters.copy(isEnableNotification = notification))
            },
        )
        SegmentedParametersChooser(
            enabled = enabled,
            parameters = PriorityParameters.entries.toTypedArray(),
            selected = parameters.priority.convertToParameter(),
            leadingIcon = painterResource(HomeTheme.icons.priority),
            title = HomeTheme.strings.priorityParameterTitle.string(),
            onChangeSelected = { priority ->
                onChangeParameters(parameters.copy(priority = priority.convertToModel()))
            },
        )
    }
}

@Composable
internal fun ActionButtonsSection(
    modifier: Modifier = Modifier,
    enableTemplateSelector: Boolean,
    isRepeatTemplate: Boolean,
    isTemplateSelect: Boolean,
    onControl: () -> Unit,
    onCreateTemplate: () -> Unit,
    onCancelClick: () -> Unit,
    onSaveClick: () -> Unit,
) {
    Box(modifier = modifier, contentAlignment = Alignment.BottomStart) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            FilledTonalButton(
                onClick = onCancelClick,
                colors = ButtonDefaults.filledTonalButtonColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                ),
                content = { Text(text = HomeTheme.strings.cancelButtonTitle.string()) },
            )
            Button(
                onClick = onSaveClick,
                content = { Text(text = HomeTheme.strings.saveTaskButtonTitle.string()) },
            )
            Spacer(modifier = Modifier.weight(1f))
            if (enableTemplateSelector) {
                TemplateSelector(
                    isSelect = isTemplateSelect,
                    isRepeat = isRepeatTemplate,
                    onControl = onControl,
                    onCreateTemplate = onCreateTemplate,
                )
            }
        }
    }
}

@Composable
internal fun TemplateSelector(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    isSelect: Boolean,
    isRepeat: Boolean,
    onControl: () -> Unit,
    onCreateTemplate: () -> Unit,
) {
    IconButton(
        onClick = { if (isSelect) onControl() else onCreateTemplate() },
        modifier = modifier.size(40.dp).background(
            color = MaterialTheme.colorScheme.primaryContainer,
            shape = RoundedCornerShape(40.dp),
        ),
        enabled = enabled,
    ) {
        val templatesButton = when (isSelect) {
            true -> when (isRepeat) {
                true -> HomeTheme.icons.repeat
                false -> AppTheme.icons.enabledSettingsIcon
            }

            false -> HomeTheme.icons.unFavorite
        }
        Icon(
            painter = painterResource(templatesButton),
            contentDescription = HomeTheme.strings.templateIconDesc.string(),
            tint = MaterialTheme.colorScheme.onPrimaryContainer,
        )
    }
}