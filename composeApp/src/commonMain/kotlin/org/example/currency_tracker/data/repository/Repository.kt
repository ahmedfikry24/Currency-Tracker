package org.example.currency_tracker.data.repository

import org.example.currency_tracker.data.model.CoinDto

interface Repository {
    suspend fun getCoinList(): List<CoinDto>
}