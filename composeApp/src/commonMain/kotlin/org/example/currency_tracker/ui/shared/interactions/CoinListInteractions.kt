package org.example.currency_tracker.ui.shared.interactions

import org.example.currency_tracker.ui.shared.ui_state.CoinUiState

interface CoinListInteractions {
    fun initData()
    fun onClickCoin(coin: CoinUiState)
}
