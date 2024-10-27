package org.example.currency_tracker

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform