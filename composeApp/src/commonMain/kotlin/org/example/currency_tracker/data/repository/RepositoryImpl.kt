package org.example.currency_tracker.data.repository

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import org.example.currency_tracker.data.model.CoinDto
import org.example.currency_tracker.data.model.CoinResponseDto
import org.example.currency_tracker.data.utils.getFullUrl
import org.example.currency_tracker.data.utils.warpApiCall

class RepositoryImpl(
    private val client: HttpClient
) : Repository {

    override suspend fun getCoinList(): List<CoinDto> {
        return warpApiCall<CoinResponseDto> { client.get(getFullUrl("/assets")) }.data
    }
}
