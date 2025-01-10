package com.kuro.mdp.shared.presentation

import java.util.Locale

/**
 * Created by: minhthinh.h on 12/9/2024
 *
 * Description:
 */

actual fun changeLanguage(appLanguage: LanguageUiType) {
    val locale: Locale = if (appLanguage.code != null) Locale(appLanguage.code) else Locale(LanguageUiType.EN.code!!)
    Locale.setDefault(locale)
}