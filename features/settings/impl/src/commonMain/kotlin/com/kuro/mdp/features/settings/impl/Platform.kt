package com.kuro.mdp.features.settings.impl

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform