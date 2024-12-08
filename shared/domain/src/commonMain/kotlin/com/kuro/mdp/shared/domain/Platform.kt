package com.kuro.mdp.shared.domain

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform