package com.kuro.mdp.features.settings.presentation.ui.donate.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.kuro.mdp.features.settings.presentation.theme.SettingsTheme
import com.kuro.mdp.shared.presentation.extensions.string
import com.kuro.mdp.shared.presentation.views.TopAppBarButton
import com.kuro.mdp.shared.presentation.views.TopAppBarEmptyButton
import com.kuro.mdp.shared.presentation.views.TopAppBarTitle

/**
 * Created by: minhthinh.h on 1/14/2025
 *
 * Description:
 */
@Composable
@OptIn(ExperimentalMaterial3Api::class)
internal fun DonateTopAppBar(
    modifier: Modifier = Modifier,
    onNavButtonClick: () -> Unit,
) {
    TopAppBar(
        modifier = modifier,
        title = {
            TopAppBarTitle(
                text = SettingsTheme.strings.donateHeader.string(),
                textAlign = TextAlign.Center,
            )
        },
        navigationIcon = {
            TopAppBarButton(
                imageVector = Icons.Default.ArrowBack,
                imageDescription = SettingsTheme.strings.backIconDesc.string(),
                onButtonClick = onNavButtonClick,
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
