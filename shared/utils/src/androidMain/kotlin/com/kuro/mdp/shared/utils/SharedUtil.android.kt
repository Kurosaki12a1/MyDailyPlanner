package com.kuro.mdp.shared.utils

import kotlin.text.format

actual fun String.format(format: String, vararg args: Any?): String = this.format(format, args)

actual fun String.format(vararg args: Any?): String = this.format(args)