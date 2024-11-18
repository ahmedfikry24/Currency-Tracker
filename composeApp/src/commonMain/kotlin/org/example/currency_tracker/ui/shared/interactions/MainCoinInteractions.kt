package org.example.currency_tracker.ui.shared.interactions

import org.example.currency_tracker.ui.shared.ui_state.CoinUiState

interface MainCoinInteractions {
    fun initData()
    fun switchScreenContent()
    fun onCLickCoin(coin: CoinUiState)
}
