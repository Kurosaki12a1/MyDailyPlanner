package com.kuro.mdp.features.home.api

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform