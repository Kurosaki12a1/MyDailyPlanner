package com.kuro.mdp.features.home.presentation.ui.home.ui.home.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.kuro.mdp.features.home.presentation.ui.home.theme.HomeTheme
import com.kuro.mdp.shared.domain.model.settings.CalendarButtonBehavior
import com.kuro.mdp.shared.presentation.theme.AppTheme
import com.kuro.mdp.shared.presentation.views.TopAppBarButton
import com.kuro.mdp.shared.presentation.views.TopAppBarTitle
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource


/**
 * Created by: minhthinh.h on 12/17/2024
 *
 * Description:
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar(
    calendarIconBehavior: CalendarButtonBehavior,
    onMenuIconClick: () -> Unit,
    onOverviewIconClick: () -> Unit,
    onOpenCalendar: () -> Unit,
    onGoToToday: () -> Unit,
) {
    HomeTheme {
        TopAppBar(
            title = {
                TopAppBarTitle(
                    text = stringResource(HomeTheme.strings.topAppBarHomeTitle),
                    textAlign = TextAlign.Center
                )
            },
            navigationIcon = {
                TopAppBarButton(
                    modifier = Modifier.padding(end = 48.dp),
                    imageVector = Icons.Default.Menu,
                    imageDescription = stringResource(HomeTheme.strings.topAppBarMenuIconDesc),
                    onButtonClick = onMenuIconClick
                )
            },
            actions = {
                TopAppBarButton(
                    imagePainter = painterResource(HomeTheme.icons.calendar),
                    imageDescription = stringResource(HomeTheme.strings.topAppBarCalendarIconDesc),
                    onButtonClick = when (calendarIconBehavior) {
                        CalendarButtonBehavior.OPEN_CALENDAR -> onOpenCalendar
                        CalendarButtonBehavior.SET_CURRENT_DATE -> onGoToToday
                    },
                    onLongButtonClick = when (calendarIconBehavior) {
                        CalendarButtonBehavior.OPEN_CALENDAR -> onGoToToday
                        CalendarButtonBehavior.SET_CURRENT_DATE -> onOpenCalendar
                    }
                )
                TopAppBarButton(
                    imagePainter = painterResource(AppTheme.icons.overview),
                    imageDescription = stringResource(HomeTheme.strings.topAppBarOverviewTitle),
                    onButtonClick = onOverviewIconClick
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.background
            )
        )
    }

}