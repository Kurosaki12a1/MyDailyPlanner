package com.kuro.mdp.features.home.presentation.ui.home.ui.editor.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Badge
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kuro.mdp.features.home.domain.model.categories.MainCategoryHome
import com.kuro.mdp.features.home.domain.model.categories.SubCategoryHome
import com.kuro.mdp.features.home.domain.model.templates.TemplateHome
import com.kuro.mdp.features.home.presentation.ui.home.theme.HomeTheme
import com.kuro.mdp.features.home.presentation.ui.home.util.fetchName
import com.kuro.mdp.shared.domain.model.template.RepeatTime
import com.kuro.mdp.shared.presentation.extensions.string
import com.kuro.mdp.shared.presentation.mappers.mapToIconPainter
import com.kuro.mdp.shared.presentation.mappers.mapToString
import com.kuro.mdp.shared.presentation.theme.AppTheme
import com.kuro.mdp.shared.presentation.views.CategoryIconMonogram
import com.kuro.mdp.shared.presentation.views.CategoryTextMonogram
import com.kuro.mdp.shared.presentation.views.ExpandedIcon
import com.kuro.mdp.shared.presentation.views.NoneItemsView
import com.kuro.mdp.shared.presentation.views.toMinutesOrHoursTitle
import com.kuro.mdp.shared.utils.extensions.duration
import com.kuro.mdp.shared.utils.format
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.format.char

/**
 * Created by: minhthinh.h on 2/7/2025
 *
 * Description:
 */
