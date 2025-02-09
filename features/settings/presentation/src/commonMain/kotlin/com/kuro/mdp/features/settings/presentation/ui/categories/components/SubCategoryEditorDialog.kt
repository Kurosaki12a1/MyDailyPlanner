package com.kuro.mdp.features.settings.presentation.ui.categories.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.kuro.mdp.features.settings.domain.model.categories.MainCategoryUi
import com.kuro.mdp.features.settings.domain.model.categories.SubCategoryUi
import com.kuro.mdp.features.settings.presentation.extension.fetchName
import com.kuro.mdp.features.settings.presentation.theme.SettingsTheme
import com.kuro.mdp.shared.presentation.extensions.string
import com.kuro.mdp.shared.presentation.theme.AppTheme
import com.kuro.mdp.shared.presentation.views.DialogButtons
import com.kuro.mdp.shared.utils.format

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun SubCategoryEditorDialog(
    modifier: Modifier = Modifier,
    mainCategory: MainCategoryUi,
    editSubCategory: SubCategoryUi? = null,
    onDismiss: () -> Unit,
    onConfirm: (name: String) -> Unit,
) {
    var isError by rememberSaveable { mutableStateOf(false) }
    val subCategoryName = editSubCategory?.name
    val textRange = TextRange(subCategoryName?.length ?: 0)
    var subCategoryNameValue by remember {
        mutableStateOf(TextFieldValue(text = subCategoryName ?: "", selection = textRange))
    }
    BasicAlertDialog(onDismissRequest = onDismiss) {
        Surface(
            modifier = modifier.width(328.dp).wrapContentHeight(),
            shape = MaterialTheme.shapes.extraLarge,
            color = MaterialTheme.colorScheme.surfaceContainer,
        ) {
            Column {
                SubCategoryEditorDialogHeader(
                    mainCategory = mainCategory,
                )
                HorizontalDivider()
                CategoryDialogField(
                    modifier = Modifier.fillMaxWidth()
                        .padding(top = 18.dp, bottom = 8.dp, start = 24.dp, end = 24.dp),
                    categoryNameValue = subCategoryNameValue,
                    isError = isError,
                    onNameChange = { nameValue -> subCategoryNameValue = nameValue },
                )
                DialogButtons(
                    confirmTitle = when (editSubCategory != null) {
                        true -> AppTheme.strings.okConfirmTitle.string()
                        false -> SettingsTheme.strings.dialogCreateTitle.string()
                    },
                    onConfirmClick = {
                        val text = subCategoryNameValue.text
                        if (text.isNotEmpty() && text.length < 100) {
                            onConfirm(subCategoryNameValue.text)
                        } else {
                            isError = true
                        }
                    },
                    onCancelClick = onDismiss,
                )
            }
        }
    }
}

@Composable
internal fun SubCategoryEditorDialogHeader(
    modifier: Modifier = Modifier,
    mainCategory: MainCategoryUi,
) {
    Column(
        modifier = modifier.padding(top = 24.dp, bottom = 12.dp, start = 24.dp, end = 12.dp),
        verticalArrangement = Arrangement.spacedBy(2.dp),
    ) {
        val mainCategoryName = mainCategory.fetchName() ?: "*"
        Text(
            text = SettingsTheme.strings.subCategoryChooserTitle.string(),
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.headlineSmall,
        )
        Text(
            text = SettingsTheme.strings.subCategoryDialogMainCategoryFormat.string()
                .format(mainCategoryName),
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}

@Composable
internal fun CategoryDialogField(
    modifier: Modifier = Modifier,
    isError: Boolean,
    categoryNameValue: TextFieldValue,
    onNameChange: (TextFieldValue) -> Unit,
) {
    val requester = remember { FocusRequester() }
    Box(modifier = modifier) {
        OutlinedTextField(
            modifier = Modifier.focusRequester(requester),
            value = categoryNameValue,
            onValueChange = onNameChange,
            singleLine = true,
            isError = isError,
            label = { Text(text = SettingsTheme.strings.categoryFieldLabel.string()) },
            shape = MaterialTheme.shapes.large,
        )
    }
    LaunchedEffect(
        key1 = Unit,
        block = { requester.requestFocus() },
    )
}