package org.example.currency_tracker.ui.shared.ui_state

import org.example.currency_tracker.data.model.CoinPriceDto
import org.example.currency_tracker.ui.coin_details.composable.DataPoint
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

data class CoinHistoryUiState(
    val priceUsd: Double = 0.0,
    val time: ZonedDateTime = ZonedDateTime.now(),
)

fun CoinPriceDto.toUiState(): CoinHistoryUiState {
    return CoinHistoryUiState(
        priceUsd = priceUsd,
        time = Instant.ofEpochMilli(time).atZone(ZoneId.of("UTC"))
    )
}

fun CoinHistoryUiState.toPoints(): DataPoint {
    return DataPoint(
        x = time.hour.toFloat(),
        y = priceUsd.toFloat(),
        xLabel = DateTimeFormatter
            .ofPattern("ha\nM/d")
            .format(time)
    )
}