package com.kuro.mdp.features.settings.presentation.ui.template.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.kuro.mdp.features.settings.domain.model.categories.CategoriesUi
import com.kuro.mdp.features.settings.domain.model.template.TemplateUi
import com.kuro.mdp.features.settings.domain.model.template.TemplatesSortedType
import com.kuro.mdp.features.settings.presentation.mappers.mapToString
import com.kuro.mdp.features.settings.presentation.theme.SettingsTheme
import com.kuro.mdp.features.settings.presentation.ui.template.TemplateViewState
import com.kuro.mdp.shared.domain.model.template.RepeatTime
import com.kuro.mdp.shared.presentation.extensions.string
import com.kuro.mdp.shared.presentation.views.EmptyDateView
import com.kuro.mdp.shared.presentation.views.ExpandedIcon

@Composable
internal fun TemplatesContent(
    state: TemplateViewState,
    modifier: Modifier = Modifier,
    onChangeSortedType: (TemplatesSortedType) -> Unit,
    onUpdateTemplate: (TemplateUi) -> Unit,
    onRestartRepeat: (TemplateUi) -> Unit,
    onStopRepeat: (TemplateUi) -> Unit,
    onAddRepeatTemplate: (RepeatTime, TemplateUi) -> Unit,
    onDeleteRepeatTemplate: (RepeatTime, TemplateUi) -> Unit,
    onDeleteTemplate: (TemplateUi) -> Unit,
) {
    Column(modifier = modifier) {
        TemplatesFiltersHeader(
            sortedType = state.sortedType,
            onChangeSortedType = onChangeSortedType,
        )
        HorizontalDivider()
        TemplatesLazyColumn(
            templates = state.templates,
            categories = state.categories,
            onUpdateTemplate = onUpdateTemplate,
            onRestartRepeat = onRestartRepeat,
            onStopRepeat = onStopRepeat,
            onAddRepeatTemplate = onAddRepeatTemplate,
            onDeleteRepeatTemplate = onDeleteRepeatTemplate,
            onDeleteTemplate = onDeleteTemplate,
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun TemplatesLazyColumn(
    modifier: Modifier = Modifier,
    templates: List<TemplateUi>?,
    categories: List<CategoriesUi>,
    onUpdateTemplate: (TemplateUi) -> Unit,
    onRestartRepeat: (TemplateUi) -> Unit,
    onStopRepeat: (TemplateUi) -> Unit,
    onAddRepeatTemplate: (RepeatTime, TemplateUi) -> Unit,
    onDeleteRepeatTemplate: (RepeatTime, TemplateUi) -> Unit,
    onDeleteTemplate: (TemplateUi) -> Unit,
) {
    if (!templates.isNullOrEmpty()) {
        LazyColumn(
            modifier = modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            items(
                items = templates,
                key = { it.templateId },
            ) { template ->
                TemplatesItem(
                    modifier = Modifier.animateItemPlacement(),
                    model = template,
                    categories = categories,
                    onUpdate = { onUpdateTemplate(it) },
                    onRestartRepeat = { onRestartRepeat(template) },
                    onStopRepeat = { onStopRepeat(template) },
                    onAddRepeat = { onAddRepeatTemplate(it, template) },
                    onDeleteRepeat = { onDeleteRepeatTemplate(it, template) },
                    onDeleteTemplate = { onDeleteTemplate(template) },
                )
            }
            item { Spacer(modifier = Modifier.height(40.dp)) }
        }
    } else if (templates != null && templates.isEmpty()) {
        Box(Modifier.fillMaxSize()) {
            EmptyDateView(
                modifier = Modifier.align(Alignment.Center),
                emptyTitle = SettingsTheme.strings.emptyListTitle.string(),
            )
        }
    } else {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }
}

@Composable
internal fun TemplatesFiltersHeader(
    modifier: Modifier = Modifier,
    sortedType: TemplatesSortedType,
    onChangeSortedType: (TemplatesSortedType) -> Unit,
) {
    var isExpanded by rememberSaveable { mutableStateOf(false) }
    Row(
        modifier = modifier.fillMaxWidth().padding(start = 16.dp, end = 8.dp, top = 8.dp, bottom = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = SettingsTheme.strings.sortedTypeTitle.string(),
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.labelLarge,
        )
        Spacer(modifier = Modifier.weight(1f))
        Box {
            Surface(
                onClick = { isExpanded = true },
                shape = MaterialTheme.shapes.small,
                color = MaterialTheme.colorScheme.background,
            ) {
                Row(
                    modifier = Modifier.animateContentSize().padding(6.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    Text(
                        text = sortedType.mapToString(),
                        color = MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.labelLarge.copy(
                            fontWeight = FontWeight.Medium,
                        ),
                    )
                    Box(modifier = Modifier.size(18.dp), contentAlignment = Alignment.Center) {
                        ExpandedIcon(isExpanded = isExpanded)
                    }
                }
            }
            SortedTypeMenu(
                modifier = Modifier.align(Alignment.TopEnd),
                isExpanded = isExpanded,
                onDismiss = { isExpanded = false },
                onSelected = { type ->
                    isExpanded = false
                    onChangeSortedType(type)
                },
            )
        }
    }
}

@Composable
internal fun SortedTypeMenu(
    modifier: Modifier = Modifier,
    isExpanded: Boolean,
    onDismiss: () -> Unit,
    onSelected: (TemplatesSortedType) -> Unit,
) {
    DropdownMenu(
        expanded = isExpanded,
        onDismissRequest = onDismiss,
        modifier = modifier,
        shape = MaterialTheme.shapes.large,
        offset = DpOffset(0.dp, 2.dp),
    ) {
        TemplatesSortedType.entries.forEach { type ->
            DropdownMenuItem(
                onClick = { onSelected(type) },
                text = {
                    Text(
                        text = type.mapToString(),
                        color = MaterialTheme.colorScheme.onSurface,
                        style = MaterialTheme.typography.titleMedium,
                    )
                },
            )
        }
    }
}
