package com.kuro.mdp.shared.presentation

import platform.Foundation.NSUserDefaults

/**
 * Created by: minhthinh.h on 12/9/2024
 *
 * Description:
 */

actual fun changeLanguage(appLanguage: LanguageUiType) {
    NSUserDefaults.standardUserDefaults.setObject(arrayListOf(appLanguage.code ?: LanguageUiType.EN.code), "AppleLanguages")
}