package org.example.currency_tracker.data.model

import org.example.currency_tracker.ui.shared_ui.CoinUiState
import org.example.currency_tracker.ui.shared_ui.DisplayedNumber
import org.example.currency_tracker.ui.util.getDrawableIdForCoin
import java.text.NumberFormat
import java.util.Locale

data class Coin(
    val id: String,
    val name: String,
    val rank: String,
    val symbol: String,
    val marketCapUsd: Double,
    val priceUsd: Double,
    val changePercent24Hr: Double,
)

fun Coin.toUiState(): CoinUiState {
    return CoinUiState(
        id = id,
        name = name,
        rank = rank,
        symbol = symbol,
        marketCapUsd = marketCapUsd.toDisplayedNumber(),
        priceUsd = priceUsd.toDisplayedNumber(),
        changePercent24Hr = changePercent24Hr.toDisplayedNumber(),
        iconRes = getDrawableIdForCoin(symbol),
    )
}


private fun Double.toDisplayedNumber(): DisplayedNumber {
    val formatter = NumberFormat.getNumberInstance(Locale.getDefault()).apply {
        maximumFractionDigits = 2
        minimumFractionDigits = 2
    }
    return DisplayedNumber(
        value = this,
        formated = formatter.format(this)
    )
}
