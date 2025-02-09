package com.kuro.mdp.features.settings.presentation.ui.settings.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import com.kuro.mdp.features.settings.domain.model.settings.TasksSettingsUi
import com.kuro.mdp.features.settings.domain.model.settings.ThemeSettingsUi
import com.kuro.mdp.features.settings.presentation.theme.SettingsTheme
import com.kuro.mdp.features.settings.presentation.ui.settings.SettingsViewState
import com.kuro.mdp.shared.domain.model.settings.CalendarButtonBehavior
import com.kuro.mdp.shared.presentation.LanguageUiType
import com.kuro.mdp.shared.presentation.extensions.string
import com.kuro.mdp.shared.presentation.theme.ColorsUiType
import com.kuro.mdp.shared.presentation.theme.ThemeUiType
import com.kuro.mdp.shared.utils.DevicePlatform
import com.kuro.mdp.shared.utils.functional.Constants
import com.kuro.mdp.shared.utils.getPlatform

/**
 * Created by: minhthinh.h on 12/26/2024
 *
 * Description:
 */
@Composable
internal fun SettingsContent(
    state: SettingsViewState,
    modifier: Modifier = Modifier,
    onUpdateThemeSettings: (ThemeSettingsUi) -> Unit,
    onUpdateTasksSettings: (TasksSettingsUi) -> Unit,
    onDonateButtonClick: () -> Unit,
) {
    if (state.themeSettings != null && state.tasksSettings != null) {
        val uriHandler = LocalUriHandler.current
        val scrollState = rememberLazyListState()
        LazyColumn(
            state = scrollState,
            modifier = modifier.fillMaxSize().padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    MainSettingsSection(
                        languageType = state.themeSettings.language,
                        themeColors = state.themeSettings.themeColors,
                        colorsType = state.themeSettings.colorsType,
                        dynamicColor = state.themeSettings.isDynamicColorEnable,
                        onThemeColorUpdate = { colorsType ->
                            onUpdateThemeSettings(state.themeSettings.copy(themeColors = colorsType))
                        },
                        onLanguageChange = { language ->
                            onUpdateThemeSettings(state.themeSettings.copy(language = language))
                        },
                        onColorsTypeUpdate = { colorsType ->
                            onUpdateThemeSettings(state.themeSettings.copy(colorsType = colorsType))
                        },
                        onDynamicColorsChange = {
                            onUpdateThemeSettings(state.themeSettings.copy(isDynamicColorEnable = it))
                        },
                    )
                    HorizontalDivider(modifier = Modifier.padding(top = 8.dp))
                }
            }
            item {
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    InterfaceSettingsSection(
                        calendarButtonBehavior = state.tasksSettings.calendarButtonBehavior,
                        onUpdateCalendarBehavior = {
                            onUpdateTasksSettings(state.tasksSettings.copy(calendarButtonBehavior = it))
                        },
                    )
                    HorizontalDivider(modifier = Modifier.padding(top = 8.dp))
                }
            }
            item {
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    SecureSettingsSection(
                        secureMode = state.tasksSettings.secureMode,
                        onUpdateSecureMode = {
                            onUpdateTasksSettings(state.tasksSettings.copy(secureMode = it))
                        },
                    )
                    HorizontalDivider(modifier = Modifier.padding(top = 8.dp))
                }
            }
            item {
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    AboutAppSection(
                        onOpenGit = {
                            uriHandler.openUri(Constants.App.GITHUB_URI)
                        },
                        onOpenIssues = {
                            uriHandler.openUri(Constants.App.ISSUES_URI)
                        },
                    )
                    DonateSection(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = onDonateButtonClick,
                    )
                }
            }
        }
    }
}

@Composable
internal fun MainSettingsSection(
    modifier: Modifier = Modifier,
    languageType: LanguageUiType,
    themeColors: ThemeUiType,
    colorsType: ColorsUiType,
    dynamicColor: Boolean,
    onLanguageChange: (LanguageUiType) -> Unit,
    onThemeColorUpdate: (ThemeUiType) -> Unit,
    onColorsTypeUpdate: (ColorsUiType) -> Unit,
    onDynamicColorsChange: (Boolean) -> Unit,
) {
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Text(
            text = SettingsTheme.strings.mainSettingsTitle.string(),
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            style = MaterialTheme.typography.labelMedium,
        )
        ThemeColorsChooser(
            modifier = Modifier.fillMaxWidth(),
            themeColors = themeColors,
            onThemeColorUpdate = onThemeColorUpdate,
        )
        ColorsTypeChooser(
            colorsType = colorsType,
            onChoose = onColorsTypeUpdate,
        )
        if (getPlatform() == DevicePlatform.ANDROID) {
            DynamicColorChooser(
                dynamicColor = dynamicColor,
                onChange = onDynamicColorsChange,
            )
        }
        LanguageChooser(
            language = languageType,
            onLanguageChanged = onLanguageChange,
        )
    }
}

@Composable
internal fun InterfaceSettingsSection(
    modifier: Modifier = Modifier,
    calendarButtonBehavior: CalendarButtonBehavior,
    onUpdateCalendarBehavior: (CalendarButtonBehavior) -> Unit,
) {
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Text(
            text = SettingsTheme.strings.interfaceSectionHeader.string(),
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            style = MaterialTheme.typography.labelMedium,
        )
        CalendarButtonBehaviorChooser(
            calendarButtonBehavior = calendarButtonBehavior,
            onUpdateCalendarBehavior = onUpdateCalendarBehavior,
        )
    }
}

@Composable
internal fun SecureSettingsSection(
    modifier: Modifier = Modifier,
    secureMode: Boolean,
    onUpdateSecureMode: (Boolean) -> Unit,
) {
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Text(
            text = SettingsTheme.strings.secureSectionHeader.string(),
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            style = MaterialTheme.typography.labelMedium,
        )
        Surface(
            modifier = modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.medium,
            color = MaterialTheme.colorScheme.surfaceContainer,
        ) {
            Row(
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = SettingsTheme.strings.secureModeTitle.string(),
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.titleMedium,
                )
                Switch(checked = secureMode, onCheckedChange = onUpdateSecureMode)
            }
        }
    }
}