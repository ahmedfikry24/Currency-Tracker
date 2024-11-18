package org.example.currency_tracker.data.repository

import org.example.currency_tracker.data.model.CoinDto
import org.example.currency_tracker.data.model.CoinPriceDto
import java.time.ZonedDateTime

interface Repository {
    suspend fun getCoinList(): List<CoinDto>
    suspend fun getCoinHistory(
        id: String,
        start: ZonedDateTime,
        end: ZonedDateTime,
    ): List<CoinPriceDto>
}