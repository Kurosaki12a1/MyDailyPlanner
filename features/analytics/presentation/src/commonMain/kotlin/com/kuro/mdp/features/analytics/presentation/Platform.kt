package com.kuro.mdp.features.analytics.presentation

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform