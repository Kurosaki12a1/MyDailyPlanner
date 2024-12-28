package com.kuro.mdp.features.analytics.domain

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform