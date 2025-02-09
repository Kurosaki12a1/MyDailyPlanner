package com.kuro.mdp.features.settings.presentation.ui.settings.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.kuro.mdp.features.settings.presentation.theme.SettingsTheme
import com.kuro.mdp.shared.presentation.extensions.scrollText
import com.kuro.mdp.shared.presentation.extensions.string
import com.kuro.mdp.shared.utils.functional.Constants
import org.jetbrains.compose.resources.painterResource

/**
 * Created by: minhthinh.h on 12/26/2024
 *
 * Description:
 */
@Composable
internal fun AboutAppSection(
    modifier: Modifier = Modifier,
    onOpenGit: () -> Unit,
    onOpenIssues: () -> Unit,
) {
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Text(
            text = SettingsTheme.strings.aboutAppHeader.string(),
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            style = MaterialTheme.typography.labelMedium,
        )
        AboutAppSectionVersion()
        AboutAppSectionDevelopment(
            onOpenGit = onOpenGit,
            onOpenIssues = onOpenIssues,
        )
    }
}

@Composable
internal fun AboutAppSectionVersion(
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier,
        shape = MaterialTheme.shapes.large,
        color = MaterialTheme.colorScheme.surfaceContainer,
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            InfoView(
                title = SettingsTheme.strings.versionNameTitle.string(),
                text = "1.0", // TODO common BuildConfig.VersionCode
            )
            Spacer(modifier = Modifier.weight(1f))
            InfoView(
                title = SettingsTheme.strings.versionCodeTitle.string(),
                text = "1.0",
            )
        }
    }
}

@Composable
internal fun AboutAppSectionDevelopment(
    modifier: Modifier = Modifier,
    onOpenGit: () -> Unit,
    onOpenIssues: () -> Unit,
) {
    Surface(
        modifier = modifier,
        shape = MaterialTheme.shapes.large,
        color = MaterialTheme.colorScheme.surfaceContainer,
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                InfoView(
                    modifier = Modifier.fillMaxWidth(),
                    title = SettingsTheme.strings.developerTitle.string(),
                    spaceInside = true,
                    text = Constants.App.DEVELOPER,
                )
                InfoView(
                    modifier = Modifier.fillMaxWidth(),
                    title = SettingsTheme.strings.licenseTitle.string(),
                    spaceInside = true,
                    text = Constants.App.LICENCE,
                )
            }
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                FilterChip(
                    modifier = Modifier.weight(1f),
                    selected = true,
                    onClick = onOpenIssues,
                    label = {
                        Text(
                            modifier = Modifier.fillMaxWidth().scrollText(),
                            text = SettingsTheme.strings.askQuestionTitle.string(),
                            textAlign = TextAlign.Center,
                        )
                    },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                        disabledSelectedContainerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f),
                        selectedLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        selectedLeadingIconColor = MaterialTheme.colorScheme.onSurface,
                        selectedTrailingIconColor = MaterialTheme.colorScheme.onSurface,
                    )
                )
                FilterChip(
                    modifier = Modifier.weight(1f),
                    selected = true,
                    onClick = onOpenGit,
                    label = {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = SettingsTheme.strings.githubTitle.string(),
                            textAlign = TextAlign.Center,
                        )
                    },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(SettingsTheme.icons.git),
                            contentDescription = null,
                        )
                    },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                        disabledSelectedContainerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f),
                        selectedLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        selectedLeadingIconColor = MaterialTheme.colorScheme.onSurface,
                        selectedTrailingIconColor = MaterialTheme.colorScheme.onSurface,
                    )
                )
            }
        }
    }
}

@Composable
internal fun InfoView(
    modifier: Modifier = Modifier,
    spaceInside: Boolean = false,
    title: String,
    text: String,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        Text(
            text = title,
            color = MaterialTheme.colorScheme.onSurface,
            maxLines = 1,
            style = MaterialTheme.typography.titleMedium,
        )
        if (spaceInside) Spacer(modifier = Modifier.weight(1f))
        Text(
            text = text,
            color = MaterialTheme.colorScheme.primary,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.titleMedium,
        )
    }
}