@Composable
@ExperimentalMaterial3Api
internal fun TemplatesBottomSheet(
    modifier: Modifier = Modifier,
    isShow: Boolean,
    templates: List<TemplateHome>?,
    currentTemplateId: Int?,
    onDismiss: () -> Unit,
    onControlClick: () -> Unit,
    onChooseTemplate: (TemplateHome) -> Unit,
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    if (isShow) {
        ModalBottomSheet(
            modifier = modifier,
            sheetState = sheetState,
            onDismissRequest = onDismiss,
        ) {
            Column(modifier = Modifier.heightIn(min = 350.dp)) {
                TemplatesBottomSheetHeader(
                    templateCount = templates?.size,
                    onControlClick = onControlClick,
                )
                HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp))
                LazyColumn(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                ) {
                    if (templates != null) {
                        if (templates.isNotEmpty()) {
                            items(items = templates, key = { it.templateId }) { template ->
                                TemplateBottomSheetItem(
                                    enable = template.templateId != currentTemplateId,
                                    model = template,
                                    onChoose = { onChooseTemplate(template) },
                                )
                            }
                        } else {
                            item {
                                NoneItemsView(text = HomeTheme.strings.emptyTemplatesTitle.string())
                            }
                        }
                    } else {
                        item {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator()
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
@ExperimentalMaterial3Api
internal fun TemplatesBottomSheetHeader(
    modifier: Modifier = Modifier,
    templateCount: Int?,
    onControlClick: () -> Unit,
) {
    Row(
        modifier = modifier.padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Text(
            text = HomeTheme.strings.templatesSheetTitle.string(),
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.titleLarge,
        )
        Badge(modifier = Modifier.align(Alignment.CenterVertically).width(22.dp)) {
            Text(templateCount?.toString() ?: "-")
        }
        Spacer(modifier = Modifier.weight(1f))
        TextButton(modifier = Modifier.height(34.dp), onClick = onControlClick) {
            Text(text = HomeTheme.strings.controlTitle.string())
        }
    }
}

@Composable
internal fun TemplateBottomSheetItem(
    modifier: Modifier = Modifier,
    enable: Boolean = true,
    model: TemplateHome,
    onChoose: () -> Unit,
) {
    var isExpanded by rememberSaveable { mutableStateOf(false) }
    Surface(
        onClick = { isExpanded = !isExpanded },
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        color = MaterialTheme.colorScheme.surfaceContainer,
    ) {
        Column(modifier = Modifier.animateContentSize()) {
            Row(
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 12.dp),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                val categoryIcon = model.category.defaultType?.mapToIconPainter()
                val categoryName = model.category.fetchName() ?: "*"
                if (categoryIcon != null) {
                    CategoryIconMonogram(
                        icon = categoryIcon,
                        iconDescription = categoryName,
                        iconColor = MaterialTheme.colorScheme.primary,
                        backgroundColor = MaterialTheme.colorScheme.primaryContainer,
                    )
                } else {
                    CategoryTextMonogram(
                        text = categoryName.first().toString(),
                        textColor = MaterialTheme.colorScheme.primary,
                        backgroundColor = MaterialTheme.colorScheme.primaryContainer,
                    )
                }
                TemplateBottomSheetItemInfo(
                    modifier = Modifier.weight(1f),
                    isFullInfo = isExpanded,
                    mainCategory = model.category,
                    subCategory = model.subCategory,
                    startTime = model.startTime,
                    endTime = model.endTime,
                    isEnableNotification = model.isEnableNotification,
                    repeatTimes = model.repeatTimes,
                )
                ExpandedIcon(
                    modifier = Modifier.size(24.dp),
                    isExpanded = isExpanded,
                )
            }
            if (isExpanded && enable) {
                Row(Modifier.fillMaxWidth().padding(end = 16.dp, bottom = 4.dp)) {
                    Spacer(modifier = Modifier.weight(1f))
                    TextButton(onClick = onChoose) {
                        Icon(
                            modifier = Modifier.size(18.dp),
                            imageVector = Icons.Default.Check,
                            contentDescription = HomeTheme.strings.applyTitle.string(),
                        )
                        Text(
                            modifier = Modifier.offset(4.dp),
                            text = HomeTheme.strings.applyTitle.string(),
                        )
                    }
                }
            }
        }
    }
}

@Composable
internal fun TemplateBottomSheetItemInfo(
    modifier: Modifier = Modifier,
    isFullInfo: Boolean,
    mainCategory: MainCategoryHome,
    subCategory: SubCategoryHome?,
    startTime: LocalDateTime,
    endTime: LocalDateTime,
    isEnableNotification: Boolean,
    repeatTimes: List<RepeatTime>,
) {
    val timeFormat = LocalDateTime.Format {
        hour()
        char('-')
        minute()
    }
    val startTimeFormat = timeFormat.format(startTime)
    val endTimeFormat = timeFormat.format(endTime)
    val duration = duration(startTime, endTime).toMinutesOrHoursTitle()
    val categoryName = mainCategory.fetchName() ?: "*"
    val subCategoryName = subCategory?.name ?: AppTheme.strings.categoryEmptyTitle.string()
    val repeatTimesTitle = when (repeatTimes.isEmpty()) {
        true -> ""
        false -> "${repeatTimes.first().repeatType.mapToString()} (${repeatTimes.size})"
    }
    val mainText = when (subCategory != null) {
        true -> AppTheme.strings.splitFormat.string().format(categoryName, subCategoryName)
        false -> categoryName
    }
    val notificationTitle = when (isEnableNotification) {
        true -> HomeTheme.strings.notificationEnabledTitle
        false -> HomeTheme.strings.notificationDisabledTitle
    }
    val subText = when (isFullInfo) {
        true -> StringBuilder()
            .appendLine(HomeTheme.strings.timeRangeFormat.string().format(startTimeFormat, endTimeFormat))
            .appendLine(HomeTheme.strings.durationFormat.string().format(duration))
            .appendLine(notificationTitle)
            .appendLine(repeatTimesTitle)

        false -> StringBuilder()
            .appendLine(HomeTheme.strings.timeRangeFormat.string().format(startTimeFormat, endTimeFormat))
            .appendLine(HomeTheme.strings.durationFormat.string().format(duration))
    }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        Text(
            text = mainText,
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.titleMedium,
        )
        Text(
            text = subText.toString(),
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}
