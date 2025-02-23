package com.kuro.mdp.features.overview.presentation.ui.overview.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.kuro.mdp.features.overview.domain.model.schedules.ScheduleOverView
import com.kuro.mdp.features.overview.presentation.theme.OverViewTheme
import com.kuro.mdp.shared.domain.model.schedules.DailyScheduleStatus
import com.kuro.mdp.shared.presentation.extensions.alphaByEnabled
import com.kuro.mdp.shared.presentation.extensions.scrollText
import com.kuro.mdp.shared.presentation.extensions.string
import com.kuro.mdp.shared.presentation.theme.AppTheme
import com.kuro.mdp.shared.utils.extensions.isCurrentDay
import com.kuro.mdp.shared.utils.functional.getTimeFormatOfMonth
import org.jetbrains.compose.resources.painterResource

/**
 * Created by: minhthinh.h on 1/21/2025
 *
 * Description:
 */
@Composable
internal fun OverViewScheduleItem(
    modifier: Modifier = Modifier,
    model: ScheduleOverView,
    onClick: () -> Unit,
) {
    val dateFormat = getTimeFormatOfMonth(
        listDayOfWeekName = AppTheme.dayOfWeeks,
        listOfMonthName = AppTheme.months
    )

    Surface(
        onClick = onClick,
        modifier = modifier.height(125.dp),
        shape = MaterialTheme.shapes.large,
        color = MaterialTheme.colorScheme.surfaceContainerLow,
        border = when (model.date.isCurrentDay()) {
            true -> BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
            false -> null
        },
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(2.dp),
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                CircularProgressIndicator(
                    progress = {
                        when (model.dateStatus) {
                            DailyScheduleStatus.REALIZED -> model.progress
                            DailyScheduleStatus.ACCOMPLISHMENT -> model.progress
                            DailyScheduleStatus.PLANNED -> 0f
                        }
                    },
                    color = MaterialTheme.colorScheme.primary,
                    trackColor = MaterialTheme.colorScheme.surfaceVariant,
                )
                Spacer(modifier = Modifier.weight(1f))
                Column {
                    Text(
                        text = when (model.dateStatus) {
                            DailyScheduleStatus.REALIZED -> OverViewTheme.strings.realizedScheduleTitle.string()
                            DailyScheduleStatus.ACCOMPLISHMENT -> OverViewTheme.strings.accomplishmentScheduleTitle.string()
                            DailyScheduleStatus.PLANNED -> OverViewTheme.strings.plannedScheduleTitle.string()
                        },
                        color = MaterialTheme.colorScheme.primary,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.labelMedium,
                    )
                    Text(
                        modifier = Modifier.scrollText(),
                        text = dateFormat.format(model.date),
                        color = MaterialTheme.colorScheme.onSurface,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.titleMedium,
                    )
                }
            }
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                ShortInfoView(
                    modifier = Modifier.weight(1f),
                    enabled = model.dateStatus != DailyScheduleStatus.PLANNED,
                    text = model.timeTasks.count { !it.isCompleted }.toString(),
                    icon = painterResource(OverViewTheme.icons.unExecutedTask),
                )
                ShortInfoView(
                    modifier = Modifier.weight(1f),
                    enabled = model.dateStatus != DailyScheduleStatus.PLANNED,
                    text = model.timeTasks.count { it.progress == 1f && it.isCompleted }.toString(),
                    icon = painterResource(OverViewTheme.icons.completedTask),
                )
                val plannedTimeTasks = model.timeTasks.count { it.progress < 1f }
                ShortInfoView(
                    modifier = Modifier.weight(1f),
                    enabled = model.dateStatus != DailyScheduleStatus.REALIZED,
                    text = plannedTimeTasks.toString(),
                    icon = painterResource(AppTheme.icons.plannedTask),
                    color = if (plannedTimeTasks == 0) {
                        MaterialTheme.colorScheme.onSurfaceVariant
                    } else {
                        MaterialTheme.colorScheme.primary
                    },
                )
            }
        }
    }
}

@Composable
internal fun ShortInfoView(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    text: String,
    icon: Painter,
    iconDescriptor: String? = null,
    color: Color = MaterialTheme.colorScheme.onSurfaceVariant,
) {
    Row(
        modifier = modifier.width(32.dp).alphaByEnabled(enabled),
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            modifier = Modifier.size(18.dp),
            painter = icon,
            contentDescription = iconDescriptor,
            tint = color,
        )
        Text(
            text = text,
            color = color,
            maxLines = 1,
            style = MaterialTheme.typography.labelMedium,
        )
    }
}
