package com.kuro.mdp.features.categories.data

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform