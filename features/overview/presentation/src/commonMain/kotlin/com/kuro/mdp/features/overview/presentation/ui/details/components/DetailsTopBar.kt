package com.kuro.mdp.features.overview.presentation.ui.details.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign
import com.kuro.mdp.features.overview.presentation.theme.OverViewTheme
import com.kuro.mdp.shared.presentation.extensions.string
import com.kuro.mdp.shared.presentation.views.TopAppBarButton
import com.kuro.mdp.shared.presentation.views.TopAppBarEmptyButton
import com.kuro.mdp.shared.presentation.views.TopAppBarTitle

/**
 * Created by: minhthinh.h on 2/18/2025
 *
 * Description:
 */
@Composable
@OptIn(ExperimentalMaterial3Api::class)
internal fun DetailsTopAppBar(
    onNavIconClick: () -> Unit,
) {
    TopAppBar(
        title = {
            TopAppBarTitle(
                text = OverViewTheme.strings.schedulesHeader.string(),
                textAlign = TextAlign.Center,
            )
        },
        navigationIcon = {
            TopAppBarButton(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                imageDescription = OverViewTheme.strings.navToBackTitle.string(),
                onButtonClick = onNavIconClick,
            )
        },
        actions = {
            TopAppBarEmptyButton()
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.background,
        ),
    )
}
