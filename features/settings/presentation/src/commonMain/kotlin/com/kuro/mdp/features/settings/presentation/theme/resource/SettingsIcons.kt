package com.kuro.mdp.features.settings.presentation.theme.resource

import androidx.compose.runtime.staticCompositionLocalOf
import org.jetbrains.compose.resources.DrawableResource
import shared.resources.Res
import shared.resources.ic_avax
import shared.resources.ic_back
import shared.resources.ic_bitcoin
import shared.resources.ic_bnb
import shared.resources.ic_content_copy
import shared.resources.ic_crypto
import shared.resources.ic_ecash_xec
import shared.resources.ic_ethereum
import shared.resources.ic_fantom
import shared.resources.ic_github
import shared.resources.ic_litecoin
import shared.resources.ic_tron

/**
 * Created by: minhthinh.h on 12/25/2024
 *
 * Description:
 */
data class SettingsIcons(
    val back: DrawableResource,
    val git: DrawableResource,
    val cryptoBitcoin: DrawableResource,
    val cryptoBNB: DrawableResource,
    val cryptoEthereum: DrawableResource,
    val cryptoTron: DrawableResource,
    val cryptoLitecoin: DrawableResource,
    val cryptoECash: DrawableResource,
    val copy: DrawableResource,
    val crypto: DrawableResource,
    val cryptoAvax: DrawableResource,
    val cryptoFtm: DrawableResource,
)

val baseSettingIcons = SettingsIcons(
    back = Res.drawable.ic_back,
    git = Res.drawable.ic_github,
    cryptoBitcoin = Res.drawable.ic_bitcoin,
    cryptoBNB = Res.drawable.ic_bnb,
    cryptoEthereum = Res.drawable.ic_ethereum,
    cryptoTron = Res.drawable.ic_tron,
    cryptoLitecoin = Res.drawable.ic_litecoin,
    cryptoECash = Res.drawable.ic_ecash_xec,
    cryptoAvax = Res.drawable.ic_avax,
    cryptoFtm = Res.drawable.ic_fantom,
    copy = Res.drawable.ic_content_copy,
    crypto = Res.drawable.ic_crypto
)

internal val LocalSettingsIcons = staticCompositionLocalOf<SettingsIcons> {
    error("Settings Icons is not provided")
}

internal fun fetchSettingsIcons() = baseSettingIcons
