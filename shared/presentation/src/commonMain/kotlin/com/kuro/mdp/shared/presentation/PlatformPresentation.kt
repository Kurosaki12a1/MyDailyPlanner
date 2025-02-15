package com.kuro.mdp.shared.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp

/**
 * Created by: minhthinh.h on 12/9/2024
 *
 * Description:
 */

expect fun changeLanguage(appLanguage: LanguageUiType)

enum class LanguageUiType(val code: String?) {
    DEFAULT(null),
    EN("en"),
    RU("ru"),
    DE("de"),
    ES("es"),
    FA("fa"),
    FR("fr"),
    PT_BR("pt"),
    TR("tr"),
    VN("vi"),
    PL("pl"),
}

val LocalAppLanguage = staticCompositionLocalOf<LanguageUiType> {
    error("Language is not provided")
}

fun fetchAppLanguage(code: String?): LanguageUiType {
    val currentLanguage: LanguageUiType = LanguageUiType.entries.find { it.code == code } ?: LanguageUiType.EN
    changeLanguage(currentLanguage)
    return currentLanguage
}

@Composable
expect fun ScreenProtection(enable: Boolean)

@Composable
expect fun is24HourFormat(): Boolean

@Composable
expect fun getScreenWidthDp(): Dp

@Composable
expect fun getScreenHeightDp(): Dp