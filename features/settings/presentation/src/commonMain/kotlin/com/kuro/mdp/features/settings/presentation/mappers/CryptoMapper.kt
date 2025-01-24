package com.kuro.mdp.features.settings.presentation.mappers

import androidx.compose.runtime.Composable
import com.kuro.mdp.features.settings.domain.model.donate.CryptoAddress
import com.kuro.mdp.features.settings.presentation.theme.SettingsTheme

/**
 * Created by: minhthinh.h on 12/26/2024
 *
 * Description:
 */
@Composable
internal fun CryptoAddress.mapToIcon() = when (this) {
    CryptoAddress.BTC -> SettingsTheme.icons.cryptoBitcoin
    CryptoAddress.BNB -> SettingsTheme.icons.cryptoBNB
    CryptoAddress.ETH -> SettingsTheme.icons.cryptoEthereum
    CryptoAddress.TRX -> SettingsTheme.icons.cryptoTron
    CryptoAddress.LTC -> SettingsTheme.icons.cryptoLitecoin
    CryptoAddress.XEC -> SettingsTheme.icons.cryptoECash
    CryptoAddress.AVAX -> SettingsTheme.icons.cryptoAvax
    CryptoAddress.FTM -> SettingsTheme.icons.cryptoFtm
}
