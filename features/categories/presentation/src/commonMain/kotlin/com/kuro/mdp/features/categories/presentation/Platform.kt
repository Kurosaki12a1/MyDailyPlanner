package com.kuro.mdp.features.categories.presentation

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform