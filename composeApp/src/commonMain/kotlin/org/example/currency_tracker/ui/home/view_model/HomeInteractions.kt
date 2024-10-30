package org.example.currency_tracker.ui.home.view_model

import org.example.currency_tracker.ui.shared_ui.CoinUiState

interface HomeInteractions {
    fun initData()
    fun onClickCoin(coin: CoinUiState)
}
