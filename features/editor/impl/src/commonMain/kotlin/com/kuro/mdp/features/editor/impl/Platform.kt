package com.kuro.mdp.features.editor.impl

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform