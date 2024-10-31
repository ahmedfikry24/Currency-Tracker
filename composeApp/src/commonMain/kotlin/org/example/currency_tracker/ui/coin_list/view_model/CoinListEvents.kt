package org.example.currency_tracker.ui.coin_list.view_model

sealed interface CoinListEvents {
    data class NavigateToCoinDetails(val id: String) : CoinListEvents
}
