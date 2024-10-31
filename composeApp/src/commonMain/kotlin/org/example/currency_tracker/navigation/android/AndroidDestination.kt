package org.example.currency_tracker.navigation.android

import kotlinx.serialization.Serializable

@Serializable
data object CoinList

@Serializable
data class CoinDetails(val id: String)