package com.kuro.mdp.features.overview.domain

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform