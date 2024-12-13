package com.kuro.mdp.shared.presentation

import platform.Foundation.NSLocale
import platform.Foundation.currentLocale
import platform.Foundation.languageCode

/**
 * Created by: minhthinh.h on 12/9/2024
 *
 * Description:
 */
actual val language: String = NSLocale.currentLocale.languageCode