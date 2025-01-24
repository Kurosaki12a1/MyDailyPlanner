package com.kuro.mdp.features.settings.presentation.ui.settings.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.kuro.mdp.features.settings.presentation.theme.SettingsTheme
import com.kuro.mdp.shared.presentation.theme.AppTheme
import com.kuro.mdp.shared.presentation.views.TopAppBarAction
import com.kuro.mdp.shared.presentation.views.TopAppBarMoreActions
import com.kuro.mdp.shared.utils.extensions.string
import org.jetbrains.compose.resources.DrawableResource

/**
 * Created by: minhthinh.h on 12/26/2024
 *
 * Description:
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun SettingsTopAppBar(
    onResetToDefaultClick: () -> Unit,
    onCategoryClick: () -> Unit,
    onTemplateClick: () -> Unit,
) {
    TopAppBar(
        title = {
            Box(modifier = Modifier.fillMaxWidth()) {
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = SettingsTheme.strings.settingsTitle.string(),
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.titleLarge,
                )
                TopAppBarMoreActions(
                    modifier = Modifier.align(Alignment.CenterEnd),
                    items = SettingsMoreActions.entries.toTypedArray(),
                    moreIconDescription = SettingsTheme.strings.moreIconDesc.string(),
                    onItemClick = { action ->
                        when (action) {
                            SettingsMoreActions.RESET_TO_DEFAULT -> onResetToDefaultClick.invoke()
                            SettingsMoreActions.OPEN_CATEGORY -> onCategoryClick.invoke()
                            SettingsMoreActions.OPEN_TEMPLATE -> onTemplateClick.invoke()
                        }
                    },
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.background,
        )
    )
}

internal enum class SettingsMoreActions : TopAppBarAction {
    OPEN_CATEGORY {
        override val title: String @Composable get() = AppTheme.strings.categoriesDrawerTitle.string()
        override val icon: DrawableResource @Composable get() = AppTheme.icons.categoriesIcon
        override val isAlwaysShow: Boolean = false

    },
    OPEN_TEMPLATE {
        override val title: String @Composable get() = AppTheme.strings.templateDrawerTitle.string()
        override val icon: DrawableResource @Composable get() = AppTheme.icons.template
        override val isAlwaysShow: Boolean = false
    },
    RESET_TO_DEFAULT {
        override val title: String @Composable get() = SettingsTheme.strings.resetToDefaultTitle.string()
        override val icon: DrawableResource @Composable get() = AppTheme.icons.reset
        override val isAlwaysShow: Boolean = false
    }
}