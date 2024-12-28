package com.kuro.mdp.features.analytics.data

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform