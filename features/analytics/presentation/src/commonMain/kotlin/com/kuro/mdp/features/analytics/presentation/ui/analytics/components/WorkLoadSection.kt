package com.kuro.mdp.features.analytics.presentation.ui.analytics.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.kuro.mdp.charts.bars.BarChart
import com.kuro.mdp.charts.bars.config.BarChartColorConfig
import com.kuro.mdp.charts.bars.config.BarChartConfig
import com.kuro.mdp.charts.bars.model.BarData
import com.kuro.mdp.charts.common.ChartColor
import com.kuro.mdp.charts.common.LabelConfig
import com.kuro.mdp.features.analytics.presentation.theme.AnalyticsTheme
import com.kuro.mdp.shared.domain.model.schedules.TimeTask
import com.kuro.mdp.shared.presentation.extensions.string
import com.kuro.mdp.shared.utils.extensions.toDaysTitle
import com.kuro.mdp.shared.utils.extensions.toMonthTitle
import com.kuro.mdp.shared.utils.functional.TimePeriod
import com.kuro.mdp.shared.utils.functional.TimeRange

/**
 * Created by: minhthinh.h on 2/20/2025
 *
 * Description:
 */
@Composable
internal fun WorkLoadSection(
    modifier: Modifier = Modifier,
    isLoading: Boolean,
    timePeriod: TimePeriod?,
    workLoadMap: Map<TimeRange, List<TimeTask>>?,
    onTimePeriodChanged: (TimePeriod) -> Unit,
) {
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(16.dp)) {
        TimeSelectorSection(
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
            timePeriod = timePeriod,
            title = AnalyticsTheme.strings.workLoadAnalyticsTitle.string(),
            onTimePeriodChanged = onTimePeriodChanged,
        )
        AnimatedContent(
            targetState = isLoading,
            label = "Workload analytics",
            transitionSpec = {
                fadeIn(animationSpec = tween(220, delayMillis = 90)).togetherWith(
                    fadeOut(animationSpec = tween(90)),
                )
            },
        ) { loading ->
            if (!loading && workLoadMap != null && timePeriod != null) {
                WorkLoadAnalyticsChart(
                    workLoadMap = workLoadMap,
                    period = timePeriod,
                )
            } else {
                Surface(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    color = MaterialTheme.colorScheme.background,
                    content = { Box(Modifier.fillMaxWidth().height(264.dp)) },
                )
            }
        }
    }
}

@Composable
internal fun WorkLoadAnalyticsChart(
    modifier: Modifier = Modifier,
    workLoadMap: Map<TimeRange, List<TimeTask>>,
    period: TimePeriod,
) {
    val data = mutableListOf<BarData>().apply {
        workLoadMap.onEachIndexed { _, entry ->
            val xValue = when (period == TimePeriod.YEAR || period == TimePeriod.HALF_YEAR) {
                true -> entry.key.toMonthTitle()
                false -> entry.key.toDaysTitle()
            }
            add(
                BarData(
                    xValue = xValue,
                    yValue = entry.value.size.toFloat(),
                    barBackgroundColor = ChartColor.Solid(Color.Transparent)
                )
            )
        }
    }
    BarChart(
        modifier = modifier.height(300.dp).padding(horizontal = 10.dp),
        data = { data },
        onBarClick = { index, barData -> println("onClick: $index and $barData") },
        barChartColorConfig = BarChartColorConfig.default().copy(
            axisLineColor = ChartColor.Solid(MaterialTheme.colorScheme.onSurfaceVariant)
        ),
        barChartConfig = BarChartConfig.default().copy(
            showAxisLines = true,
            minimumBarCount = 1
        ),
        labelConfig = LabelConfig.default().copy(
            showXLabel = true,
            showYLabel = true,
            textColor = ChartColor.Solid(MaterialTheme.colorScheme.onSurface)
        ),
    )
}


