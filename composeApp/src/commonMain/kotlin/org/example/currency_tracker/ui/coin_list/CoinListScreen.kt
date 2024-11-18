package org.example.currency_tracker.ui.coin_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.example.currency_tracker.ui.composable.CoinListItem
import org.example.currency_tracker.ui.shared.interactions.MainCoinInteractions
import org.example.currency_tracker.ui.shared.ui_state.MainCoinUiState
import org.example.currency_tracker.ui.theme.spacing


@Composable
fun CoinListScreen(
    modifier: Modifier = Modifier,
    state: MainCoinUiState,
    interactions: MainCoinInteractions,
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.space8)
    ) {
        items(
            items = state.coinList,
            key = { it.id }
        ) { coin ->
            CoinListItem(
                state = coin,
                onClick = {
                    interactions.onCLickCoin(coin)
                    interactions.switchScreenContent()
                }
            )
        }
    }
}