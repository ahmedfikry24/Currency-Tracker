package org.example.currency_tracker.ui.shared.ui_state

data class MainCoinUiState(
    val contentStatus: ContentStatus = ContentStatus.LOADING,
    val isCoinListVisible: Boolean = true,
    val coinList: List<CoinUiState> = listOf(),
    val selectedCoin: CoinUiState = CoinUiState(),
    val coinHistory: List<CoinHistoryUiState> = listOf(),
) 
