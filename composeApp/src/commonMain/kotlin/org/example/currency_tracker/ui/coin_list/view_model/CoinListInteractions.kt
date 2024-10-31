package org.example.currency_tracker.ui.coin_list.view_model

import org.example.currency_tracker.ui.shared_ui.CoinUiState

interface CoinListInteractions {
    fun initData()
    fun onClickCoin(coin: CoinUiState)
}
