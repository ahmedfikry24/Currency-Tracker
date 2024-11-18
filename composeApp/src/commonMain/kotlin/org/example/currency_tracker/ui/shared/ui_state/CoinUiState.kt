package org.example.currency_tracker.ui.shared.ui_state

import androidx.annotation.DrawableRes
import currencytracker.composeapp.generated.resources.Res
import currencytracker.composeapp.generated.resources.btc
import org.example.currency_tracker.data.model.CoinDto
import org.example.currency_tracker.ui.util.getDrawableIdForCoin
import org.jetbrains.compose.resources.DrawableResource
import java.text.NumberFormat
import java.util.Locale

data class CoinUiState(
    val id: String = "",
    val name: String = "",
    val rank: String = "",
    val symbol: String = "",
    val marketCapUsd: DisplayedNumber = DisplayedNumber(),
    val priceUsd: DisplayedNumber = DisplayedNumber(),
    val changePercent24Hr: DisplayedNumber = DisplayedNumber(),
    @DrawableRes val iconRes: DrawableResource = Res.drawable.btc,
)

data class DisplayedNumber(
    val value: Double = 0.0,
    val formated: String = "",
)


fun CoinDto.toUiState(): CoinUiState {
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


fun Double.toDisplayedNumber(): DisplayedNumber {
    val formatter = NumberFormat.getNumberInstance(Locale.getDefault()).apply {
        maximumFractionDigits = 2
        minimumFractionDigits = 2
    }
    return DisplayedNumber(
        value = this,
        formated = formatter.format(this)
    )
}
