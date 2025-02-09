package com.kuro.mdp.app.presentation.ui.bottom_bar

import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kuro.mdp.shared.presentation.navigation.graph.NavigationGraph
import com.kuro.mdp.shared.presentation.views.BottomBarItem
import com.kuro.mdp.shared.presentation.views.BottomNavigationBar
import com.kuro.mdp.shared.utils.functional.Constants
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.stringResource
import shared.resources.Res
import shared.resources.analyticsTabTitle
import shared.resources.homeTabTitle
import shared.resources.ic_analytics
import shared.resources.ic_analytics_outline
import shared.resources.ic_dashboard
import shared.resources.ic_dashboard_outline
import shared.resources.ic_home
import shared.resources.ic_home_outlined
import shared.resources.ic_settings
import shared.resources.ic_settings_outline
import shared.resources.overviewDrawerTitle
import shared.resources.settingsTabTitle

/**
 * Created by: minhthinh.h on 12/11/2024
 *
 * Description:
 */
@Composable
fun TabsBottomNavigationBar(
    modifier: Modifier = Modifier,
    selectedItem: String?,
    onItemSelected: (BottomNavigationItem) -> Unit
) {
    BottomNavigationBar(
        modifier = modifier.height(80.dp),
        selectedItem = selectedItem,
        items = BottomNavigationItem.entries.toTypedArray(),
        showLabel = true,
        onItemSelected = onItemSelected
    )
}

fun shouldBottomBarVisible(route: String?): Boolean {
    return route?.contains(Constants.Destination.HOME) == true
            || route?.contains(Constants.Destination.ANALYTICS) == true
            || route?.contains(Constants.Destination.OVERVIEW) == true
            || route?.contains(Constants.Destination.SETTINGS) == true
}

enum class BottomNavigationItem : BottomBarItem {
    HOME {
        override val destination: NavigationGraph
            get() = NavigationGraph.HomeGraph
        override val enabledIcon: DrawableResource
            get() = Res.drawable.ic_home
        override val disabledIcon: DrawableResource
            get() = Res.drawable.ic_home_outlined
        override val label: String
            @Composable get() = stringResource(Res.string.homeTabTitle)
    },
    ANALYTICS {
        override val destination: NavigationGraph
            get() = NavigationGraph.AnalyticsGraph
        override val enabledIcon: DrawableResource
            get() = Res.drawable.ic_analytics
        override val disabledIcon: DrawableResource
            get() = Res.drawable.ic_analytics_outline
        override val label: String
            @Composable get() = stringResource(Res.string.analyticsTabTitle)
    },
    OVERVIEW {
        override val destination: NavigationGraph
            get() = NavigationGraph.OverviewGraph
        override val enabledIcon: DrawableResource
            get() = Res.drawable.ic_dashboard
        override val disabledIcon: DrawableResource
            get() = Res.drawable.ic_dashboard_outline
        override val label: String
            @Composable get() = stringResource(Res.string.overviewDrawerTitle)
    },

    /*    CATEGORY {
            override val destination: String
                get() = Constants.NavigationGraph.CATEGORIES
            override val enabledIcon: DrawableResource
                get() = Res.drawable.ic_categories
            override val disabledIcon: DrawableResource
                get() = Res.drawable.ic_categories_outline
            override val label: String
                @Composable
                get() = stringResource(Res.string.categoriesDrawerTitle)
        },*/
    SETTINGS {
        override val destination: NavigationGraph
            get() = NavigationGraph.SettingsGraph
        override val enabledIcon: DrawableResource
            get() = Res.drawable.ic_settings
        override val disabledIcon: DrawableResource
            get() = Res.drawable.ic_settings_outline
        override val label: String
            @Composable get() = stringResource(Res.string.settingsTabTitle)
    }
}
