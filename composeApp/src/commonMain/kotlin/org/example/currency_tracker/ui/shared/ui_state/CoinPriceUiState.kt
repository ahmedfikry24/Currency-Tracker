package org.example.currency_tracker.ui.shared.ui_state

import org.example.currency_tracker.data.model.CoinPriceDto
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime

data class CoinPriceUiState(
    val priceUsd: Double = 0.0,
    val time: ZonedDateTime = ZonedDateTime.now(),
)

fun CoinPriceDto.toUiState(): CoinPriceUiState {
    return CoinPriceUiState(
        priceUsd = priceUsd,
        time = Instant.ofEpochMilli(time).atZone(ZoneId.of("UTC"))
    )
}
