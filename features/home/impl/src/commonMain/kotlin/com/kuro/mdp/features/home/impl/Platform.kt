package com.kuro.mdp.features.home.impl

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform