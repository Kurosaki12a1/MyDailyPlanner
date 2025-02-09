package com.kuro.mdp.features.settings.presentation.ui.template.components

import androidx.compose.foundation.background
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun TemplatesTopAppBar(
    modifier: Modifier = Modifier,
    onButtonClick: () -> Unit,
) {
    TopAppBar(
        modifier = modifier.background(MaterialTheme.colorScheme.background),
        title = {
            TopAppBarTitle(
                text = SettingsTheme.strings.topAppBarTemplatesTitle.string(),
                textAlign = TextAlign.Center,
            )
        },
        navigationIcon = {
            TopAppBarButton(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                imageDescription = null,
                onButtonClick = onButtonClick,
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
