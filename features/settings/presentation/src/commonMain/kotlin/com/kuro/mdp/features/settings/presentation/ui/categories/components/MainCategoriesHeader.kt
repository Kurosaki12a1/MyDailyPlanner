package com.kuro.mdp.features.settings.presentation.ui.categories.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kuro.mdp.features.settings.presentation.theme.SettingsTheme
import com.kuro.mdp.shared.presentation.extensions.string

@Composable
internal fun MainCategoriesHeader(
    modifier: Modifier = Modifier,
    onRestoreDefaultCategories: () -> Unit,
) {
    var isOpenParametersMenu by rememberSaveable { mutableStateOf(false) }

    Row(
        modifier = modifier.padding(start = 16.dp, end = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            modifier = Modifier.padding(end = 8.dp),
            text = SettingsTheme.strings.mainCategoryTitle.string(),
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.titleMedium,
        )
        Spacer(modifier = Modifier.weight(1f))
        IconButton(
            modifier = Modifier.size(24.dp),
            onClick = { isOpenParametersMenu = true },
        ) {
            Icon(
                modifier = Modifier.size(18.dp),
                imageVector = Icons.Default.MoreVert,
                contentDescription = null,
            )
            CategoriesParametersMenu(
                expanded = isOpenParametersMenu,
                onDismiss = { isOpenParametersMenu = false },
                onRestoreDefaultCategories = {
                    onRestoreDefaultCategories()
                    isOpenParametersMenu = false
                },
            )
        }
    }
}
