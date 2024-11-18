package org.example.currency_tracker.data.repository

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import org.example.currency_tracker.data.model.CoinDto
import org.example.currency_tracker.data.model.CoinPriceDto
import org.example.currency_tracker.data.model.DefaultResponseDto
import org.example.currency_tracker.data.utils.getFullUrl
import org.example.currency_tracker.data.utils.warpApiCall
import java.time.ZoneId
import java.time.ZonedDateTime

class RepositoryImpl(
    private val client: HttpClient,
) : Repository {

    override suspend fun getCoinList(): List<CoinDto> {
        return warpApiCall<DefaultResponseDto<CoinDto>> { client.get(getFullUrl("/assets")) }.data
    }

    override suspend fun getCoinHistory(
        id: String,
        start: ZonedDateTime,
        end: ZonedDateTime,
    ): List<CoinPriceDto> {
        return warpApiCall<DefaultResponseDto<CoinPriceDto>> {
            val startMillis = start.withZoneSameInstant(ZoneId.of("UTC")).toInstant().toEpochMilli()
            val endMillis = end.withZoneSameInstant(ZoneId.of("UTC")).toInstant().toEpochMilli()
            client.get(getFullUrl("/assets/$id/history")) {
                parameter("interval", "h6")
                parameter("start", startMillis)
                parameter("end", endMillis)
            }
        }.data
    }
}
