package com.kuro.mdp.features.settings.domain.model

/**
 * Created by: minhthinh.h on 12/26/2024
 *
 * Description:
 */
enum class CryptoAddress(val cryptoName: String, val address: String) {
    BTC("BITCOIN", ""),
    BNB("BNB SMART CHAIN", ""),
    ETH("ETHEREUM", ""),
    TRX("TRON", ""),
    LTC("LITECOIN", ""),
    XEC("ECASH(XEC)", ""),
    AVAX("AVALANCHE", ""),
    FTM("FANTOM", ""),
}
