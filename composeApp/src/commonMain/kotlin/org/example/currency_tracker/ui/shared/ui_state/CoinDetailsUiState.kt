package org.example.currency_tracker.ui.shared.ui_state

data class CoinDetailsUiState(
    val contentStatus: ContentStatus = ContentStatus.LOADING,
    val coin: CoinUiState = CoinUiState(),
) 
