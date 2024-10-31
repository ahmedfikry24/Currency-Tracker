package org.example.currency_tracker.navigation.android

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.example.currency_tracker.ui.coin_details.CoinDetailsScreen
import org.example.currency_tracker.ui.coin_list.CoinListScreen
import org.example.currency_tracker.ui.composable.ContentError
import org.example.currency_tracker.ui.composable.ContentLoading
import org.example.currency_tracker.ui.composable.ContentVisible
import org.example.currency_tracker.ui.shared.ui_state.ContentStatus
import org.example.currency_tracker.ui.shared.view_model.MainCoinViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AndroidNavigation() {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Box(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(innerPadding)
        ) {
            val viewModel = koinViewModel<MainCoinViewModel>()
            val state by viewModel.state.collectAsStateWithLifecycle()
            ContentLoading(isVisible = state.contentStatus == ContentStatus.LOADING)
            ContentVisible(isVisible = state.contentStatus == ContentStatus.VISIBLE) {
                Crossfade(
                    modifier = Modifier.fillMaxSize(),
                    targetState = state.isCoinListVisible
                ) { isCoinList ->
                    if (isCoinList) {
                        CoinListScreen(state = state, interactions = viewModel)
                    } else {
                        CoinDetailsScreen(state = state, interactions = viewModel)
                    }
                }

            }
            ContentError(
                isVisible = state.contentStatus == ContentStatus.FAILURE,
                onTryAgain = viewModel::initData
            )
        }
    }
}
