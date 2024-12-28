package com.kuro.mdp.features.settings.presentation.ui.settings.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign
import com.kuro.mdp.features.settings.presentation.theme.SettingsTheme
import com.kuro.mdp.shared.presentation.theme.AppTheme
import com.kuro.mdp.shared.presentation.views.TopAppBarAction
import com.kuro.mdp.shared.presentation.views.TopAppBarButton
import com.kuro.mdp.shared.presentation.views.TopAppBarMoreActions
import com.kuro.mdp.shared.presentation.views.TopAppBarTitle
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
    onMenuButtonClick: () -> Unit
) {
    TopAppBar(
        title = {
            TopAppBarTitle(
                text = SettingsTheme.strings.settingsTitle.string(),
                textAlign = TextAlign.Center,
            )
        },
        navigationIcon = {
            TopAppBarButton(
                imageVector = Icons.Default.Menu,
                imageDescription = null,
                onButtonClick = onMenuButtonClick,
            )
        },
        actions = {
            TopAppBarMoreActions(
                items = SettingsMoreActions.entries.toTypedArray(),
                moreIconDescription = SettingsTheme.strings.moreIconDesc.string(),
                onItemClick = { action ->
                    when (action) {
                        SettingsMoreActions.RESET_TO_DEFAULT -> onResetToDefaultClick.invoke()
                    }
                },
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.background,
        )
    )
}

internal enum class SettingsMoreActions : TopAppBarAction {
    RESET_TO_DEFAULT {
        override val title: String @Composable get() = SettingsTheme.strings.resetToDefaultTitle.string()
        override val icon: DrawableResource @Composable get() = AppTheme.icons.reset
        override val isAlwaysShow: Boolean = false
    }
}