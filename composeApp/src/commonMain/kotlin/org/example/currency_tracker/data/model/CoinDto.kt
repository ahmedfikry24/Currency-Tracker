package org.example.currency_tracker.data.model

import kotlinx.serialization.Serializable
import org.example.currency_tracker.ui.shared_ui.CoinUiState
import org.example.currency_tracker.ui.shared_ui.DisplayedNumber
import org.example.currency_tracker.ui.util.getDrawableIdForCoin
import java.text.NumberFormat
import java.util.Locale

@Serializable
data class CoinDto(
    val id: String,
    val name: String,
    val rank: String,
    val symbol: String,
    val marketCapUsd: Double,
    val priceUsd: Double,
    val changePercent24Hr: Double,
)
