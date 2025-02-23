package com.kuro.mdp.features.analytics.presentation.ui.analytics

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.kuro.mdp.features.analytics.presentation.theme.AnalyticsTheme
import com.kuro.mdp.features.analytics.presentation.ui.analytics.components.AnalyticsTopAppBar
import com.kuro.mdp.features.analytics.presentation.ui.analytics.components.TimeTab
import com.kuro.mdp.features.analytics.presentation.ui.analytics.components.WorkLoadTab
import com.kuro.mdp.features.analytics.presentation.ui.analytics.contract.AnalyticsEvent
import com.kuro.mdp.features.analytics.presentation.viewmodel.AnalyticsViewModel
import com.kuro.mdp.shared.presentation.extensions.string
import com.kuro.mdp.shared.presentation.views.ErrorSnackBar
import com.kuro.mdp.shared.presentation.views.HorizontalTabsPager
import com.kuro.mdp.shared.presentation.views.TabItem
import org.jetbrains.compose.resources.DrawableResource
import org.koin.compose.viewmodel.koinViewModel

/**
 * Created by: minhthinh.h on 2/18/2025
 *
 * Description:
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AnalyticsScreen(
    viewModel: AnalyticsViewModel = koinViewModel()
) {
    val state by viewModel.state
    val snackBarState = remember { SnackbarHostState() }

    Scaffold(
        content = { paddingValues ->
            HorizontalTabsPager(
                modifier = Modifier.padding(paddingValues),
                tabs = AnalyticsTabItem.entries
            ) { tab ->
                when (tab) {
                    AnalyticsTabItem.TIME -> TimeTab(
                        state = state,
                        onTimePeriodChanged = { timePeriod ->
                            viewModel.dispatchEvent(AnalyticsEvent.ChangeTimePeriod(timePeriod))
                        },
                    )

                    AnalyticsTabItem.WORKLOAD -> WorkLoadTab(
                        state = state,
                        onTimePeriodChanged = { timePeriod ->
                            viewModel.dispatchEvent(AnalyticsEvent.ChangeTimePeriod(timePeriod))
                        },
                    )
                }
            }
        },
        topBar = {
            AnalyticsTopAppBar(
                onMenuButtonClick = { },
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackBarState) {
                ErrorSnackBar(snackBarData = it)
            }
        },
    )
}

internal enum class AnalyticsTabItem : TabItem {
    TIME {
        override val title: String @Composable get() = AnalyticsTheme.strings.timeTabTitle.string()
        override val leadingIcon: DrawableResource @Composable get() = AnalyticsTheme.icons.pieChart
    },
    WORKLOAD {
        override val title: String @Composable get() = AnalyticsTheme.strings.workLoadTabTitle.string()
        override val leadingIcon: DrawableResource @Composable get() = AnalyticsTheme.icons.barChart
    },
}
