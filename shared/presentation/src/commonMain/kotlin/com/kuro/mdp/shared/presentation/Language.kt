package com.kuro.mdp.shared.presentation

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.intl.Locale

/**
 * Created by: minhthinh.h on 12/9/2024
 *
 * Description:
 */
expect val language : String

enum class AppLanguage(val code: String) {
    EN("en"),
    RU("ru"),
    DE("de"),
    ES("es"),
    FA("fa"),
    FR("fr"),
    PT_BR("pt-br"),
    TR("tr"),
    VN("vn"),
    PL("pl"),
}


enum class LanguageUiType(val code: String?)  {
    DEFAULT(null),
    EN("en"),
    RU("ru"),
    DE("de"),
    ES("es"),
    FA("fa"),
    FR("fr"),
    PT_BR("pt-br"),
    TR("tr"),
    VN("vn"),
    PL("pl"),
}

val LocalAppLanguage = staticCompositionLocalOf<AppLanguage> {
    error("Language is not provided")
}

fun fetchAppLanguage(code: String): AppLanguage {
    return AppLanguage.entries.find { it.code == code } ?: AppLanguage.EN
}

fun fetchAppLanguage(languageType: LanguageUiType) = when (languageType) {
    LanguageUiType.DEFAULT -> fetchAppLanguage(language)
    LanguageUiType.EN -> AppLanguage.EN
    LanguageUiType.RU -> AppLanguage.RU
    LanguageUiType.DE -> AppLanguage.DE
    LanguageUiType.ES -> AppLanguage.ES
    LanguageUiType.FA -> AppLanguage.FA
    LanguageUiType.FR -> AppLanguage.FR
    LanguageUiType.PT_BR -> AppLanguage.PT_BR
    LanguageUiType.TR -> AppLanguage.TR
    LanguageUiType.VN -> AppLanguage.VN
    LanguageUiType.PL -> AppLanguage.PL
}
