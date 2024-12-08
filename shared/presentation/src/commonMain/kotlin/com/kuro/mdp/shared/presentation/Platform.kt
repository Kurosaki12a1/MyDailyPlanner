package com.kuro.mdp.shared.presentation

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform