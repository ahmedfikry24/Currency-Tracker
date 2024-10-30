package org.example.currency_tracker.ui.shared_ui

import androidx.annotation.DrawableRes
import currencytracker.composeapp.generated.resources.Res
import currencytracker.composeapp.generated.resources.btc
import org.jetbrains.compose.resources.DrawableResource

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

