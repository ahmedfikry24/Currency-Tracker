package org.example.currency_tracker.ui.coin_list.view_model

import kotlinx.coroutines.flow.update
import org.example.currency_tracker.data.model.CoinDto
import org.example.currency_tracker.data.repository.Repository
import org.example.currency_tracker.ui.base.BaseViewModel
import org.example.currency_tracker.ui.shared_ui.CoinUiState
import org.example.currency_tracker.ui.shared_ui.ContentStatus
import org.example.currency_tracker.ui.shared_ui.toUiState

class CoinListViewModel(
    private val repository: Repository,
) : BaseViewModel<CoinListUiState, CoinListEvents>(CoinListUiState()), CoinListInteractions {

    override fun initData() {
        tryExecute(
            { repository.getCoinList() },
            ::coinListSuccess,
            { coinListError() }
        )
    }

    private fun coinListSuccess(coins: List<CoinDto>) {
        _state.update { value ->
            value.copy(
                contentStatus = ContentStatus.VISIBLE,
                coins = coins.map { it.toUiState() }
            )
        }
    }

    private fun coinListError() {
        _state.update { it.copy(contentStatus = ContentStatus.FAILURE) }
        sendEvent(CoinListEvents.ShowAnError)
    }

    override fun onClickCoin(coin: CoinUiState) {
        _state.update { it.copy(selectedCoin = coin) }
        sendEvent(CoinListEvents.NavigateToCoinDetails(coin.id))
    }
}
