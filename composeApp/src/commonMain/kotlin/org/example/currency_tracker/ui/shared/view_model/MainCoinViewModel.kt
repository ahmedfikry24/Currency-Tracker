package org.example.currency_tracker.ui.shared.view_model

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.example.currency_tracker.data.model.CoinDto
import org.example.currency_tracker.data.model.CoinPriceDto
import org.example.currency_tracker.data.repository.Repository
import org.example.currency_tracker.ui.base.BaseViewModel
import org.example.currency_tracker.ui.shared.interactions.MainCoinInteractions
import org.example.currency_tracker.ui.shared.ui_state.CoinUiState
import org.example.currency_tracker.ui.shared.ui_state.ContentStatus
import org.example.currency_tracker.ui.shared.ui_state.MainCoinUiState
import org.example.currency_tracker.ui.shared.ui_state.toUiState
import java.time.ZonedDateTime

class MainCoinViewModel(
    private val repository: Repository,
) : BaseViewModel<MainCoinUiState, MainCoinEvents>(MainCoinUiState()), MainCoinInteractions {

    override fun initData() {
        viewModelScope.launch(Dispatchers.IO) {
            getCoinList()
            getCoinHistory("bitcoin")
        }
    }


    private fun getCoinList() {
        tryExecute(
            { repository.getCoinList() },
            ::coinListSuccess,
            { coinError() }
        )
    }

    private fun coinListSuccess(coins: List<CoinDto>) {
        _state.update { value ->
            value.copy(
                contentStatus = ContentStatus.VISIBLE,
                coinList = coins.map { it.toUiState() },
                selectedCoin = coins.find { it.id == "bitcoin" }!!.toUiState()
            )
        }
    }

    override fun onCLickCoin(coin: CoinUiState) {
        _state.update { it.copy(selectedCoin = coin) }
        getCoinHistory(coin.id)
    }

    private fun getCoinHistory(id: String) {
        tryExecute(
            {
                repository.getCoinHistory(
                    id = id,
                    start = ZonedDateTime.now().minusDays(5),
                    end = ZonedDateTime.now()
                )
            },
            ::coinHistorySuccess,
            { coinError() }
        )
    }

    private fun coinHistorySuccess(history: List<CoinPriceDto>) {
        _state.update { value ->
            value.copy(
                coinHistory = history.map { it.toUiState() }
            )
        }
    }

    private fun coinError() {
        _state.update { it.copy(contentStatus = ContentStatus.FAILURE) }
    }


    override fun switchScreenContent() {
        _state.update { it.copy(isCoinListVisible = !it.isCoinListVisible) }
    }

}
