package com.kuro.mdp.features.overview.presentation.ui.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign
import com.kuro.mdp.features.overview.presentation.theme.OverViewTheme
import com.kuro.mdp.shared.presentation.views.TopAppBarButton
import com.kuro.mdp.shared.presentation.views.TopAppBarTitle
import com.kuro.mdp.shared.utils.extensions.string
import org.jetbrains.compose.resources.painterResource

/**
 * Created by: minhthinh.h on 1/21/2025
 *
 * Description:
 */
@Composable
@OptIn(ExperimentalMaterial3Api::class)
internal fun OverviewTopAppBar(
    onMenuIconClick: () -> Unit,
    onOpenSchedule: () -> Unit,
) {
    TopAppBar(
        title = {
            TopAppBarTitle(
                text = OverViewTheme.strings.topAppBarOverviewTitle.string(),
                textAlign = TextAlign.Center,
            )
        },
        navigationIcon = {
            TopAppBarButton(
                imageVector = Icons.Default.Menu,
                imageDescription = OverViewTheme.strings.topAppBarMenuIconDesc.string(),
                onButtonClick = onMenuIconClick,
            )
        },
        actions = {
            TopAppBarButton(
                imagePainter = painterResource(OverViewTheme.icons.schedule),
                imageDescription = OverViewTheme.strings.topAppBarHomeTitle.string(),
                onButtonClick = onOpenSchedule,
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.background,
        ),
    )
}
