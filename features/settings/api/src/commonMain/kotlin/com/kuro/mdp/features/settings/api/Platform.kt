package com.kuro.mdp.features.settings.api

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform