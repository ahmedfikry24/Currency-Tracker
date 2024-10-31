package org.example.currency_tracker.ui.shared.ui_state

import androidx.compose.runtime.Immutable

@Immutable
data class CoinListUiState(
    val contentStatus: ContentStatus = ContentStatus.LOADING,
    val coins: List<CoinUiState> = listOf(),
    val selectedCoin: CoinUiState = CoinUiState(),
) 
