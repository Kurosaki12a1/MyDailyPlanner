package com.kuro.mdp.features.settings.presentation.ui.donate

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.kuro.mdp.features.settings.presentation.ui.donate.components.CryptoAddressLazyColumn
import com.kuro.mdp.features.settings.presentation.ui.donate.components.DonateTopAppBar
import com.kuro.mdp.shared.presentation.navigation.navigator.Navigator
import org.koin.compose.koinInject

/**
 * Created by: minhthinh.h on 12/26/2024
 *
 * Description:
 */
@Composable
internal fun DonateScreen(
    navigator: Navigator = koinInject()
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        content = {
            CryptoAddressLazyColumn(
                modifier = Modifier.padding(it),
            )
        },
        topBar = {
            DonateTopAppBar(
                onNavButtonClick = { navigator.tryNavigateBack() }
            )
        }
    )
}
