package org.example.currency_tracker.ui.coin_details.view_model

import org.example.currency_tracker.data.repository.Repository
import org.example.currency_tracker.ui.base.BaseViewModel
import org.example.currency_tracker.ui.shared.interactions.CoinDetailsInteractions
import org.example.currency_tracker.ui.shared.ui_state.CoinDetailsUiState

class CoinDetailsViewModel(
    private val repository: Repository,
) : BaseViewModel<CoinDetailsUiState, CoinDetailsEvents>(CoinDetailsUiState()),
    CoinDetailsInteractions {

    override fun initData() {

    }

}
