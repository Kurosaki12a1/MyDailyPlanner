package com.kuro.mdp.features.settings.presentation.ui.categories.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.kuro.mdp.features.settings.domain.model.categories.MainCategoryUi
import com.kuro.mdp.features.settings.presentation.extension.fetchName
import com.kuro.mdp.features.settings.presentation.theme.SettingsTheme
import com.kuro.mdp.shared.presentation.extensions.string
import com.kuro.mdp.shared.presentation.mappers.mapToIconPainter
import com.kuro.mdp.shared.presentation.theme.AppTheme
import com.kuro.mdp.shared.presentation.views.CategoryIconMonogram
import com.kuro.mdp.shared.presentation.views.CategoryTextMonogram
import com.kuro.mdp.shared.presentation.views.DialogButtons
import com.kuro.mdp.shared.utils.functional.Constants

@Composable
@OptIn(ExperimentalMaterial3Api::class)
internal fun MainCategoryEditorDialog(
    modifier: Modifier = Modifier,
    editCategory: MainCategoryUi? = null,
    onDismiss: () -> Unit,
    onConfirm: (name: String) -> Unit,
) {
    var isError by rememberSaveable { mutableStateOf(false) }
    val categoryName = editCategory?.fetchName()
    val textRange = TextRange(categoryName?.length ?: 0)
    var mainCategoryNameValue by remember {
        mutableStateOf(TextFieldValue(text = categoryName ?: "", selection = textRange))
    }
    BasicAlertDialog(onDismissRequest = onDismiss) {
        Surface(
            modifier = modifier.width(328.dp).wrapContentHeight(),
            shape = MaterialTheme.shapes.extraLarge,
            color = MaterialTheme.colorScheme.surfaceContainer,
        ) {
            Column {
                MainCategoryEditorDialogHeader()
                HorizontalDivider()
                Row(
                    modifier = Modifier.padding(
                        start = 16.dp,
                        end = 16.dp,
                        top = 16.dp,
                        bottom = 0.dp
                    ),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    if (editCategory?.defaultType?.mapToIconPainter() != null) {
                        CategoryIconMonogram(
                            icon = checkNotNull(editCategory.defaultType).mapToIconPainter(),
                            iconDescription = editCategory.customName,
                            iconColor = MaterialTheme.colorScheme.primary,
                            backgroundColor = MaterialTheme.colorScheme.primaryContainer,
                        )
                    } else {
                        CategoryTextMonogram(
                            text = mainCategoryNameValue.text.firstOrNull()?.toString() ?: "-",
                            textColor = MaterialTheme.colorScheme.primary,
                            backgroundColor = MaterialTheme.colorScheme.primaryContainer,
                        )
                    }
                    CategoryDialogField(
                        modifier = Modifier.fillMaxWidth(),
                        categoryNameValue = mainCategoryNameValue,
                        isError = isError,
                        onNameChange = { nameValue -> mainCategoryNameValue = nameValue },
                    )
                }
                DialogButtons(
                    confirmTitle = when (editCategory != null) {
                        true -> AppTheme.strings.okConfirmTitle.string()
                        false -> SettingsTheme.strings.dialogCreateTitle.string()
                    },
                    onConfirmClick = {
                        val text = mainCategoryNameValue.text
                        if (text.isNotEmpty() && text.length < Constants.Text.MAX_LENGTH) {
                            onConfirm(mainCategoryNameValue.text)
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
internal fun MainCategoryEditorDialogHeader(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(top = 24.dp, bottom = 12.dp, start = 24.dp, end = 24.dp),
        verticalArrangement = Arrangement.spacedBy(2.dp),
    ) {
        Text(
            text = SettingsTheme.strings.mainCategoryChooserTitle.string(),
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.headlineSmall,
        )
    }
}
