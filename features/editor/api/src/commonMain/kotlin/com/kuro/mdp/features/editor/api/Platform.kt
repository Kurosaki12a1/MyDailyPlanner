package com.kuro.mdp.features.editor.api

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform