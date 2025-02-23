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
import androidx.compose.ui.unit.dp
import com.kuro.mdp.features.analytics.presentation.theme.AnalyticsTheme
import com.kuro.mdp.shared.domain.model.schedules.TimeTask
import com.kuro.mdp.shared.presentation.extensions.string
import com.kuro.mdp.shared.utils.functional.TimePeriod
import com.kuro.mdp.shared.utils.functional.TimeRange

/**
 * Created by: minhthinh.h on 2/20/2025
 *
 * Description:
 */
@Composable
internal fun ExecutedAnalyticsSection(
    modifier: Modifier = Modifier,
    isLoading: Boolean,
    timePeriod: TimePeriod?,
    workLoadMap: Map<TimeRange, List<TimeTask>>?,
    onTimePeriodChanged: (TimePeriod) -> Unit,
) {
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(4.dp)) {
        TimeSelectorSection(
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
            timePeriod = timePeriod,
            title = AnalyticsTheme.strings.executedStatisticsTitle.string(),
            onTimePeriodChanged = onTimePeriodChanged,
        )
        AnimatedContent(
            targetState = isLoading,
            label = "Executed analytics",
            transitionSpec = {
                fadeIn(animationSpec = tween(220, delayMillis = 90)).togetherWith(
                    fadeOut(animationSpec = tween(90)),
                )
            },
        ) { loading ->
            if (!loading && workLoadMap != null && timePeriod != null) {
                ExecutedAnalyticsChart(
                    workLoadMap = workLoadMap,
                    period = timePeriod,
                )
            } else {
                Surface(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    color = MaterialTheme.colorScheme.background,
                    content = { Box(Modifier.fillMaxWidth().height(206.dp)) },
                )
            }
        }
    }
}

@Composable
internal fun ExecutedAnalyticsChart(
    modifier: Modifier = Modifier,
    workLoadMap: Map<TimeRange, List<TimeTask>>,
    period: TimePeriod,
) {
    /* LineChart(
         modifier = modifier.height(200.dp).fillMaxWidth().padding(
             horizontal = 36.dp,
             vertical = 32.dp,
         ),
         data = {
             mutableListOf<LineData>().apply {
                 workLoadMap.forEach { (timeRange, timeTasks) ->
                     val xValue = when (period == TimePeriod.YEAR || period == TimePeriod.HALF_YEAR) {
                         true -> timeRange.toMonthTitle()
                         false -> timeRange.toDaysTitle()
                     }
                     val allTimeTasks = timeTasks.size.let { if (it == 0) 1 else it }
                     val yValue = timeTasks.count { it.isCompleted } / allTimeTasks.toFloat()
                     val scaleYValue = ceil(yValue * 10.0f) / 10.0f
                     add(LineData(xValue = xValue, yValue = scaleYValue * 100f))
                 }
             }
         },
         colorConfig = LineChartColorConfig.default().copy(
             axisColor = ChartColor.Solid(MaterialTheme.colorScheme.secondary),
         ),
         labelConfig = LabelConfig.default().copy(
             textColor = ChartColor.Solid(MaterialTheme.colorScheme.onSurface),
             showXLabel = true,
         ),
         chartConfig = LineChartConfig(
             drawPointerCircle = true
         )
     )*/
}
