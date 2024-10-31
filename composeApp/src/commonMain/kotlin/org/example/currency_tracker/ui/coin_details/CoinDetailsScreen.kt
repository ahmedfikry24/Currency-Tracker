package org.example.currency_tracker.ui.coin_details

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.example.currency_tracker.ui.composable.ContentError
import org.example.currency_tracker.ui.composable.ContentLoading
import org.example.currency_tracker.ui.composable.ContentVisible
import org.example.currency_tracker.ui.shared.interactions.CoinDetailsInteractions
import org.example.currency_tracker.ui.shared.ui_state.CoinDetailsUiState
import org.example.currency_tracker.ui.shared.ui_state.ContentStatus

@Composable
fun CoinDetailsScreen(
    modifier: Modifier = Modifier,
    state: CoinDetailsUiState,
    interactions: CoinDetailsInteractions,
) {
    ContentLoading(isVisible = state.contentStatus == ContentStatus.LOADING)
    ContentVisible(isVisible = state.contentStatus == ContentStatus.VISIBLE) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {

        }
    }
    ContentError(
        isVisible = state.contentStatus == ContentStatus.FAILURE,
        onTryAgain = interactions::initData
    )
}