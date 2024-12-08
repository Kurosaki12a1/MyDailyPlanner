package com.kuro.mdp.app

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform