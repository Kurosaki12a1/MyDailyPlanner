package com.kuro.mdp.shared.utils

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform