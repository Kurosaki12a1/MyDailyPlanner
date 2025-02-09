package com.kuro.mdp.features.home.presentation.ui.home.ui.editor.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Badge
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign
import com.kuro.mdp.features.home.presentation.ui.home.theme.HomeTheme
import com.kuro.mdp.shared.presentation.extensions.string
import com.kuro.mdp.shared.presentation.theme.AppTheme
import com.kuro.mdp.shared.presentation.views.TopAppBarAction
import com.kuro.mdp.shared.presentation.views.TopAppBarButton
import com.kuro.mdp.shared.presentation.views.TopAppBarEmptyButton
import com.kuro.mdp.shared.presentation.views.TopAppBarMoreActions
import com.kuro.mdp.shared.presentation.views.TopAppBarTitle
import org.jetbrains.compose.resources.painterResource

/**
 * Created by: minhthinh.h on 2/7/2025
 *
 * Description:
 */
@Composable
@OptIn(ExperimentalMaterial3Api::class)
internal fun EditorTopAppBar(
    actionsEnabled: Boolean = true,
    countUndefinedTasks: Int,
    onBackIconClick: () -> Unit,
    onOpenUndefinedTasks: () -> Unit,
    onDeleteActionClick: () -> Unit,
    onTemplatesActionClick: () -> Unit,
) {
    TopAppBar(
        title = {
            TopAppBarTitle(
                text = HomeTheme.strings.topAppBarEditorTitle.string(),
                textAlign = TextAlign.Center,
            )
        },
        navigationIcon = {
            TopAppBarButton(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                imageDescription = HomeTheme.strings.topAppBarBackIconDesc.string(),
                onButtonClick = onBackIconClick,
            )
            TopAppBarEmptyButton()
        },
        actions = {
            if (actionsEnabled) {
                TopAppBarButton(
                    imagePainter = painterResource(AppTheme.icons.plannedTask),
                    imageDescription = null,
                    onButtonClick = onOpenUndefinedTasks,
                    badge = if (countUndefinedTasks > 0) {
                        {
                            Badge { Text(text = countUndefinedTasks.toString()) }
                        }
                    } else {
                        null
                    },
                )
                TopAppBarButton(
                    imagePainter = painterResource(HomeTheme.icons.templates),
                    imageDescription = HomeTheme.strings.topAppBarTemplatesTitle.string(),
                    onButtonClick = onTemplatesActionClick,
                )
                TopAppBarMoreActions(
                    items = EditorTopAppBarActions.entries.toTypedArray(),
                    onItemClick = {
                        when (it) {
                            EditorTopAppBarActions.DELETE -> onDeleteActionClick()
                        }
                    },
                    moreIconDescription = null,
                )
            } else {
                TopAppBarEmptyButton()
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.background,
        ),
    )
}

internal enum class EditorTopAppBarActions : TopAppBarAction {
    DELETE {
        override val icon @Composable get() = HomeTheme.icons.delete
        override val title @Composable get() = HomeTheme.strings.topAppBarDeleteTitle.string()
        override val isAlwaysShow get() = false
    },
}