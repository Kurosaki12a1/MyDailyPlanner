package com.kuro.mdp.features.analytics.presentation.ui.analytics.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kuro.mdp.features.analytics.domain.models.analytics.ScheduleAnalytics
import com.kuro.mdp.features.analytics.presentation.theme.AnalyticsTheme
import com.kuro.mdp.shared.presentation.extensions.scrollText
import com.kuro.mdp.shared.presentation.extensions.string
import com.kuro.mdp.shared.presentation.views.toMinutesAndHoursTitle
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

/**
 * Created by: minhthinh.h on 2/20/2025
 *
 * Description:
 */
@Composable
internal fun StatisticsSection(
    modifier: Modifier = Modifier,
    isLoading: Boolean,
    schedulesAnalytics: ScheduleAnalytics?,
) {
    AnimatedContent(
        modifier = modifier.padding(top = 8.dp),
        targetState = isLoading,
        label = "Executed analytics",
        transitionSpec = {
            fadeIn(animationSpec = tween(220, delayMillis = 90)).togetherWith(
                fadeOut(animationSpec = tween(90)),
            )
        },
    ) { loading ->
        if (!loading && schedulesAnalytics != null) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp).height(320.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                item {
                    StatisticInfoView(
                        icon = AnalyticsTheme.icons.numberedList,
                        name = AnalyticsTheme.strings.totalCountTaskTitle.string(),
                        value = schedulesAnalytics.totalTasksCount.toString(),
                    )
                }
                item {
                    StatisticInfoView(
                        icon = AnalyticsTheme.icons.numericOneCircle,
                        name = AnalyticsTheme.strings.averageCountTaskTitle.string(),
                        value = "~ ${schedulesAnalytics.averageDayLoad}",
                    )
                }
                item {
                    StatisticInfoView(
                        icon = AnalyticsTheme.icons.timeComplete,
                        name = AnalyticsTheme.strings.totalTimeTaskTitle.string(),
                        value = schedulesAnalytics.totalTasksTime.toMinutesAndHoursTitle(),
                    )
                }
                item {
                    StatisticInfoView(
                        icon = AnalyticsTheme.icons.timeCheck,
                        name = AnalyticsTheme.strings.averageTimeTaskTitle.string(),
                        value = schedulesAnalytics.averageTaskTime.toMinutesAndHoursTitle(),
                    )
                }
            }
        }
    }
}

@Composable
internal fun StatisticInfoView(
    modifier: Modifier = Modifier,
    icon: DrawableResource,
    name: String,
    value: String,
) {
    Surface(
        modifier = modifier,
        shape = MaterialTheme.shapes.large,
        color = MaterialTheme.colorScheme.surfaceContainerLow,
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Icon(
                painter = painterResource(icon),
                contentDescription = name,
                tint = MaterialTheme.colorScheme.onSurface,
            )
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(
                    modifier = Modifier.scrollText(),
                    text = name,
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 2
                )
                Text(
                    modifier = Modifier.scrollText(),
                    text = value,
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = 1,
                    style = MaterialTheme.typography.titleLarge,
                )
            }
        }
    }
}
