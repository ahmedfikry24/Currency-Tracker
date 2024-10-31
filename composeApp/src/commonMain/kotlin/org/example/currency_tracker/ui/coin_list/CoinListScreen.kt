package org.example.currency_tracker.ui.coin_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.example.currency_tracker.ui.shared.ui_state.CoinListUiState
import org.example.currency_tracker.ui.shared.interactions.CoinListInteractions
import org.example.currency_tracker.ui.composable.CoinListItem
import org.example.currency_tracker.ui.composable.ContentError
import org.example.currency_tracker.ui.composable.ContentLoading
import org.example.currency_tracker.ui.composable.ContentVisible
import org.example.currency_tracker.ui.shared.ui_state.ContentStatus
import org.example.currency_tracker.ui.theme.spacing


@Composable
fun CoinListScreen(
    modifier: Modifier = Modifier,
    state: CoinListUiState,
    interactions: CoinListInteractions,
) {
    ContentLoading(isVisible = state.contentStatus == ContentStatus.LOADING)
    ContentVisible(isVisible = state.contentStatus == ContentStatus.VISIBLE) {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.space8)
        ) {
            items(
                items = state.coins,
                key = { it.id }
            ) { coin ->
                CoinListItem(
                    state = coin,
                    onClick = { interactions.onClickCoin(coin) }
                )
            }
        }
    }
    ContentError(
        isVisible = state.contentStatus == ContentStatus.FAILURE,
        onTryAgain = interactions::initData
    )
}