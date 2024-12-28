package com.kuro.mdp.features.overview.presentation

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform