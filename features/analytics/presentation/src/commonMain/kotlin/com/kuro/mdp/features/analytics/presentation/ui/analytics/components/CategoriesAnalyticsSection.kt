package com.kuro.mdp.features.analytics.presentation.ui.analytics.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import com.kuro.mdp.charts.legend.data.LegendPosition
import com.kuro.mdp.charts.pie.PieChart
import com.kuro.mdp.charts.pie.data.PieChartData
import com.kuro.mdp.charts.pie.data.PieChartEntry
import com.kuro.mdp.features.analytics.domain.models.analytics.CategoryAnalytics
import com.kuro.mdp.features.analytics.presentation.theme.AnalyticsTheme
import com.kuro.mdp.shared.presentation.charts.fetchPieColorByTop
import com.kuro.mdp.shared.presentation.extensions.string
import com.kuro.mdp.shared.presentation.mappers.fetchName
import com.kuro.mdp.shared.presentation.theme.AppTheme
import com.kuro.mdp.shared.presentation.views.toMinutesAndHoursTitle
import com.kuro.mdp.shared.utils.functional.TimePeriod

/**
 * Created by: minhthinh.h on 2/19/2025
 *
 * Description:
 */
@Composable
internal fun CategoriesAnalyticsSection(
    modifier: Modifier = Modifier,
    isLoading: Boolean,
    categoriesAnalytics: List<CategoryAnalytics>?,
    timePeriod: TimePeriod?,
    onTimePeriodChanged: (TimePeriod) -> Unit,
) {
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(16.dp)) {
        TimeSelectorSection(
            modifier = Modifier.padding(start = 16.dp, end = 8.dp),
            timePeriod = timePeriod,
            title = AnalyticsTheme.strings.categoryStatisticsTitle.string(),
            onTimePeriodChanged = onTimePeriodChanged,
        )
        AnimatedContent(
            targetState = isLoading,
            label = "Categories analytics",
            transitionSpec = {
                fadeIn(animationSpec = tween(220, delayMillis = 90)).togetherWith(
                    fadeOut(animationSpec = tween(90)),
                )
            },
        ) { loading ->
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                var selectedItem by remember { mutableIntStateOf(0) }
                if (!loading && categoriesAnalytics != null) {
                    CategoriesAnalyticsChart(
                        analytics = categoriesAnalytics,
                        selectedItem = selectedItem,
                        onSelectItem = { selectedItem = it },
                    )
                    SubAnalyticsTimeLegend(
                        modifier = Modifier.height(400.dp).padding(top = 12.dp),
                        analytics = categoriesAnalytics,
                        selectedItem = selectedItem,
                    )
                } else {
                    Surface(
                        modifier = Modifier,
                        shape = MaterialTheme.shapes.large,
                        color = MaterialTheme.colorScheme.surfaceContainerLow,
                        content = { Box(Modifier.fillMaxWidth().height(190.dp)) },
                    )
                    LazyColumn(
                        modifier = Modifier.height(400.dp).padding(top = 12.dp),
                        state = rememberLazyListState(),
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                    ) {
                        items(6) {
                            Surface(
                                shape = MaterialTheme.shapes.large,
                                color = MaterialTheme.colorScheme.surfaceContainerLow,
                            ) {
                                Box(Modifier.fillMaxWidth().height(58.dp))
                            }
                        }
                    }
                }
            }
        }
    }
}


@Composable
internal fun CategoriesAnalyticsChart(
    modifier: Modifier = Modifier,
    analytics: List<CategoryAnalytics>,
    selectedItem: Int,
    onSelectItem: (Int) -> Unit,
) {
    val topList = analytics.subList(fromIndex = 0, toIndex = 5)
    val otherList = analytics.subList(5, analytics.lastIndex)
    val pieDataList = mutableListOf<PieChartEntry>().apply {
        topList.forEachIndexed { index, analytic ->
            val label = analytic.mainCategory.fetchName() ?: "*"
            val data = PieChartEntry(
                value = analytic.duration.toFloat() + 1f,
                label = AnnotatedString(label),
                color = fetchPieColorByTop(index),
                textDisplay = analytic.duration.toMinutesAndHoursTitle()
            )
            add(data)
        }
        val otherAnalytics = otherList.sumOf { it.duration }
        val otherPieData = PieChartEntry(
            value = otherAnalytics.toFloat(),
            label = AnnotatedString(AnalyticsTheme.strings.otherAnalyticsName.string()),
            color = fetchPieColorByTop(5),
            textDisplay = otherAnalytics.toMinutesAndHoursTitle()
        )
        add(otherPieData)
    }
    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        PieChart(
            data = remember(pieDataList) {
                PieChartData(
                    entries = pieDataList,
                    legendPosition = LegendPosition.Bottom,
                    legendShape = RoundedCornerShape(8.dp),
                )
            },
            pieSelected = selectedItem,
            chartSize = 200.dp,
            sliceWidth = 30.dp,
            sliceSpacing = 0.dp
        ) { legendEntries ->
            AnalyticsTimeLegend(
                modifier = Modifier.wrapContentHeight(),
                legendEntries = legendEntries,
                selectedItem = selectedItem,
                onSelectedItem = onSelectItem,
            )
        }
        /* Text(
             modifier = Modifier.align(Alignment.TopCenter).offset(x = 0.dp, y = 70.dp),
             text = analytics[selectedItem].duration.toMinutesAndHoursTitle(),
             color = MaterialTheme.colorScheme.onSurfaceVariant,
             style = MaterialTheme.typography.titleMedium,
         )*/
    }
}

@Composable
internal fun SubAnalyticsTimeLegend(
    modifier: Modifier = Modifier,
    analytics: List<CategoryAnalytics>,
    selectedItem: Int,
) {
    LazyColumn(
        modifier = modifier,
        state = rememberLazyListState(),
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        if (selectedItem < 5) {
            val allSubCategoryAnalytics = analytics[selectedItem]
            val subCategoryAnalytic = allSubCategoryAnalytics.subCategoriesInfo

            items(subCategoryAnalytic) { analytic ->
                val percent = analytic.duration.toFloat() / allSubCategoryAnalytics.duration * 100

                SubAnalyticsTimeLegendItem(
                    name = analytic.subCategory.name
                        ?: AppTheme.strings.categoryEmptyTitle.string(),
                    duration = analytic.duration,
                    percent = percent,
                )
            }
        } else {
            val otherAnalytics = analytics.subList(5, analytics.lastIndex)
            items(otherAnalytics) { analytic ->
                val percent =
                    analytic.duration.toFloat() / otherAnalytics.sumOf { it.duration } * 100

                SubAnalyticsTimeLegendItem(
                    name = analytic.mainCategory.fetchName() ?: "*",
                    duration = analytic.duration,
                    percent = percent,
                )
            }
        }
    }
}

@Composable
internal fun SubAnalyticsTimeLegendItem(
    modifier: Modifier = Modifier,
    name: String,
    duration: Long,
    percent: Float,
) {
    Surface(
        modifier = modifier,
        shape = MaterialTheme.shapes.large,
        color = MaterialTheme.colorScheme.surfaceContainerLow,
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                Text(
                    text = name,
                    color = MaterialTheme.colorScheme.secondary,
                    style = MaterialTheme.typography.titleSmall,
                )
                Text(
                    text = duration.toMinutesAndHoursTitle(),
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "${percent.toInt()}%",
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.titleSmall,
            )
        }
    }
}
