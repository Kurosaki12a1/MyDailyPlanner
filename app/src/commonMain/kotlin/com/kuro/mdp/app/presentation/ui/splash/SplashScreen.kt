package com.kuro.mdp.app.presentation.ui.splash

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kuro.mdp.shared.presentation.navigation.destination.Destination
import com.kuro.mdp.shared.presentation.navigation.navigator.Navigator
import com.kuro.mdp.shared.presentation.theme.AppTheme
import com.kuro.mdp.shared.presentation.theme.materials.onSplashGradient
import com.kuro.mdp.shared.presentation.theme.materials.splashGradientColors
import com.kuro.mdp.shared.utils.functional.Constants
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject
import shared.resources.Res
import shared.resources.ic_splash

/**
 * Created by: minhthinh.h on 12/11/2024
 *
 * Description:
 */
@Composable
fun SplashScreen(
    navigator: Navigator = koinInject()
) {

    val isLogoVisible = remember { mutableStateOf(false) }
    val isAppNameVisible = remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize().background(brush = Brush.verticalGradient(splashGradientColors)), contentAlignment = Alignment.Center) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AnimatedVisibility(
                visible = isLogoVisible.value,
                enter = fadeIn()
            ) {
                Image(
                    modifier = Modifier.size(100.dp),
                    painter = painterResource(Res.drawable.ic_splash),
                    contentDescription = stringResource(AppTheme.strings.appName)
                )
            }
            AnimatedVisibility(
                visible = isAppNameVisible.value,
                enter = fadeIn()
            ) {
                Text(
                    text = Constants.App.SPLASH_NAME,
                    style = MaterialTheme.typography.headlineLarge.copy(
                        fontWeight = FontWeight.Black,
                        lineHeight = 35.sp,
                    ),
                    color = onSplashGradient,
                )
            }
        }
    }

    LaunchedEffect(Unit) {
        delay(Constants.Delay.SPLASH_LOGO)
        isLogoVisible.value = true
        delay(Constants.Delay.SPLASH_TEXT)
        isAppNameVisible.value = true
        delay(Constants.Delay.SPLASH_BEFORE_NAVIGATE)
        navigator.navigateTo(route = Destination.Home(), popUpToRoute = Destination.Splash, inclusive = true)
    }
}