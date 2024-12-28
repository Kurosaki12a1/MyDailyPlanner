package com.kuro.mdp.features.overview.data

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform