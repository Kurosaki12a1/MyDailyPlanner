package com.kuro.mdp.features.categories.domain

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform